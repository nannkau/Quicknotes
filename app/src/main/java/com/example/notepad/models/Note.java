package com.example.notepad.models;

import java.util.Date;

public class Note {
    String noteText;
    String time;

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoteText() {
        return noteText;
    }

    public  String getTime() {
        return time;
    }

    public Note(String noteText,String  time) {
        this.noteText = noteText;
        this.time = time;
    }
}
