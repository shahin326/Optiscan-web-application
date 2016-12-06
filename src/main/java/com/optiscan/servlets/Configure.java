/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optiscan.servlets;

import com.optiscan.database.PersistenceBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for processing config form.
 * @author Sl-lAl-liN
 */
public class Configure extends HttpServlet {

    /**
     * String variables, which be used to store entered information by config form.
     */
    private String port;
    private String databaseName;
    private String username;
    private String password;

    /**
     * Boolean variable, which will represent if the form validated or not. 
     */
    private Boolean validated;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get all entered values
        this.port = request.getParameter("port");
        this.databaseName = request.getParameter("databaseName");
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");

        // get back to order.jsp page using forward if validation failed
        if (!validateConfigForm(request, response)) {
            keepValues(request, response);
            request.getRequestDispatcher("/config.jsp").forward(request, response);
        } else {
            //try creating an entity manager 
            if (!checkDatabaseConnection()) {
                //databse connection failed
                request.setAttribute("createError", "Could not connect to database please verify input values and try again.");
                keepValues(request, response);
                request.getRequestDispatcher("/config.jsp").forward(request, response);
            }else{
                //database connection stablished
                response.sendRedirect("/ShahinSafari-Optiscan/form.jsp");
            }
            
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Validates given inputs for the config form.
     *
     * @param request servlet request
     * @param response servlet response
     * @return true if validation OK, false otherwise
     */
    private Boolean validateConfigForm(HttpServletRequest request, HttpServletResponse response) {
        //assume that form validates
        validated = true;
        //check for empty values, in case of any error validation fails
        if (port.equals("")) {
            request.setAttribute("portError", "Port number is missing");
            validated = false;
        }

        if (databaseName.equals("")) {
            request.setAttribute("nameError", "Database name is missing");
            validated = false;
        }

        if (username.equals("")) {
            request.setAttribute("usernameError", "Usename is missing");
            validated = false;
        }

        if (password.equals("")) {
            request.setAttribute("passwordError", "Password is missing");
            validated = false;
        }
        return validated;

    }

    /**
     * Creates an entity manager object if input values are correct.
     *
     * @return true if entity created, false otherwise
     */
    private Boolean checkDatabaseConnection() {
        PersistenceBuilder.PORT = port;
        PersistenceBuilder.DATABASE_NAME = databaseName;
        PersistenceBuilder.USERNAME = username;
        PersistenceBuilder.PASSWORD = password;

        EntityManager entityManager = PersistenceBuilder.getEntityManager();
        if (entityManager == null) {
            return false;
        } else {
            entityManager.clear();
            entityManager.close();
            return true;
        }
    }

    /**
     * Keeps input values in the web form.
     * 
     * @param request servlet request
     * @param response servlet response
     */
    private void keepValues(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("port", port);
        request.setAttribute("databaseName", databaseName);
        request.setAttribute("username", username);
        request.setAttribute("password", password);
    }
}
