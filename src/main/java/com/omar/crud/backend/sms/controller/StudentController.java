package com.omar.crud.backend.sms.controller;

import com.omar.crud.backend.sms.entity.Student;
import com.omar.crud.backend.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RestController
@CrossOrigin(origins= "https://localhost:3000")
@RequestMapping(path = "api")
public class StudentController {

        private StudentService studentService;

        @Autowired
        public StudentController(StudentService studentService) {
            this.studentService = studentService;
        }

    // Listing all students...
        @GetMapping("/all")
        @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
        public CompletableFuture<List<Student>> listStudents() {
            return studentService.getAllStudents();
        }

    // Getting student by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    //Adding student
    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    //Editing a student
    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student editStudent(@RequestBody Student student,@PathVariable Long id) {
        return studentService.updateStudent(student, id);
    }


    //Deleting a student
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

//    @PostMapping("/students")
//        public String saveStudent(@ModelAttribute("student") Student student) {
//            studentService.saveStudent(student);
//            return "redirect:/students";
//        }
//
//        @GetMapping("/students/edit/{id}")
//        public String editStudentForm(@PathVariable Long id, Model model) {
//            model.addAttribute("student", studentService.getStudentById(id));
//            return "edit_student";
//        }
//
//        @PostMapping("/students/{id}")
//        public String updateStudent(@PathVariable Long id,
//                                    @ModelAttribute("student") Student student,
//                                    Model model) {
//
//            // get student from database by id
//            Student existingStudent = studentService.getStudentById(id);
//            existingStudent.setId(id);
//            existingStudent.setName(student.getName());
//            existingStudent.setMarks(student.getMarks());
//
//            // save updated student object
//            studentService.updateStudent(existingStudent);
//            return "redirect:/students";
//        }
//
//        // handler method to handle delete student request
//
//        @GetMapping("/students/{id}")
//        public String deleteStudent(@PathVariable Long id) {
//            studentService.deleteStudentById(id);
//            return "redirect:/students";
//        }

}
