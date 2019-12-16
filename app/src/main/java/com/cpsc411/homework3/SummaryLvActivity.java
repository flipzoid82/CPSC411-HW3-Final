package com.cpsc411.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.homework3.adapter.SummaryListAdapter;
import com.cpsc411.homework3.model.StudentDB;

public class SummaryLvActivity extends AppCompatActivity {
    protected ListView mSummaryView;
    protected SummaryListAdapter ad;
    protected Menu summaryMenu;
    StudentDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new StudentDB(this);

        setContentView(R.layout.student_list_lv);

        mSummaryView = findViewById(R.id.student_list_id);
        ad = new SummaryListAdapter();
        mSummaryView.setAdapter(ad);

    }

    @Override
    protected void onStart() {
        ad.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.summary_screen_menu, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        menu.findItem(R.id.action_submit).setVisible(false);
        summaryMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, StudentAddActivity.class);
            startActivity(intent);
            item.setVisible(false);
        } else if (item.getItemId() == R.id.action_submit) {
            Intent intent = new Intent(getBaseContext(), SummaryLvActivity.class);
            startActivity(intent);
            item.setVisible(false);
            summaryMenu.findItem(R.id.action_add).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
