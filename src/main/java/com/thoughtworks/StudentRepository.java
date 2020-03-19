package com.thoughtworks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public static final String TABLE_NAME = "student_info";

    public void save(List<Student> students) {
        students.forEach(this::save);
    }

    public void save(Student student) {
        // TODO:
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlCmd = "INSERT INTO " + TABLE_NAME +
                    "(id,full_name,gender,admission_year,birthday,classId)" +
                    "values(" + "?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCmd); //预编译SQL，减少sql执行
            preparedStatement.setInt(1, Integer.parseInt(student.getId()));
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAdmissionYear() + "");
            preparedStatement.setDate(5, new Date(student.getBirthday().getTime()));
            preparedStatement.setString(6, student.getClassId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> query() {
        // TODO:
        List<Student> studentList = new ArrayList<>();
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlQuery = "SELECT " +
                    "id,full_name,gender,admission_year,birthday,classId " +
                    "FROM " + TABLE_NAME;
            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
            while(resultSet.next()){
                studentList.add(new Student(
                        resultSet.getInt("id")+"",
                        resultSet.getString("full_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("admission_year"),
                        resultSet.getDate("birthday").toString(),
                        resultSet.getString("classId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Student> queryByClassId(String classId) {
        // TODO:
        List<Student> studentList = new ArrayList<>();
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlQuery = "SELECT " +
                    "id,full_name,gender,admission_year,birthday,classId " +
                    "FROM " + TABLE_NAME +
                    " WHERE classId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,classId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                studentList.add(new Student(
                        resultSet.getInt("id")+"",
                        resultSet.getString("full_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("admission_year"),
                        resultSet.getDate("birthday").toString(),
                        resultSet.getString("classId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void update(String id, Student student) {
        // TODO:
    }

    public void delete(String id) {
        // TODO:
    }
}
