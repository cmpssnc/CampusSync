package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

//    // Create or update a course
//    public Course saveCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    // Find a course by its ID
//    public Course getCourseById(Long courseId) {
//        return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
//    }

    // Other course-related business logic can be added here
}
