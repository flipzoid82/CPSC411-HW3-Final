package com.cpsc411.homework3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.homework3.model.CourseEnrollment;
import com.cpsc411.homework3.model.Student;
import com.cpsc411.homework3.model.StudentDB;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {
    protected Student sObj;
    protected int studentIndx;
    protected Menu detailMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_details);

        studentIndx = getIntent().getIntExtra("StudentIndex", 0);

        // Retrieve data
        sObj = StudentDB.getStudentList().get(studentIndx);

        // Display first name
        EditText editView = findViewById(R.id.p_fname_id);
        editView.setText(sObj.getFirstName());
        editView.setEnabled(false);

        // Display last name
        editView = findViewById(R.id.p_lname_id);
        editView.setText(sObj.getLastName());
        editView.setEnabled(false);

        // Display CWID
        TextView textView = findViewById((R.id.p_cwid_id));
        textView.setText(sObj.getCWID().toString());
        textView.setEnabled(false);

        // Display List of Courses in detail screen
        ArrayList<CourseEnrollment> courseList = sObj.getCourses();

        for(int i = 0; i < sObj.getCourses().size(); i++) {

            CourseEnrollment c = courseList.get(i);

            TextView tv = findViewById(R.id.p_course_name_id);
            tv.append(c.getCourseId());
            tv.append("\n");
            tv.setEnabled(false);

            tv = findViewById(R.id.p_course_grade_id);
            tv.append(c.getGrade());
            tv.append("\n");
            tv.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);
        detailMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            EditText editView = findViewById(R.id.p_fname_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.p_lname_id);
            editView.setEnabled(true);

            item.setVisible(false);
            detailMenu.findItem(R.id.action_done).setVisible(true);
        } else if (item.getItemId() == R.id.action_done) {
            EditText editView = findViewById(R.id.p_fname_id);
            StudentDB.getStudentList().get(studentIndx).setFirstName(editView.getText().toString());
            editView.setEnabled(false);

            editView = findViewById(R.id.p_lname_id);
            StudentDB.getStudentList().get(studentIndx).setLastName(editView.getText().toString());
            editView.setEnabled(false);

            item.setVisible(false);
            detailMenu.findItem(R.id.action_edit).setVisible(true);
        }

        return super.onOptionsItemSelected(item);
    }
}
