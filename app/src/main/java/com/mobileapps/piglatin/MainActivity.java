package com.mobileapps.piglatin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etPhrase;
    TextView tvTranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhrase = findViewById(R.id.etPhrase);
        tvTranslation = findViewById(R.id.tvTranslation);

    }

    public void onClick(View view) {

        String phrase = etPhrase.getText().toString();
        String result = "";

        //String temp = "";
        String end = "";
        String ay = "ay";
        char currLetter = 'a';

        boolean newword = true;
        boolean newenddone = true;

        phrase = phrase.trim().toLowerCase();

        if (phrase.length() == 0) {
            tvTranslation.setText("Please enter a phrase above");
        }else {
            for (int i = 0; i < phrase.length(); i++){
                currLetter = phrase.charAt(i);
                Log.d("TAG", "onClick: char @ " + i + ": " + currLetter);
                if (currLetter == ' ') {
                    newword = true;
                    result = result + end + ay + " ";
                    end = "";
                    newenddone = false;
                }else {//non-blank
                    if (newword) { //first char
                        switch (currLetter) {
                            case 'a':
                            case 'e':
                            case 'i':
                            case 'o':
                            case 'u':
                                result = result + currLetter;
                                break;
                            default: //...is consonant
                                end = end + currLetter;
                        }
                        newword = false;
                    } else { // 2nd+ char
                        if (end.length() > 0 && !newenddone) { // if building new ending
                            switch (currLetter) {
                                case 'a':
                                case 'e':
                                case 'i':
                                case 'o':
                                case 'u':
                                case 'y':
                                    result = result + currLetter;
                                    newenddone = true;
                                    break;
                                default:
                                    end = end + currLetter;
                            }
                        } else { // 2nd+ char - not building new ending
                            result = result + currLetter;
                        }
                    }
                }
                Log.d("TAG", "onClick: end: " + end + " result: " + result);
            }
            result = result + end + ay;
            Log.d("TAG", "onClick: result: " + result);
            tvTranslation.setText(result);
        }
    }
}
