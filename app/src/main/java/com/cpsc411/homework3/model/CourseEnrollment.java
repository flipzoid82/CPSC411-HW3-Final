package com.cpsc411.homework3.model;

public class CourseEnrollment {
    protected String mCourseId;
    protected String mGrade;

    public CourseEnrollment(String courseId, String grade) {
        mCourseId = courseId;
        mGrade = grade;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String courseId) {
        mCourseId = courseId;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }
}
