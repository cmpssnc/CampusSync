package com.cdac.campussync.Repository;

import com.cdac.campussync.Entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
