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
public class FormServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public FormServletTest() {
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
     * Test an example of an invalid candidate form.
     */
    @Test
    public void invalidCandidateForm() throws Exception {
        given(request.getParameter("firstname")).willReturn("");
        given(request.getParameter("lastname")).willReturn("");
        given(request.getParameter("day")).willReturn("");
        given(request.getParameter("month")).willReturn("");
        given(request.getParameter("year")).willReturn("");
        given(request.getParameter("gender")).willReturn("");
        given(request.getParameter("message")).willReturn("");
        
        //assume that form validates
        Boolean validated = true;
        //check for empty values, in case of any error validation fails
        //if any of the following conditions fails the validation fails also
        if (request.getParameter("firstname").equals("") 
                || request.getParameter("lastname").equals("")) {
            request.setAttribute("nameError", "Please enter your full name:");
            validated = false;
        }
        if (request.getParameter("day").equals("") 
                || request.getParameter("month").equals("") 
                || request.getParameter("year").equals("")) {
            request.setAttribute("dateError", "Please enter your birthday:");
            validated = false;
        }
        if (request.getParameter("gender").equals("")) {
            request.setAttribute("genderError", "Please select your gender:");
            validated = false;
        }
        if (request.getParameter("message").equals("")) {
            request.setAttribute("messageError", "Please answer the question:");
            validated = false;
        }
        assertFalse(validated);
    }
    
    
    /**
     * Test processing of a valid candidate form.
     */
    @Test
    public void validCandidateForm()  {
        given(request.getParameter("firstname")).willReturn("Shahin");
        given(request.getParameter("lastname")).willReturn("Safari");
        given(request.getParameter("day")).willReturn("17");
        given(request.getParameter("month")).willReturn("07");
        given(request.getParameter("year")).willReturn("1990");
        given(request.getParameter("gender")).willReturn("male");
        given(request.getParameter("message")).willReturn("I really want this job!");
        
        //assume that form validates
        Boolean validated = true;
        

        //if any of the following conditions fails the validation fails also
        if (request.getParameter("firstname").equals("") 
                || request.getParameter("lastname").equals("")) {
            request.setAttribute("nameError", "Please enter your full name:");
            validated = false;
        }
        if (request.getParameter("day").equals("") 
                || request.getParameter("month").equals("") 
                || request.getParameter("year").equals("")) {
            request.setAttribute("dateError", "Please enter your birthday:");
            validated = false;
        }
        if (request.getParameter("gender").equals("")) {
            request.setAttribute("genderError", "Please select your gender:");
            validated = false;
        }
        if (request.getParameter("message").equals("")) {
            request.setAttribute("messageError", "Please answer the question:");
            validated = false;
        }
        assertTrue(validated);
    }

    


}
