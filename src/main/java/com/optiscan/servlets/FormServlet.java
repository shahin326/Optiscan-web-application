/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optiscan.servlets;

import com.optiscan.database.Candidate;
import com.optiscan.database.PersistenceBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
     * Value Strings, which be used to store entered information.
     */
    private String firstName;
    private String lastName;
    private String day;
    private String month;
    private String year;
    private String gender;
    private String message;

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
        this.firstName = request.getParameter("firstname");
        this.lastName = request.getParameter("lastname");
        this.day = request.getParameter("day");
        this.month = request.getParameter("month");
        this.year = request.getParameter("year");
        this.gender = request.getParameter("gender");
        this.message = request.getParameter("message");

        // if not validated get back to form page
        if (!validateCandidateForm(request, response)) {
            keepValues(request);
            request.getRequestDispatcher("/form.jsp").forward(request, response);
        } else {
            // form validated
            saveCandidate();
            
            //confirm results to the user
            confirmSubmission(request, response);
        }
    }

    private void saveCandidate() {
        //create entity manager 
        EntityManager entityManager = PersistenceBuilder.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // Create candidate object
            Candidate entity = new Candidate();

            //set first and last name
            entity.setFirstName(firstName);
            entity.setLastName(lastName);

            //create date object from strings
            String inputString = this.day + "." + this.month + "." + this.year;
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
            //rollback transaction
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

    /**
     * Validates given inputs for the candidate form.
     *
     * @param request servlet request
     * @param response servlet response
     * @return true if validation OK, false otherwise
     */
    private Boolean validateCandidateForm(HttpServletRequest request, HttpServletResponse response) {
        //first assume that form validates
        validated = true;

        //if any of the following conditions fails the validation fails also
        if (firstName.equals("") || lastName.equals("")) {
            request.setAttribute("nameError", "Please enter your full name:");
            validated = false;
        }
        if (day.equals("") || month.equals("") || year.equals("")) {
            request.setAttribute("dateError", "Please enter your birthday:");
            validated = false;
        }
        if (gender.equals("")) {
            request.setAttribute("genderError", "Please select your gender:");
            validated = false;
        }
        if (message.equals("")) {
            request.setAttribute("messageError", "Please answer the question:");
            validated = false;
        }
        return validated;

    }

    /**
     * Keeps input values in the web form.
     *
     * @param request servlet request
     */
    private void keepValues(HttpServletRequest request) {
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("day", day);
        request.setAttribute("month", month);
        request.setAttribute("year", year);
        request.setAttribute("gender", gender);
        request.setAttribute("message", message);
    }

    /**
     * Shows to the user a confirmation of successful processing.
     */
    private void confirmSubmission(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Confirmation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Your request has been succesfully processed!</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
