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
 * This class builds EntityManager object for FormServlet.
 * @author Sl-lAl-liN
 */
public class PersistenceBuilder {
    
    /**
     * String variables, which be used to store entered information by config form.
     */
    public static String PORT;
    public static String DATABASE_NAME;
    public static String USERNAME;
    public static String PASSWORD;
    
    /**
     * Name of the PERSISTENCE_UNIT
     */
    private static final String PERSISTENCE_UNIT_NAME = "com_ShahinSafari-Optiscan_war_1.0";

    /**
     * EntityManager variable.
     */
    private static EntityManager entityManager;
    

    public static void build() throws Exception{
        //Create a HashMap to store properties
        Map<String, String> persistenceMap = new HashMap<>();

        //Properties of PERSISTENCE_UNIT 
        persistenceMap.put("hibernate.connection.url", "jdbc:postgresql://localhost:"+PORT+"/"+DATABASE_NAME);
        persistenceMap.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        persistenceMap.put("hibernate.connection.username", USERNAME);
        persistenceMap.put("hibernate.connection.password", PASSWORD);
        persistenceMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        persistenceMap.put("hibernate.hbm2ddl.auto", "update");
        
        //create entityManager object 
        entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,persistenceMap).createEntityManager();
    }

    public static EntityManager getEntityManager() throws Exception {
        build();
        return entityManager;
    }

    
    
    

}
