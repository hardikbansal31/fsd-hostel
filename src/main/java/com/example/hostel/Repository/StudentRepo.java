package com.example.hostel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostel.Entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
    
}
