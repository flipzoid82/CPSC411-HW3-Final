package com.cpsc411.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpsc411.homework3.model.Student;
import com.cpsc411.homework3.model.StudentDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout root;

    StudentDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new StudentDB(this);

        setContentView(R.layout.student_list);
        root = findViewById(R.id.student_list);

        ArrayList<Student> studentList = db.getStudentList();
        for(int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);

            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.student_row, root, false);

            ((TextView) row_view.findViewById(R.id.first_name)).setText(s.getFirstName());
            ((TextView) row_view.findViewById(R.id.last_name)).setText(s.getLastName());
            root.addView(row_view);
        }
    }
}
