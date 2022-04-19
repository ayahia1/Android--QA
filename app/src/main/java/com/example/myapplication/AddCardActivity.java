package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelBtn = findViewById(R.id.cacel_icn);
        ImageView saveBtn = findViewById(R.id.save_icn);
        EditText QuestionView = findViewById(R.id.Question_view);
        EditText AnswerView = findViewById(R.id.Answer_view);

        if (getIntent().getExtras() == null){
            Log.d("getExtras null message", "onCreate: ");
        }

        if (getIntent().getExtras() != null) {
            String curQ = getIntent().getExtras().get("Question").toString();
            String curA = getIntent().getExtras().get("Answer").toString();
            QuestionView.setText(curQ);
            AnswerView.setText(curA);
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Question = ((EditText)  findViewById(R.id.Question_view)).getText().toString();
                String Answer = ((EditText)  findViewById(R.id.Answer_view)).getText().toString();
                if (Question.length() == 0 || Answer.length() == 0){
                    String message = "Make sure that Answer and Question are not empty";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Intent data = new Intent();
                    data.putExtra("Question", Question);
                    data.putExtra("Answer", Answer);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}