package kz.galymbay.coursesite.controller;

import kz.galymbay.coursesite.dto.Mentor;
import kz.galymbay.coursesite.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<Mentor>> getAll() {
        return ResponseEntity.ok().body(mentorService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Mentor> saveMentor(@RequestBody Mentor mentor) {
        return ResponseEntity.ok().body(mentorService.saveMentor(mentor));
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<Mentor> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mentorService.findById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Mentor> updateMentor(@RequestBody Mentor mentor, @PathVariable Long id) {
        return ResponseEntity.ok().body(mentorService.updateMentor(mentor, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mentor> deleteMentor(@PathVariable Long id) {
        return ResponseEntity.ok().body(mentorService.deleteById(id));
    }
}
