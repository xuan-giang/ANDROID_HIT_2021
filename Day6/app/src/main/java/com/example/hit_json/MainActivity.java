package com.example.hit_json;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String json = "{\"id\":4,\"username\":\"abc4\",\"password\":\"xyz\"}";
    String array = "[{\"id\":4,\"username\":\"abc4\",\"password\":\"xyz\"},{\"id\":5,\"username\":\"abc5\",\"password\":\"xyz\"},{\"id\":2,\"username\":\"abc2\",\"password\":\"xyz1\"},{\"id\":7,\"username\":\"aaaaaaaaa\",\"password\":\"hihi\"},{\"id\":20,\"username\":\"TinhMercedes\",\"password\":\"E để con pet ở đây hóng chuyện\"},{\"id\":34,\"username\":\"TinhMercedes02\",\"password\":\"Xong việc lát e qua đón, mọi người đừng đuổi nó đi nhé!!\"},{\"id\":40,\"username\":\"Đẹp trai\",\"password\":\"okeee\"},{\"id\":8,\"username\":\"1\",\"password\":\"em nghịch chút nhá\"},{\"id\":41,\"username\":\"Anh Trung vip\",\"password\":\"kkkkkkk\"}]";
    String weather = "{\n" +
            "  \"coord\": {\n" +
            "    \"lon\": -0.13,\n" +
            "    \"lat\": 51.51\n" +
            "  },\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 300,\n" +
            "      \"main\": \"Drizzle\",\n" +
            "      \"description\": \"light intensity drizzle\",\n" +
            "      \"icon\": \"09d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"base\": \"stations\",\n" +
            "  \"main\": {\n" +
            "    \"temp\": 280.32,\n" +
            "    \"pressure\": 1012,\n" +
            "    \"humidity\": 81,\n" +
            "    \"temp_min\": 279.15,\n" +
            "    \"temp_max\": 281.15\n" +
            "  }\n" +
            "}";
    String url = "https://demo-b5.herokuapp.com/api/accounts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONArray jsonArray = new JSONArray(array);
//            int id = jsonObject.getInt("id");
//            String username = jsonObject.getString("username");
//            String password = jsonObject.getString("password");
//            Account account = new Account(id, username, password);
//            List<Account> list = new ArrayList<>();
//
//            Log.d("TAG1", "onCreate: " + account.getId() + " " + account.getUsername() + " " + account.getPassworđ());
//
//            for(int i = 0; i < jsonArray.length(); i++){
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                int id1 = jsonObject1.getInt("id");
//                String username1 = jsonObject1.getString("username");
//                String password1 = jsonObject1.getString("password");
//                list.add(new Account(id1, username1, password1));
//                Log.d("TAG2", "onCreate: " + id1 + " " + username1 + " " + password1);
//            }
//            JSONObject jsonObject = new JSONObject(weather);
//            JSONObject jsonObject1 = jsonObject.getJSONObject("coord");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        Button btnGet = (Button) findViewById(R.id.btnGet);
        Button btnPost = (Button) findViewById(R.id.btnPost);
        Button btnPut = (Button) findViewById(R.id.btnPut);
        Button btnDel = (Button) findViewById(R.id.btnSet);
        TextView tvShow = (TextView) findViewById(R.id.tvShow);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvShow.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvShow.setText("Lỗi rồi!");
                    }
                });
                requestQueue.add(stringRequest);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", "test2");
                    jsonObject.put("password", "nhgngfdfd");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestBody = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvShow.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvShow.setText("Lỗi rồi");
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(requestBody==null){
                            return null;
                        }else {
                            try {
                                return requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("password", "editpassword");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String requestBody = jsonObject.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url+"/43", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvShow.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvShow.setText("Lỗi rồi");
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset = utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        if(requestBody==null){
                            return null;
                        }else {
                            try {
                                return requestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url + "/43", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvShow.setText("Đã xoá" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvShow.setText("Lỗi rồi");
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
   }
}