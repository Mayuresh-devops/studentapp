package com.srk.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.srk.dao.StudentDAO;

@WebServlet("/deleteStudent")
public class DeleteStudent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("stdId");

        // Validate the input - make sure it's a valid integer
        if (sid == null || sid.isEmpty()) {
            // Handle the case where no student ID was provided
            request.setAttribute("error", "Student ID is missing!");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            return;
        }

        try {
            int id = Integer.parseInt(sid);

            // Delete student using the DAO
            int result = StudentDAO.deleteStudent(id);

            if (result > 0) {
                // Deletion successful, redirect to view students page
                response.sendRedirect("viewStudents");
            } else {
                // Handle case where the student ID does not exist
                request.setAttribute("error", "Failed to delete student or student not found.");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("error", "Invalid student ID format.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle other exceptions (e.g., database issues)
            e.printStackTrace();  // Ideally, use a logging framework
            request.setAttribute("error", "An error occurred while deleting the student.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}
