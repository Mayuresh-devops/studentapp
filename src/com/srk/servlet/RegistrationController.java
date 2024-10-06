package com.srk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.srk.dao.StudentDAO;
import vo.Student;

@WebServlet("/registrationController")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve parameters from the form
        String name = request.getParameter("fullname");
        String Addr = request.getParameter("address");
        String age = request.getParameter("age");
        String Qual = request.getParameter("qual");
        String Persent = request.getParameter("percent");
        String Year = request.getParameter("yop");

        // Basic validation to check if any field is empty
        if (isAnyFieldEmpty(name, Addr, age, Qual, Persent, Year)) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            out.println("<font color=red>Please fill all the fields</font>");
            rd.include(request, response);
        } else {
            // Create student object and populate it
            Student student = new Student();
            student.setStudentName(escapeHtml(name));
            student.setStudentAddr(escapeHtml(Addr));
            student.setAge(escapeHtml(age));
            student.setQualification(escapeHtml(Qual));
            student.setPercentage(escapeHtml(Persent));
            student.setYearPassed(escapeHtml(Year));

            // Save student to database
            int status = StudentDAO.saveStudent(student);
            if (status > 0) {
                // Redirect to view all students if successful
                response.sendRedirect("viewStudents");
            } else {
                out.println("Sorry! Unable to save record.");
            }

            out.close();
        }
    }

    // Method to check if any field is empty
    private boolean isAnyFieldEmpty(String name, String addr, String age, String qual, String percent, String year) {
        return name == null || addr == null || age == null || qual == null || percent == null || year == null ||
               name.isEmpty() || addr.isEmpty() || age.isEmpty() || qual.isEmpty() || percent.isEmpty() || year.isEmpty();
    }

    // Utility method to escape HTML and avoid XSS attacks
    private String escapeHtml(String input) {
        if (input == null) return null;
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#39;");
    }
}
