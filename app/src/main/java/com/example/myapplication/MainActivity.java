package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Question = findViewById(R.id.flashcard_question);
        TextView Answer = findViewById(R.id.flashcard_answer);
        TextView Op1 = findViewById(R.id.option1);
        TextView Op2 = findViewById(R.id.option2);
        TextView Op3 = findViewById(R.id.option3);
        ImageView On_eye = findViewById(R.id.on_eye);
        ImageView Off_eye = findViewById(R.id.off_eye);


        Question.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Question.setVisibility(View.INVISIBLE);
                Answer.setVisibility(View.VISIBLE);
            }
        });

        Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer.setVisibility(View.INVISIBLE);
                Question.setVisibility(View.VISIBLE);
            }
        });

        Op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Op1.setBackgroundColor(getResources().getColor(R.color.red));
                Op2.setBackgroundColor(getResources().getColor(R.color.Chai));
                Op3.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        Op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Op1.setBackgroundColor(getResources().getColor(R.color.Chai));
                Op2.setBackgroundColor(getResources().getColor(R.color.red));
                Op3.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        Op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Op1.setBackgroundColor(getResources().getColor(R.color.Chai));
                Op2.setBackgroundColor(getResources().getColor(R.color.Chai));
                Op3.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        On_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                On_eye.setVisibility(View.INVISIBLE);
                Off_eye.setVisibility(View.VISIBLE);

                Op1.setVisibility(View.VISIBLE);
                Op2.setVisibility(View.VISIBLE);
                Op3.setVisibility(View.VISIBLE);
            }
        });

        Off_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Off_eye.setVisibility(View.INVISIBLE);
                On_eye.setVisibility(View.VISIBLE);

                Op1.setVisibility(View.INVISIBLE);
                Op2.setVisibility(View.INVISIBLE);
                Op3.setVisibility(View.INVISIBLE);
            }
        });
    }
}