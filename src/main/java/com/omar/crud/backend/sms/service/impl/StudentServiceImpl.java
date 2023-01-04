package com.omar.crud.backend.sms.service.impl;

import com.omar.crud.backend.sms.entity.Student;
import com.omar.crud.backend.sms.repository.StudentRepository;
import com.omar.crud.backend.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
 @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = EntityNotFoundException.class)
 public class StudentServiceImpl implements StudentService {

        @Autowired
        private StudentRepository studentRepository;

        public StudentServiceImpl(StudentRepository studentRepository) {
            this.studentRepository = studentRepository;
        }

        @Override
        public CompletableFuture<List<Student>> getAllStudents() {
            return CompletableFuture.completedFuture(studentRepository.findAll());
        }

        @Override
        public Student getStudentById(Long id) {
            Optional<Student> op_student = studentRepository.findById(id);
            return op_student.get();
        }

        @Override
        public Student saveStudent(Student student) {
            return studentRepository.save(student);
        }



        @Override
        public Student updateStudent(Student newStudent, Long id) {
            return studentRepository.findById(id)
            .map(student -> {
                student.setName(newStudent.getName());
                student.setMarks(newStudent.getMarks());

                return studentRepository.save(student);
            })
                    .orElseGet(() -> {
                        newStudent.setId(id);
                        return studentRepository.save(newStudent);
                    });
        }

        @Override
        public void deleteStudent(Long id) {
            studentRepository.deleteById(id);
        }
}
