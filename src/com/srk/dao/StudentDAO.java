package com.srk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.Student;

public class StudentDAO {

    // Get Connection from DataSource (via JNDI)
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
        conn = ds.getConnection();
        return conn;
    }

    // Save student to database
    public static int saveStudent(Student std) {
        String query = "INSERT INTO students(student_name, student_addr, student_age, student_qual, student_percent, student_year_passed) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, std.getStudentName());
            ps.setString(2, std.getStudentAddr());
            ps.setInt(3, Integer.parseInt(std.getAge()));  // Convert to int
            ps.setString(4, std.getQualification());
            ps.setDouble(5, Double.parseDouble(std.getPercentage()));  // Convert to double
            ps.setInt(6, Integer.parseInt(std.getYearPassed()));  // Convert to int
            
            return ps.executeUpdate();  // Return number of rows affected

        } catch (Exception ex) {
            ex.printStackTrace();  // Consider proper logging here
        }
        return 0;
    }

    // Update student data in the database
    public static int updateStudent(Student std) {
        String query = "UPDATE students SET student_name=?, student_addr=?, student_age=?, student_qual=?, student_percent=?, student_year_passed=? WHERE student_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, std.getStudentName());
            ps.setString(2, std.getStudentAddr());
            ps.setInt(3, Integer.parseInt(std.getAge()));  // Convert to int
            ps.setString(4, std.getQualification());
            ps.setDouble(5, Double.parseDouble(std.getPercentage()));  // Convert to double
            ps.setInt(6, Integer.parseInt(std.getYearPassed()));  // Convert to int
            ps.setInt(7, std.getStudentId());  // Set student_id for WHERE clause
            
            return ps.executeUpdate();  // Return number of rows affected

        } catch (Exception ex) {
            ex.printStackTrace();  // Consider proper logging here
        }
        return 0;
    }

    // Delete student from the database
    public static int deleteStudent(int stdId) {
        String query = "DELETE FROM students WHERE student_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, stdId);
            return ps.executeUpdate();  // Return number of rows affected

        } catch (Exception ex) {
            ex.printStackTrace();  // Consider proper logging here
        }
        return 0;
    }

    // Get student by ID from the database
    public static Student getStudentById(int StdId) {
        String query = "SELECT * FROM students WHERE student_id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, StdId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentName(rs.getString("student_name"));
                    student.setStudentAddr(rs.getString("student_addr"));
                    student.setAge(rs.getString("student_age"));
                    student.setQualification(rs.getString("student_qual"));
                    student.setPercentage(rs.getString("student_percent"));
                    student.setYearPassed(rs.getString("student_year_passed"));
                    return student;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();  // Consider proper logging here
        }
        return null;  // Return null if no student found
    }

    // Get all students from the database
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentAddr(rs.getString("student_addr"));
                student.setAge(rs.getString("student_age"));
                student.setQualification(rs.getString("student_qual"));
                student.setPercentage(rs.getString("student_percent"));
                student.setYearPassed(rs.getString("student_year_passed"));
                students.add(student);
            }
        } catch (Exception ex) {
            ex.printStackTrace();  // Consider proper logging here
        }
        return students;
    }
}
