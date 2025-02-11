package com.cdac.campussync.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Teacher extends User {

    // One teacher can teach multiple subjects
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjectsTaught;

    // getter, setter, constructor
    public Teacher() {
    }

    public Teacher(List<Subject> subjectsTaught) {
        this.subjectsTaught = subjectsTaught;
    }

    public List<Subject> getSubjectsTaught() {
        return subjectsTaught;
    }

    public void setSubjectsTaught(List<Subject> subjectsTaught) {
        this.subjectsTaught = subjectsTaught;
    }
}