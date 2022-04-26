package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class RandomQ extends AppCompatActivity {
    RequestQueue queue;
    String Question;
    String Answer;
    TextView QV;
    TextView AV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_q);
        queue = Volley.newRequestQueue(this);

        ImageView cancelBtn = findViewById(R.id.cacel_btn);
        ImageView saveBtn = findViewById(R.id.save_btn);
        QV = findViewById(R.id.QV);
        AV = findViewById(R.id.AV);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        callAPI();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("Question", Question);
                data.putExtra("Answer", Answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

    private void callAPI(){
        String url = "https://opentdb.com/api.php?amount=1&category=21&difficulty=easy&type=multiple&encode=url3986";
        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        JSONObject myObj = (JSONObject) response;

                        String myObjString = myObj.toString();
                        Log.d("Obj", myObjString);

                        try {
                            //This is working fine (Still need to know how)
                            //need to set the global Q ad global A to the question and
                            //answer we got from the API

                            JSONArray Results_array = myObj.getJSONArray("results");
                            JSONObject Results = (JSONObject) Results_array.get(0);

                            String Question = Results.getString("question");
                            String Answer = Results.getString("correct_answer");

                            Question = URLDecoder.decode(Question, "UTF-8");
                            Answer = URLDecoder.decode(Answer, "UTF-8");


                            QV.setText(Question);
                            AV.setText(Answer);

                            Log.d("my_arr", Question);

                        } catch (JSONException | UnsupportedEncodingException e) {
                            //show an error to the user inidcating that something went wrong
                            Log.d("2", "Failed to get array");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("Check", "Failure");
                    }
                });
        queue.add(jsonObjectRequest);
    }
}