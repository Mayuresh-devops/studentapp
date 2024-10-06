package com.srk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.srk.dao.StudentDAO;
import vo.Student;

@WebServlet("/editStudent")
public class EditStudent extends HttpServlet {

    // Ensure proper encoding for request and response
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");  // Set encoding for the response
        
        // Ensure the request uses UTF-8 encoding as well
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();  
        
        String sid = request.getParameter("stdId");
        
        if (sid == null || sid.isEmpty()) {
            // Better to redirect to a custom error page instead of direct error message
            response.sendRedirect("errorPage.jsp?error=Student ID is missing!");
            return;
        }

        int stdId;
        try {
            stdId = Integer.parseInt(sid);
        } catch (NumberFormatException e) {
            // Same as above, redirect to error page
            response.sendRedirect("errorPage.jsp?error=Invalid Student ID format!");
            return;
        }

        Student student = StudentDAO.getStudentById(stdId);
        
        if (student == null) {
            response.sendRedirect("errorPage.jsp?error=Student not found!");
            return;
        }

        // Print the form to edit student details
        out.println("<h1>Update Student</h1>");
        out.print("<form action='editStudent2' method='post'>");  
        out.print("<table>");  
        out.print("<tr><td></td><td><input type='hidden' name='stdId' value='" + student.getStudentId() + "'/></td></tr>");  
        out.print("<tr><td>Full Name :</td><td><input type='text' name='stdname' value='" + escapeHtml(student.getStudentName()) + "'/></td></tr>");  
        out.print("<tr><td>Address :</td><td><inp
