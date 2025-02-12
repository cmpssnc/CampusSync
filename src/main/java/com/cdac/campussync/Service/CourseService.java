package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    // constructor autowiring
    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // retrieve and return all courses from the database
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Find a course by its ID
    public Course getCourseById(Long courseId) {
        try {
            return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        } catch (RuntimeException e) {
            return null;
        }
    }

    // Create or update a course
    public boolean saveCourse(Course course) {
        try {
            courseRepository.save(course);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // find a course by its name
    public Course getCourseByName(String courseName) {
        try{
            return courseRepository.findByCourseName(courseName);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
