package com.omar.crud.backend.sms.service;

import com.omar.crud.backend.sms.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StudentService {
    //Displaying list of all students
    public CompletableFuture<List<Student>> getAllStudents();

    //Adding new student
    Student saveStudent(Student student);

    //Find particular student
    Student getStudentById(Long id);

    //Edit specific student information
    Student updateStudent(Student student, Long id);

    //Delete a student from the database
    void deleteStudent(Long id);
}
