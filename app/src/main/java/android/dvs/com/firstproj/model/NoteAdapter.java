package android.dvs.com.firstproj.model;

import android.content.Context;
import android.dvs.com.firstproj.R;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 14-Apr-18.
 */

public class NoteAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Note> listNote;

    public NoteAdapter(Context context, int layout, List<Note> listNote) {
        this.context = context;
        this.layout = layout;
        this.listNote = listNote;
    }

    @Override
    public int getCount() {
        return listNote.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.note = view.findViewById(R.id.note);
            viewHolder.date = view.findViewById(R.id.date);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Note note1 = listNote.get(i);
        viewHolder.note.setText(note1.getNote());
        viewHolder.date.setText(note1.getDate());
//        view.setBackgroundColor(Color.parseColor("#ffe6e6"));
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                    view.setBackgroundColor(Color.parseColor("#ff9999"));
//                } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
//                    view.setBackgroundColor(Color.parseColor("#ffe6e6"));
//                }
//                return false;
//            }
//        });
        return view;
    }

    private class ViewHolder{
        TextView note;
        TextView date;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Note> getListNote() {
        return listNote;
    }

    public void setListNote(List<Note> listNote) {
        this.listNote = listNote;
    }
}
