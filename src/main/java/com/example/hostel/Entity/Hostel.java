package com.example.hostel.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hostel")
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key for Hostel table

    private int blockNumber;
    private int roomNumber;

    // relation to Student
    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "student_id", referencedColumnName = "id") // FK in hostel table
    private Student student;
    
}
