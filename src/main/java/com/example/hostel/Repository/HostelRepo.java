package com.example.hostel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.Entity.Student;
import com.example.hostel.Entity.Hostel;
import com.example.hostel.Entity.Student;

public interface HostelRepo extends JpaRepository<Hostel, Long> {
    Hostel findByStudent(Student student);

}