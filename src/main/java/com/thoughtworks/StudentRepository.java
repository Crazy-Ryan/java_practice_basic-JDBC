package com.thoughtworks;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public void save(List<Student> students) {
        students.forEach(this::save);
    }

    public void save(Student student) {
        // TODO:
        Connection connection = DatabaseUtil.getConnection();
        try {
            String sqlCmd = "INSERT INTO student_info" +
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
        return new ArrayList<>();
    }

    public List<Student> queryByClassId(String classId) {
        // TODO:
        return new ArrayList<>();
    }

    public void update(String id, Student student) {
        // TODO:
    }

    public void delete(String id) {
        // TODO:
    }
}
