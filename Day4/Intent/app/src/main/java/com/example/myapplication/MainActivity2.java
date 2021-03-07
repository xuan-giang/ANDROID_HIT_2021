package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView tvName, tvAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AnhXa();
        Intent intent = getIntent();
        List<Person> list = new ArrayList<>();
//        String name = intent.getStringExtra("name");
//        int age = intent.getIntExtra("age", 0);
  //        Person p = intent.getParcelableExtra("object");
//        tvName.setText(name);
//        tvAge.setText(String.valueOf(age));
//        tvName.setText(p.getName());
//        tvAge.setText(String.valueOf(p.getAge()));
        list = intent.getParcelableArrayListExtra("List");
        for(Person x : list){
            Log.d("list", x.toString());
        }
    }

    private void AnhXa(){
        tvName = (TextView) findViewById(R.id.tvName);
        tvAge = (TextView) findViewById(R.id.tvAge);
    }
}