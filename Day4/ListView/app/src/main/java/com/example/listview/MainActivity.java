package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcStudent;
    List<Student> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        list.add(new Student(R.drawable.ic_student, "Admin", "Giang"));
        list.add(new Student(R.drawable.ic_student, "Tiên tri", "Duy"));
        list.add(new Student(R.drawable.ic_student, "Phù thuỷ", "Ngọc"));
        list.add(new Student(R.drawable.ic_student, "Sói", "Bích"));
        list.add(new Student(R.drawable.ic_student, "Bảo vệ", "Ngô Hiền"));

        StudentAdapter adapter = new StudentAdapter(list, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        rcStudent.setLayoutManager(layoutManager);
        rcStudent.setAdapter(adapter);
    }
    private void AnhXa() {
        rcStudent =(RecyclerView) findViewById(R.id.rcStudent);
    }
}