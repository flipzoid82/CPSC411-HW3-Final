package com.cpsc411.homework3.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cpsc411.homework3.R;
import com.cpsc411.homework3.StudentDetailsActivity;
import com.cpsc411.homework3.model.Student;
import com.cpsc411.homework3.model.StudentDB;

public class SummaryListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return StudentDB.getStudentList().size();
    }

    @Override
    public Object getItem(int i) {
        return StudentDB.getStudentList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;

        final Student s = StudentDB.getStudentList().get(i);

        ((TextView) row_view.findViewById(R.id.first_name)).setText(s.getFirstName());
        ((TextView) row_view.findViewById(R.id.last_name)).setText(s.getLastName());
        row_view.setTag((new Integer(i)));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(v.getContext(), "User Selected " + s.getFirstName(), Toast.LENGTH_SHORT).show();

                        // Nav
                        Intent intent = new Intent(v.getContext(), StudentDetailsActivity.class);
                        intent.putExtra("StudentIndex", ((Integer)v.getTag()).intValue());
                        v.getContext().startActivity(intent);
                    }
                }
        );
        return row_view;
    }
}
