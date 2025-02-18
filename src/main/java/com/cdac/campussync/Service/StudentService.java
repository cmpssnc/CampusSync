package com.cdac.campussync.Service;

import com.cdac.campussync.Entity.Course;
import com.cdac.campussync.Entity.Student;
import com.cdac.campussync.Enum.Gender;
import com.cdac.campussync.Repository.CourseRepository;
import com.cdac.campussync.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public boolean saveStudent(Student student) {
        try {
            studentRepository.save(student);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Fetch all students
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    // Find all students who don't have a course assigned (course_id is null)
    public List<Student> findStudentsWithNoCourse() {
        return studentRepository.findByEnrolledCourseIsNull();
    }

    // find student by id
    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Optional<Student> updateStudent(Long studentId, Map<String, Object> updates) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        student.setName((String) value);
                        break;
                    case "dob":
                        student.setDateOfBirth(LocalDate.parse((String) value)); // Ensure proper date parsing
                        break;
                    case "contactNumber":
                        student.setContactNumber((String) value);
                        break;
                    case "gender":
                        student.setGender(Gender.valueOf((String) value)); // Ensure valid enum conversion
                        break;
                    case "address":
                        student.setAddress((String) value);
                        break;
                    case "enrolledCourseId":
                        Course course = courseRepository.findById(Long.parseLong(value.toString())).orElse(null);
                        student.setEnrolledCourse(course);
                        break;
                    default:
                        break;
                }
            });

            studentRepository.save(student);
            return Optional.of(student);
        }
        return Optional.empty();
    }


    // Delete a student by id
    public boolean deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true; // Successfully deleted
        }
        return false; // Student not found
    }

    // find students with a name search query
    public List<Student> findStudentsWithNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    // finds all students who are enrolled in a particular course
    public List<Student> findStudentsByEnrolledCourse(Course course) {
        return studentRepository.findByEnrolledCourse(course);
    }

    // finds all students who are enrolled in the course and who's names contain the provided name string
    public List<Student> findStudentsByEnrolledCourseAndNameContaining(Course course, String name) {
        return studentRepository.findByEnrolledCourseAndNameContaining(course, name);
    }
}


