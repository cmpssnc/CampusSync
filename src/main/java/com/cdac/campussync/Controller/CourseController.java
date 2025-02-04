package com.cdac.campussync.Controller;


import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    // constructor autowiring
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // return all courses in the database when there is a request on the endpoint: /api/courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        System.out.println("url Hit");
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courses);
    }

    // return the course with the provided id when there is a request on the endpoint: /api/courses/:course_id
    @GetMapping("/{course_id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long course_id) {
        Course course = courseService.getCourseById(course_id);
        if(course != null) {
            return ResponseEntity.status(200).body(course);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        boolean success = courseService.saveCourse(course);

        if(success) {
            return ResponseEntity.status(201).body("Course created");
        } else {
            return ResponseEntity.status(400).body("Course not created. Invalid Input Data.");
        }
    }
}
