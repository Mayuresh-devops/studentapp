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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");  // Set encoding for the response
        
        // Ensure the request uses UTF-8 encoding as well
        request.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();  
        
        out.println("<h1>Student List</h1>");
        out.print("<table border='1'>");
        out.print("<tr><th>ID</th><th>Name</th><th>Address</th><th>Age</th><th>Qualification</th><th>Percentage</th><th>Year Passed</th></tr>");
        
        // Get list of students from DAO
        List<Student> students = StudentDAO.getAllStudents();
        
        // Iterate over the list and display each student's information
        for (Student student : students) {
            out.print("<tr>");
            out.print("<td>" + String.valueOf(student.getStudentId()) + "</td>");
            out.print("<td>" + escapeHtml(student.getStudentName()) + "</td>");
            out.print("<td>" + escapeHtml(student.getStudentAddr()) + "</td>");
            out.print("<td>" + String.valueOf(student.getAge()) + "</td>");
            out.print("<td>" + escapeHtml(student.getQualification()) + "</td>");
            out.print("<td>" + String.valueOf(student.getPercentage()) + "</td>");
            out.print("<td>" + String.valueOf(student.getYearPassed()) + "</td>");
            out.print("</tr>");
        }
        
        out.print("</table>");
        out.close();
    }  
    
    // Utility method to escape HTML to prevent XSS
    private String escapeHtml(String input) {
        if (input == null) return null;
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#39;");
    }
}
