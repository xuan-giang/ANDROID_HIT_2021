package com.example.hit_practicerecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataCountryAdapter dataCountryAdapter;
    ConstraintLayout rootLayer;
    FloatingActionButton flagSwicher;
    Boolean isDark = false;
    String url = "https://ncov.trungbt.xyz/countries";
    ProgressBar progressBar;
    int progressBarStatus = 0;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mainScr = findViewById(R.id.mainScreen);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        flagSwicher = (FloatingActionButton) findViewById(R.id.flag_swicher);
        rootLayer = (ConstraintLayout) findViewById(R.id.mainScreen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus<100){
                    progressBarStatus++;
                    SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(progressBarStatus == 100){
                                isDark = getThemeSatePref();
                                if(isDark){
                                    rootLayer.setBackgroundColor(getResources().getColor(R.color.black));
                                }else {
                                    rootLayer.setBackgroundColor(getResources().getColor(R.color.white));
                                }
                                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        initView(response);
                                        Toast.makeText(MainActivity.this, "Cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Cập nhật dữ liệu thất bại, vui lòng thử lại sau!", Toast.LENGTH_LONG).show();
                                    }
                                });
                                requestQueue.add(jsonArrayRequest);
                            }
                        }
                    });
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        }).start();

    }
    public void initView(JSONArray jsonArray){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<DataCountry> list = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String Country_Region = jsonObject.getString("Country_Region");
                String Confirmed = jsonObject.getString("Confirmed");
                String Deaths = jsonObject.getString("Deaths");
                String Recovered = jsonObject.getString("Recovered");
                list.add(new DataCountry(R.drawable.vr,Country_Region, Confirmed, Deaths, Recovered));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        dataCountryAdapter = new DataCountryAdapter(list, MainActivity.this, isDark);
        recyclerView.setAdapter(dataCountryAdapter);
        flagSwicher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDark = !isDark;
                if(isDark){
                    rootLayer.setBackgroundColor(getResources().getColor(R.color.black));
                }else {
                    rootLayer.setBackgroundColor(getResources().getColor(R.color.white));
                }
                dataCountryAdapter = new DataCountryAdapter(list, MainActivity.this, isDark);
                recyclerView.setAdapter(dataCountryAdapter);
                saveThemeSatePref(isDark);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                dataCountryAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    private void saveThemeSatePref(boolean isDark){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark", isDark);
        editor.commit();
    }
    private boolean getThemeSatePref(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        Boolean isDark = pref.getBoolean("myPref", false);
        return  isDark;
    }
}