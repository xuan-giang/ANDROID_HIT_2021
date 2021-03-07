package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btnSubmit, btnAction;
    String name;
    int age;
    List<Person> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        list.add(new Person("Nguyễn Xuân Giang", 19));
        list.add(new Person("Nguyễn Xuân Giang", 20));
        list.add(new Person("Nguyễn Xuân Giang", 21));
        list.add(new Person("Nguyễn Xuân Giang", 22));
        list.add(new Person("Nguyễn Xuân Giang", 23));
        list.add(new Person("Nguyễn Xuân Giang", 98));
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt1.getText().toString();
                age = Integer.parseInt(edt2.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                Person p = new Person("Nguyễn Xuân Giang", 19);
                intent.putExtra("object", (Parcelable) p);

                intent.putParcelableArrayListExtra("List", (ArrayList<? extends Parcelable>) list);
                startActivity(intent);
            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                Intent intent1 = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(intent1);
            }
        });
    }

    private void AnhXa(){
        edt1 = (EditText) findViewById(R.id.edtName);
        edt2 = (EditText) findViewById(R.id.edtAge);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnAction = (Button) findViewById(R.id.btnAction);
    }
}