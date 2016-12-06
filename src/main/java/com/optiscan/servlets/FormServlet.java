/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optiscan.servlets;

import org.hibernate.jpa.HibernatePersistenceProvider;
import com.optiscan.database.Candidate;
import com.optiscan.database.PersistenceBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sl-lAl-liN
 */
public class FormServlet extends HttpServlet {

    /**
     * Error Strings, which will be used for showing possible errors to the
     * user.
     */
    private String NAME_ERROR;
    private String DATE_ERROR;
    private String GENDER_ERROR;
    private String MESSAGE_ERROR;

    private final String PERSISTENCE_UNIT_NAME = "com_ShahinSafari-Optiscan_war_1.0";

    /**
     * Value Strings, which be used to store entered information.
     */
    private String firstName;
    private String lastName;
    private String day;
    private String month;
    private String year;
    private String gender;
    private String message;
    
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
        this.firstName = request.getParameter("firstname");
        this.lastName = request.getParameter("lastname");
        this.day = request.getParameter("day");
        this.month = request.getParameter("month");
        this.year = request.getParameter("year");
        this.gender = request.getParameter("gender");
        this.message = request.getParameter("message");
        
        //first assume that form validates
        validated = true;

        if (firstName.equals("") || lastName.equals("") ){
            request.setAttribute("nameError","Please enter your full name:");
            validated=false;
        }
        if (day.equals("") || month.equals("") || year.equals("")){
            request.setAttribute("dateError","Please enter your birthday:");
            validated=false;
        }
        if (gender.equals("")){
            request.setAttribute("genderError","Please select your gender:");
            validated=false;
        }
        if (message.equals("")){
            request.setAttribute("messageError","Please answer the question:");
            validated=false;
        }
     
         // if not validated get back to form page
        if(!validated){
            request.setAttribute("firstName",firstName);
            request.setAttribute("lastName",lastName);
            request.setAttribute("day",day);
            request.setAttribute("month",month);
            request.setAttribute("year",year);
            request.setAttribute("gender",gender);
            request.setAttribute("message",message);
            request.getRequestDispatcher("/form.jsp").forward(request, response);
        }else{
            // form validates
            saveCandidate();
        }
        //returnRequest(request, response);
        

    }

    /**
     * In case of a validation error it returns the request while keeping users
     * data. request servlet request
     *
     * @param response servlet response
     */
    private void returnRequest(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("firstname", this.firstName);
        request.setAttribute("lastname", this.lastName);
        request.setAttribute("day", this.day);
        request.setAttribute("month", this.month);
        request.setAttribute("year", this.year);
        request.setAttribute("gender", this.gender);
        request.setAttribute("message", this.message);
        try {
            // get back to order.jsp page using forward
            request.getRequestDispatcher("/form.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(FormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveCandidate() {   
        //create entity manager 
        
        //EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        
        EntityManager entityManager = PersistenceBuilder.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // Create candidate object
            Candidate entity = new Candidate();
            
            //set first and last name
            entity.setFirstName(firstName);
            entity.setLastName(lastName);

            //create date object
            String inputString = this.day+"."+this.month+"."+this.year;
            DateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");
            Date inputDate = dateFormat.parse(inputString);
            entity.setBirthday(inputDate);
            
            //set gender
            entity.setGender(gender);
            
            //set message
            entity.setMessage(message);
            
            //save entity
            entityManager.persist(entity);
            transaction.commit();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            // Clear all entities from the EntityManager and close it
            entityManager.clear();
            entityManager.close();
        }
    }

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

}
