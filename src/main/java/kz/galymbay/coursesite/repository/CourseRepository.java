package kz.galymbay.coursesite.repository;

import kz.galymbay.coursesite.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
