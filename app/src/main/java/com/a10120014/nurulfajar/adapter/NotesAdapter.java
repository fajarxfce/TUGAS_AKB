package com.a10120014.nurulfajar.adapter;

/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a10120014.nurulfajar.R;
import com.a10120014.nurulfajar.model.Data;

import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> list;

    public NotesAdapter(Activity activity, List<Data> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null){
            view = inflater.inflate(R.layout.list_user, null);
        }

        if (view != null){
            TextView tv_title = view.findViewById(R.id.tv_title);
            TextView tv_category = view.findViewById(R.id.tv_category);
            TextView tv_description = view.findViewById(R.id.tv_description);
            TextView tv_created_at = view.findViewById(R.id.tv_created_at);
            TextView tv_updated_at = view.findViewById(R.id.tv_updated_at);


            Data data = list.get(i);
            tv_title.setText(data.getTitle());
            tv_category.setText(data.getCategory());
            tv_description.setText(data.getDescription());
            tv_created_at.setText(data.getCreatedAt());
            tv_updated_at.setText(data.getUpdatedAt());
        }

        return view;
    }
}
