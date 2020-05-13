package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notepad.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText= (EditText) findViewById(R.id.editText);
        textView= (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        id= intent.getIntExtra("noteId",-1);
        if(id != -1){
            textView.setText("Last update: "+ MainActivity.notes1.get(id).getTime());
            editText.setText(MainActivity.notes1.get(id).getNoteText());
        }
        else {
            MainActivity.notes1.add(new Note("",""));
            MainActivity.notes.add("");
            id=MainActivity.notes1.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
            editText.setText("");
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                String date = df.format(Calendar.getInstance().getTime());
                Note note= new Note(String.valueOf(s),date );
                MainActivity.notes1.set(id,note);
                MainActivity.notes.set(id,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
