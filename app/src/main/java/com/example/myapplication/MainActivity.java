package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    FlashcardDatabase flashcardDatabase;
    //instance of the flashcard table in the database

    List <Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyKey", "App should be running");
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        final int[] Flashcard_index = {-1};
        TextView Question = findViewById(R.id.flashcard_question);
        TextView Answer = findViewById(R.id.flashcard_answer);
        TextView Op1 = findViewById(R.id.option1);
        TextView Op2 = findViewById(R.id.option2);
        TextView Op3 = findViewById(R.id.option3);
        ImageView On_eye = findViewById(R.id.on_eye);
        ImageView Off_eye = findViewById(R.id.off_eye);
        ImageView addCard = findViewById(R.id.add_icn);
        ImageView nextCard = findViewById(R.id.next_icn);
        ImageView delete_btn = findViewById(R.id.trash);

        allFlashcards = flashcardDatabase.getAllCards();
        //Retrieving all flashcards from the flashcardDatabase (table) and storing them into a list


        if (allFlashcards.size() > 0) {
            Flashcard_index[0] = 0;
            Flashcard first_flashcard = allFlashcards.get(0);
            Question.setText(first_flashcard.getQuestion());
            Answer.setText(first_flashcard.getAnswer());
        }

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

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


        nextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flashcard_index[0] = Flashcard_index[0] + 1;
                if (Flashcard_index[0] == allFlashcards.size()){
                    Flashcard_index[0] -= 1;
                    String message = "No flashcards to display";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //will comment when running for the first time
                  //  Flashcard_index[0] = getRandom(Flashcard_index[0], allFlashcards.size() - 1);
                    Log.d("Message", "Message");
                    Question.setText(allFlashcards.get(Flashcard_index[0]).getQuestion());
                    Answer.setText(allFlashcards.get(Flashcard_index[0]).getAnswer());
                }
            }

            private int getRandom(int min, int max) {
                int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
                return random;
            }
        });


        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0){
                    //if we have nothing to delete
                    return;
                }

                ///delete from the database
                flashcardDatabase.deleteCard(allFlashcards.get(Flashcard_index[0]).getQuestion());

                //delete from the list
                allFlashcards.remove(Flashcard_index[0]);

                // if size == 0: empty state
                if (allFlashcards.size() == 0){
                    Question.setText("Who is the 44th President of the United States?");
                    Answer.setText("Barack Obama");
                    Flashcard_index[0] = -1;
                }
                // if index - 1 >= 0: use index - 1 and set to index - 1
                else if (Flashcard_index[0] - 1 >= 0){
                    Question.setText(allFlashcards.get(Flashcard_index[0] - 1).getQuestion());
                    Answer.setText(allFlashcards.get(Flashcard_index[0] - 1).getAnswer());
                    Flashcard_index[0] -= 1;
                }
                else {
                    Question.setText(allFlashcards.get(Flashcard_index[0]).getQuestion());
                    Answer.setText(allFlashcards.get(Flashcard_index[0]).getAnswer());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
           // Log.d("WentBack1", "We got there");
            if (data != null && resultCode == RESULT_OK) {
                String Q = data.getExtras().getString("Question");
                String A = data.getExtras().getString("Answer");
              //  ((TextView) findViewById(R.id.flashcard_question)).setText(Q);
               // ((TextView) findViewById(R.id.flashcard_answer)).setText(A);

                flashcardDatabase.insertCard(new Flashcard(Q, A));


                Flashcard newCard = new Flashcard(Q,A);
                allFlashcards.add(newCard);
            }
        }
    }
}