package com.srk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.srk.dao.StudentDAO;
import vo.Student;

@WebServlet("/viewStudents")
public class ViewStudents extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
              throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        // Display link to register student
        out.println("<a href='index.jsp'>Register Student</a>");  
        out.println("<h1>Students List</h1>");  
        
        try {
            // Retrieve all students from the database
            List<Student> list = StudentDAO.getAllStudents();
            
            // Check if there are no students
            if (list.isEmpty()) {
                out.println("<p>No students found.</p>");
            } else {
                // Create a table to display student information
                out.print("<table border='1' width='100%'");  
                out.print("<tr><th>Student ID</th><th>Student Name</th><th>Student Address</th><th>Student Age</th><th>Student Qualification</th><th>Student Percentage</th><th>Student Year Passed</th><th>Edit</th><th>Delete</th></tr>");  
                
                // Iterate through the list of students and display each one
                for (Student student : list) {  
                    out.print("<tr>");
                    out.print("<td>" + escapeHtml(student.getStudentId()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getStudentName()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getStudentAddr()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getAge()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getQualification()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getPercentage()) + "</td>");
                    out.print("<td>" + escapeHtml(student.getYearPassed()) + "</td>");
                    out.print("<td><a href='editStudent?stdId=" + student.getStudentId() + "'>edit</a></td>");
                    out.print("<td><a href='deleteStudent?stdId=" + student.getStudentId() + "'>delete</a></td>");
                    out.print("</tr>");
                }  
                out.print("</table>");
            }
        } catch (Exception e) {
            // Log the error (use a proper logging framework like log4j or SLF4J in a real-world app)
            out.println("<p>Error fetching students. Please try again later.</p>");
            e.printStackTrace();
        }
        
        out.close();  
    }
    
    // Utility method to escape HTML and avoid XSS vulnerabilities
    private String escapeHtml(String input) {
        if (input == null) return null;
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#39;");
    }
}
