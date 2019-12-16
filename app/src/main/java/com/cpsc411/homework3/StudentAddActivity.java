package com.cpsc411.homework3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.homework3.model.CourseEnrollment;
import com.cpsc411.homework3.model.Student;
import com.cpsc411.homework3.model.StudentDB;

import java.util.ArrayList;
import java.util.List;

public class StudentAddActivity extends AppCompatActivity {
    StudentDB database;

    EditText fname;
    EditText lname;
    EditText cwid;
    EditText course;
    EditText courseGrade;
    List<String[]> courses;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new StudentDB(this);

        setContentView(R.layout.blank_screen_menu);

        fname = findViewById(R.id.add_fname_id);
        lname = findViewById(R.id.add_lname_id);
        cwid = findViewById(R.id.add_cwid_id);
        course = findViewById(R.id.add_course_name);
        courseGrade = findViewById(R.id.add_course_grade);
        courses = new ArrayList<String[]>();

        Button addC = findViewById(R.id.add_course_btn);
        addC.setOnClickListener(new View.OnClickListener() {
            int r = 0;

            @Override
            public void onClick(View v) {
                if(course.getText().toString() != "" && courseGrade.getText().toString() != "") {
                    courses.add(new String[] {course.getText().toString(), courseGrade.getText().toString()});
                    Toast.makeText(getApplicationContext(), course.getText().toString() + " and " + courseGrade.getText().toString() + " added", Toast.LENGTH_LONG).show();
                    course.setText("");
                    courseGrade.setText("");
                    r++;
                } else {
                    Toast.makeText( getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.summary_screen_menu, menu);
        menu.findItem(R.id.action_submit).setVisible(true);
        menu.findItem(R.id.action_add).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_submit) {
            ArrayList<Student> studentList = new ArrayList<Student>();
            ArrayList<CourseEnrollment> courseList = new ArrayList<>();
            Student tempStu = new Student(fname.getText().toString(), lname.getText().toString(), cwid.getText().toString());
            for(String[]course : courses) {
                courseList.add(new CourseEnrollment(course[0],course[1]));
            }
            tempStu.setCourses(courseList);
            studentList.add(tempStu);
            database.addListToDB(studentList);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
