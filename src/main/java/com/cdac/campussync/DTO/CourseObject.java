package com.cdac.campussync.DTO;

public class CourseObject {
    private long courseId;
    private String courseName;
    private String courseDescription;

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public CourseObject(long courseId, String courseName, String courseDescription) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
