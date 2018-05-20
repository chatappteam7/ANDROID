package android.dvs.com.firstproj.model;

import java.util.Date;

/**
 * Created by Admin on 14-Apr-18.
 */

public class Note {
    private int id;
    private String note;
    private String date;

    public Note(int id, String note, String date) {
        this.id = id;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
