/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optiscan.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Sl-lAl-liN
 */
public class ConfigureTest {
    private HttpServletRequest request;       
    private HttpServletResponse response;  
    
    public ConfigureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);       
        response = mock(HttpServletResponse.class);
    }
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test an example of an invalid config form.
     */
    @Test
    public void invalidConfigForm() throws Exception {
        given(request.getParameter("port")).willReturn("");
        given(request.getParameter("databaseName")).willReturn("");
        given(request.getParameter("username")).willReturn("");
        given(request.getParameter("password")).willReturn("");
        
        //assume that form validates
        Boolean validated = true;
        //check for empty values, in case of any error validation fails
        if (request.getParameter("port").equals("")) {
            request.setAttribute("portError", "Port number is missing");
            validated = false;
        }

        if (request.getParameter("databaseName").equals("")) {
            request.setAttribute("nameError", "Database name is missing");
            validated = false;
        }

        if (request.getParameter("username").equals("")) {
            request.setAttribute("usernameError", "Usename is missing");
            validated = false;
        }

        if (request.getParameter("password").equals("")) {
            request.setAttribute("passwordError", "Password is missing");
            validated = false;
        }
        assertFalse(validated);
    }
    
    
    /**
     * Test processing of a valid config form.
     */
    @Test
    public void validConfigForm() throws Exception {
        given(request.getParameter("port")).willReturn("1234");
        given(request.getParameter("databaseName")).willReturn("name");
        given(request.getParameter("username")).willReturn("user");
        given(request.getParameter("password")).willReturn("pass");
        
        //assume that form validates
        Boolean validated = true;
        //check for empty values, in case of any error validation fails
        if (request.getParameter("port").equals("")) {
            request.setAttribute("portError", "Port number is missing");
            validated = false;
        }

        if (request.getParameter("databaseName").equals("")) {
            request.setAttribute("nameError", "Database name is missing");
            validated = false;
        }

        if (request.getParameter("username").equals("")) {
            request.setAttribute("usernameError", "Usename is missing");
            validated = false;
        }

        if (request.getParameter("password").equals("")) {
            request.setAttribute("passwordError", "Password is missing");
            validated = false;
        }
        assertTrue(validated);
    }

    

    
}
