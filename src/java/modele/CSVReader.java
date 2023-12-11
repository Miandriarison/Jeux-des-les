/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author hp
 */
public class CSVReader {
    public String getCSV(String csvFile){
        csvFile="C:\\Users\\Asus\\Desktop\\nomena\\evaluation3 detail\\"+csvFile;
        String retour="";
        String line="";
        String csvDelimiter=",";
        try(BufferedReader br=new BufferedReader(new FileReader(csvFile))){
            while((line=br.readLine())!=null){
                String[] values=line.split(csvDelimiter);
                for(String value:values){
                    retour+=value+" ";
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return retour;
    }
}
