/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  ASUS
 * Created: 4 févr. 2023
 */
<copyfiles files="${libs.hibernate4-support.classpath}" todir="${build.web.dir}/WEB-INF/lib"/>




bean id="sessionFactory"
              class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">

        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="model"/>

        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>
                <prop key="hibernate.current_session_context_class">thread</prop>
                <prop key="hibernate.show_sql">true</prop>
            </props>
        </property>
    </bean>
    <bean id="hibernateDAO" class="dao.HibernateDao">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>

bean id="sessionFactory"
              class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">

        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="model"/>

        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>
                <prop key="hibernate.current_session_context_class">thread</prop>
                <prop key="hibernate.show_sql">true</prop>
            </props>
        </property>
    </bean>
    <bean id="hibernateDAO" class="dao.HibernateDao">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>

<bean id="sessionFactory"
              class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">

        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="model"/>

        <property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>
                <prop key="hibernate.current_session_context_class">thread</prop>
                <prop key="hibernate.show_sql">true</prop>
            </props>
        </property>
    </bean>
    <bean id="hibernateDAO" class="dao.HibernateDao">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>

