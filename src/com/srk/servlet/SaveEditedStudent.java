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

@WebServlet("/editStudent2")
public class SaveEditedStudent extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
              throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
          
        // Retrieve student ID and other updated details from the request
        String sid = request.getParameter("stdId");  
        int studentId = Integer.parseInt(sid);  
        
        String studentName = request.getParameter("stdname");  
        String studentAddrs = request.getParameter("stdaddrs");  
        String studentAge = request.getParameter("stdage");  
        String studentQual = request.getParameter("stdqual");  
        String studentPercent = request.getParameter("stdpercent");
        String studentYearPass = request.getParameter("stdyearpass");
        
        // Validation: Ensure no fields are empty
        if (isAnyFieldEmpty(studentName, studentAddrs, studentAge, studentQual, studentPercent, studentYearPass)) {
            out.println("<font color=red>Please fill all the fields</font>");
            request.getRequestDispatcher("editStudent?stdId=" + studentId).include(request, response);
            return;
        }
        
        // Create Student object and set the updated values
        Student student = new Student();  
        student.setStudentId(studentId); 
        student.setStudentName(escapeHtml(studentName));
        student.setStudentAddr(escapeHtml(studentAddrs));
        student.setAge(escapeHtml(studentAge));
        student.setQualification(escapeHtml(studentQual));
        student.setPercentage(escapeHtml(studentPercent));
        student.setYearPassed(escapeHtml(studentYearPass));  
        
        // Update the student in the database
        int status = StudentDAO.updateStudent(student);  
        if (status > 0) {  
            response.sendRedirect("viewStudents");  
        } else {  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();  
    }

    // Helper method to check if any input field is empty
    private boolean isAnyFieldEmpty(String name, String addr, String age, String qual, String percent, String year) {
        return name == null || addr == null || age == null || qual == null || percent == null || year == null ||
               name.isEmpty() || addr.isEmpty() || age.isEmpty() || qual.isEmpty() || percent.isEmpty() || year.isEmpty();
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
