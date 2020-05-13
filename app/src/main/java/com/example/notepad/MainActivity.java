package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notepad.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     ListView listView;
    static ArrayList<String> notes= new ArrayList();
    static ArrayList<Note> notes1= new ArrayList();
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.add_note)
        {
            Intent intent= new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listView);
        notes1.add(new Note("text firt","3/4/5 : 10h30"));
        for( Note a : notes1){
            notes.add(a.getNoteText());
        }
        arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,notes);
        listView.setAdapter(arrayAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("noteId",position);
               startActivity(intent);
           }



       });
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               final int itemDelete=position;
               new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Are you sure delete this note?")
               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        notes1.remove(itemDelete);
                        notes.remove(itemDelete);
                        arrayAdapter.notifyDataSetChanged();
                   }
               }).setNegativeButton("no",null).show();

               return true;
           }
       });
    }
}
