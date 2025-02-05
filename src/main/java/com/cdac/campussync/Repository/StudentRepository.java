package com.cdac.campussync.Repository;

import com.cdac.campussync.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByEnrolledCourseIsNull();
}
