/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optiscan.database;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Sl-lAl-liN
 */
public class PersistenceBuilder {
    
    public static String PORT;
    public static String DATABASE_NAME;
    public static String USERNAME;
    public static String PASSWORD;
    
    private static final String PERSISTENCE_UNIT_NAME = "com_ShahinSafari-Optiscan_war_1.0";

    private static EntityManager entityManager;
    
    /* <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/Candidate_database"/>
      <property name="hibernate.connection.driver_class" value="org.postgresql.Driver"/>
      <property name="hibernate.connection.username" value="postgres"/>
      <property name="hibernate.connection.password" value="pass"/>
      <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
      <property name="hibernate.hbm2ddl.auto" value="update"/>*/

    public static void build() {
        Map<String, String> persistenceMap = new HashMap<>();

        persistenceMap.put("hibernate.connection.url", "jdbc:postgresql://localhost:"+PORT+"/"+DATABASE_NAME);
        persistenceMap.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        persistenceMap.put("hibernate.connection.username", USERNAME);
        persistenceMap.put("hibernate.connection.password", PASSWORD);
        persistenceMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        persistenceMap.put("hibernate.hbm2ddl.auto", "update");
        entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,persistenceMap).createEntityManager();
    }

    public static EntityManager getEntityManager() {
        build();
        return entityManager;
    }

    
    
    

}
