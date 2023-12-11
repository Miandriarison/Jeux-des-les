/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genreiquedao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rabeasimbola
 */
public class ObjetBdd {
     public Connection getConnexion() throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/afaka","postgres","nomena");
        return conn;
    }
    public String toUpper(String s)
    {
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    public String[] getAttr() throws Exception{
        String nomTable=getClass().getSimpleName();
        String [] lesAttributs=new String[getClass().getDeclaredFields().length];
        String tousLesAtr=new String();
        String allValues=new String();
        Vector typeretour = new Vector();
        for(int i=0;i<lesAttributs.length;i++)
        {
            lesAttributs[i]=getClass().getDeclaredFields()[i].getName();
            lesAttributs[i]=toUpper(lesAttributs[i]);
            Object attributValue=getClass().getMethod("get"+lesAttributs[i]).invoke(this);
        }
        return null;
//        String nomTable=getClass().getSimpleName();
//        String [] lesAttributs=new String[getClass().getDeclaredFields().length];
//        Vector liste = new Vector();
//        lesAttributs[i]=getClass().getDeclaredFields()[i].getName();
//        lesAttributs[i]=toUpper(lesAttributs[i]);
//        Object attributValue=getClass().getMethod("get"+lesAttributs[i]).invoke(this);
//        for(int i = 0 ; i < lesAttributs.length ; i++){
//            this.getClass().getConstructor(getClass()).newInstance();
//        }
    }
    public void create() throws Exception
    {
        Connection con=getConnexion();
        String nomTable=getClass().getSimpleName();
        String [] lesAttributs=new String[getClass().getDeclaredFields().length];
        String tousLesAtr=new String();
        String allValues=new String();
        
        for(int i=0;i<lesAttributs.length;i++)
        {
            lesAttributs[i]=getClass().getDeclaredFields()[i].getName();
            lesAttributs[i]=toUpper(lesAttributs[i]);
            if(lesAttributs[i].equals("Id") == false){
                Object attributValue=getClass().getMethod("get"+lesAttributs[i]).invoke(this);
                tousLesAtr+=lesAttributs[i];
                if(attributValue!=null)
                {   
                    allValues+="'"+attributValue.toString()+"'";
                }
                else if(attributValue==null && i!=0)
                {
                    allValues+="null";
                }

                if(i<lesAttributs.length-1)
                {
                    tousLesAtr+=",";
                    allValues+=",";
                }
            }else{
                continue;
            }
        }
        String requete = "insert into "+nomTable+" ("+tousLesAtr+") values ("+allValues+")";
//        System.out.println(requete);
        PreparedStatement stmt = con.prepareStatement(requete);

        stmt.executeUpdate();
        stmt.close();
        con.close();
        
    }
    public boolean test_Object(Object objt){
        if(objt instanceof Integer || objt instanceof String || objt instanceof Float || objt instanceof Date || objt instanceof Double){
            return true;
        }else return false;
    }
    public void update() throws Exception
    {
        Connection con=getConnexion();
        String req="update "+getClass().getSimpleName().toLowerCase()+" set ";
        String [] attributsName=new String[getClass().getDeclaredFields().length];
        int count=0;

        for(int i=0;i<attributsName.length;i++)
        {
                attributsName[i]=getClass().getDeclaredFields()[i].getName();
                attributsName[i]=toUpper(attributsName[i]);
                Object attributsValues=getClass().getMethod("get"+attributsName[i]).invoke(this);

                if((attributsValues instanceof String && attributsValues!=null) || (attributsValues instanceof Integer && (int)attributsValues!=0))
                {
                        if(count==0) req+=attributsName[i]+"="+"'"+attributsValues.toString()+"'";
                        if(count>0)	req+=","+attributsName[i]+"="+"'"+attributsValues.toString()+"'";
                        count++;
                }
        }

        req+=" where ";
        for(int i=0;i<attributsName.length;i++)
        {
                attributsName[i]=getClass().getDeclaredFields()[i].getName();
                attributsName[i]=toUpper(attributsName[i]);
                Object attributValue=getClass().getMethod("get"+attributsName[i]).invoke(this);

                if(attributsName[i].equalsIgnoreCase("Id"))
                {
                        req+=attributsName[i]+"="; req+="'"+attributValue.toString()+"'";
                }
        }
        PreparedStatement stmt = con.prepareStatement(req);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
    public ObjetBdd getDbObject(ResultSet r)throws Exception{
        Object ObjetBdd=getClass().newInstance();
	Field [] attrObj=getClass().getDeclaredFields();
	String[] nomAttr=new String[getClass().getDeclaredFields().length];
        for(int i=0;i<attrObj.length;i++)
	{
            nomAttr[i]=attrObj[i].getName();
            Class classType=attrObj[i].getType();
            String allType=classType.getSimpleName();
            Object resultDb=new Object();
            try{
		resultDb=r.getString(nomAttr[i]);
		if(resultDb!=null)
		{
                    if(allType.equalsIgnoreCase("int"))
                    {
                            resultDb=Integer.parseInt((String)resultDb);
                    }
                    else if(allType.equalsIgnoreCase("Timestamp")){
                        resultDb=java.sql.Timestamp.valueOf((String)resultDb);
                    }
                    else if(allType.equalsIgnoreCase("double"))
                    {
                            resultDb=Double.parseDouble((String)resultDb);
                    }
                    else if(allType.equalsIgnoreCase("date"))
                    {
                        resultDb=Date.valueOf((String)resultDb);
                    }
                    else if(allType.equalsIgnoreCase("float"))
                    {
                        resultDb=Float.parseFloat((String)resultDb);
                    }
                    else if(allType.equalsIgnoreCase("Time"))
                    {
                        resultDb=Time.valueOf((String)resultDb);
                    }
                    else
                    {
                            resultDb=(String)resultDb;
                    }
//                    System.err.println("set"+toUpper(nomAttr[i]));
                    ObjetBdd.getClass().getMethod("set"+toUpper(nomAttr[i]),classType).invoke(ObjetBdd,resultDb);
		}
            }catch(java.sql.SQLException ee){}

            }
	return (ObjetBdd)ObjetBdd;
    }
    public Vector find(String requete) throws Exception
    {
        Connection con=getConnexion();
        java.sql.Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(requete);
        Vector DBTable=new Vector();
        while(res.next())
        {
                DBTable.addElement(getDbObject(res));
        }
        res.close();
        con.close();
        return DBTable;
    }
    public void updat(String requete) throws Exception{
        Connection con=getConnexion();
        PreparedStatement stmt;
         try {
             stmt = con.prepareStatement(requete);
             stmt.executeUpdate();
             stmt.close();
         } catch (SQLException ex) {
             ex.printStackTrace();
         }finally{
             con.close();
         }
    }
}
