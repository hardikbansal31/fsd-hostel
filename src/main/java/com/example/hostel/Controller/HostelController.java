package com.example.hostel.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.hostel.Entity.Hostel;
import com.example.hostel.Entity.Student;
import com.example.hostel.Repository.HostelRepo;
import com.example.hostel.Repository.StudentRepo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hostel")
public class HostelController {

    private final HostelRepo hostelRepo;
    private final StudentRepo studentRepo;
    private static final Logger logger = LoggerFactory.getLogger(HostelController.class);

    public HostelController(HostelRepo hostelRepo, StudentRepo studentRepo) {
        this.hostelRepo = hostelRepo;
        this.studentRepo = studentRepo;
    }
    
    @GetMapping("/logger")
    public List<Student> getName() {
        logger.info("success INFO");
        logger.debug("fetching all users DEBUG");
        logger.error("fialed ERROR");
        logger.warn("WARN");
        logger.trace("TRACE");
        return studentRepo.findAll();
    }

    @GetMapping("/")
    public List<Hostel> getAllHostels() {
        return hostelRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id) {
        return hostelRepo.findById(id)
                .map(hostel -> ResponseEntity.ok(hostel)) 
                .orElse(ResponseEntity.notFound().build()); 
    }

    @PostMapping("/create") // create empty room, or with student
    public Hostel createHostel(@RequestBody Hostel newHostel) {
        return hostelRepo.save(newHostel);
    }

    @PutMapping("/{id}") // edit the hostel room itself
    public ResponseEntity<Hostel> updateHostel(@PathVariable Long id, @RequestBody Hostel hostelDetails) {
        return hostelRepo.findById(id)
                .map(existingHostel -> {
                    existingHostel.setBlockNumber(hostelDetails.getBlockNumber());
                    existingHostel.setRoomNumber(hostelDetails.getRoomNumber());
                    existingHostel.setStudent(hostelDetails.getStudent());
                    Hostel updatedHostel = hostelRepo.save(existingHostel);
                    return ResponseEntity.ok(updatedHostel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/empty") // remove student from room, required for deleting room itself
    public ResponseEntity<Hostel> emptyRoom(@PathVariable Long id) {
        return hostelRepo.findById(id)
                .map(hostel -> {
                    hostel.setStudent(null);
                    Hostel updatedHostel = hostelRepo.save(hostel);
                    return ResponseEntity.ok(updatedHostel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // delete room itself
    public ResponseEntity<Void> deleteHostel(@PathVariable Long id) {
        return hostelRepo.findById(id)
                .map(hostel -> {
                    hostelRepo.delete(hostel);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
