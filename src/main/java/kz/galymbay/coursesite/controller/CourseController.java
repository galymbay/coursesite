package kz.galymbay.coursesite.controller;

import kz.galymbay.coursesite.dto.Course;
import kz.galymbay.coursesite.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok().body(courseService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return ResponseEntity.ok().body(courseService.saveCourse(course));
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.updateCourse(course, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.deleteById(id));
    }
}
