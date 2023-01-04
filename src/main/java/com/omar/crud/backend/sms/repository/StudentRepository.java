package com.omar.crud.backend.sms.repository;

import com.omar.crud.backend.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
