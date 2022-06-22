package kz.galymbay.coursesite.service;

import kz.galymbay.coursesite.dto.Course;
import kz.galymbay.coursesite.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }

    public Course updateCourse(Course course, Long id) {
        Course updatedCourse = findById(id);

        updatedCourse.setName(course.getName());
        updatedCourse.setDescription(course.getDescription());
        courseRepository.save(updatedCourse);

        return updatedCourse;
    }

    public Course deleteById(Long id) {
        Course course = findById(id);
        courseRepository.deleteById(id);

        return course;
    }
}
