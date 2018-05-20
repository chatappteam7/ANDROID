package android.dvs.com.firstproj;

import android.app.Dialog;
import android.database.Cursor;
import android.dvs.com.firstproj.model.Note;
import android.dvs.com.firstproj.model.NoteAdapter;
import android.dvs.com.firstproj.model.SQLite;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SQLite database;
    ListView lvNote;
    ArrayList<Note> arrayNote;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new SQLite(this,"note.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS NOTES (Id INTEGER PRIMARY KEY AUTOINCREMENT, GhiChu NVARCHAR, ThoiGian NVARCHAR)");

        //database.QueryData("DELETE FROM NOTES");

        AnhXa();
        noteAdapter = new NoteAdapter(this,R.layout.list_note,arrayNote);
        lvNote.setAdapter(noteAdapter);
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogSua(i);
            }
        });
    }

    private void AnhXa(){
        lvNote = findViewById(R.id.listnote);
        arrayNote = new ArrayList<>();
        Cursor data = database.getData("SELECT * FROM NOTES");
        while (data.moveToNext()){
            arrayNote.add(new Note(data.getInt(0),data.getString(1),data.getString(2)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.them){
            dialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_note);

        final EditText editText = dialog.findViewById(R.id.editText);
        Button huy = dialog.findViewById(R.id.button3);
        Button them = dialog.findViewById(R.id.button4);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("insert into NOTES values (null,'"+editText.getText()+"',DateTime('now','localtime'))");
                arrayNote = new ArrayList<>();
                Cursor data = database.getData("SELECT * FROM NOTES");
                while (data.moveToNext()){
                    arrayNote.add(new Note(data.getInt(0),data.getString(1),data.getString(2)));
                }
                noteAdapter.setListNote(arrayNote);
                lvNote.setAdapter(noteAdapter);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void dialogSua(int i){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_note);
        final Note note = arrayNote.get(i);

        final EditText editText = dialog.findViewById(R.id.editText);
        editText.setText(note.getNote());
        Button huy = dialog.findViewById(R.id.button3);
        Button them = dialog.findViewById(R.id.button4);

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("delete from NOTES where Id = "+note.getId());
                arrayNote = new ArrayList<>();
                Cursor data = database.getData("SELECT * FROM NOTES");
                while (data.moveToNext()){
                    arrayNote.add(new Note(data.getInt(0),data.getString(1),data.getString(2)));
                }
                noteAdapter.setListNote(arrayNote);
                lvNote.setAdapter(noteAdapter);
                dialog.dismiss();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("update NOTES set GhiChu = '"+editText.getText()+"', ThoiGian = DateTime('now','localtime') where Id = "+note.getId());
                arrayNote = new ArrayList<>();
                Cursor data = database.getData("SELECT * FROM NOTES");
                while (data.moveToNext()){
                    arrayNote.add(new Note(data.getInt(0),data.getString(1),data.getString(2)));
                }
                noteAdapter.setListNote(arrayNote);
                lvNote.setAdapter(noteAdapter);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
