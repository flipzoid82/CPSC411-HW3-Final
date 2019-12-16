package com.cpsc411.homework3.model;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB extends Activity {

    File database;
    private  static SQLiteDatabase sqlDB;

    public StudentDB(Context context) {
        database = context.getDatabasePath("student.db");
        sqlDB = SQLiteDatabase.openOrCreateDatabase(database, null);

        String sqlQueue = "CREATE TABLE IF NOT EXISTS Student (firstName TEXT, lastName TEXT, cwid TEXT)";
        sqlDB.execSQL(sqlQueue);

        sqlQueue = "CREATE TABLE IF NOT EXISTS Courses (cwid TEXT, course TEXT, grade TEXT)";
        sqlDB.execSQL(sqlQueue);
    }

    public static ArrayList<Student> getStudentList() {
        ArrayList<Student> studentList = new ArrayList<>();
        Student student;
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        //Cursor used to go through the student table
        Cursor curr = sqlDB.query("Student", null, null, null, null, null, null);

        if (curr.getCount() > 0) { //if there is something in the table do this...
            while (curr.moveToNext()) { //iterate through all of the rows
                //..in the row that curr is on get the string stored at 0,1,2 index which in this case is the firstName, lastName, and CWID
                student = new Student(curr.getString(0), curr.getString(1), curr.getString(2));

                Cursor curr2 = sqlDB.query("Courses", null,"CWID=?", new String[]{curr.getString(2)}, null, null, null);

                if(curr2.getCount() > 0) {
                    courses = new ArrayList<>();
                    while (curr2.moveToNext()) {
                        courses.add(new CourseEnrollment(curr2.getString(1), curr2.getString(2)));
                    }
                }
                student.setCourses(courses);
                studentList.add(student);
            }
        }
        return studentList;
    }

    public static void addListToDB(ArrayList<Student> studentList) {
        //Go through all of the students in the studentList and insert it to the Student table
        for (Student student:studentList) {
            sqlDB.execSQL("INSERT INTO Student VALUES (?,?,?)", new String[]{student.getFirstName(), student.getLastName(), student.getCWID()});

            //Go through all of the courses within a student's courses which is an ArrayList<CourseEnrollment>
            for (CourseEnrollment course:student.getCourses()) {
                sqlDB.execSQL("INSERT INTO Courses VALUES (?,?,?)", new String[]{student.getCWID(), course.getCourseId(), course.getGrade()});
            }
        }
    }
}
