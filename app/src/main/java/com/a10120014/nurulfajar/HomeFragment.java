package com.a10120014.nurulfajar;

/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.a10120014.nurulfajar.adapter.NotesAdapter;
import com.a10120014.nurulfajar.helper.Helper;
import com.a10120014.nurulfajar.model.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> list = new ArrayList<>();
    NotesAdapter adapter;
    Helper db = new Helper(getContext());
    Button btn_tambah, btnxHapus, btnxTambah;
    FloatingActionButton add_button;

    ImageView ivHapus, ivEdit;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        db = new Helper(getContext());
        add_button = view.findViewById(R.id.btn_tambah);

        listView = view.findViewById(R.id.lv_item);
        adapter = new NotesAdapter(getActivity(), list);
        listView.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditorActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = String.valueOf(list.get(i).getId());
                final String title = list.get(i).getTitle();
                final String category = list.get(i).getCategory();
                final String description = list.get(i).getDescription();
                final String created_at = list.get(i).getCreatedAt();
                final String updated_at = list.get(i).getUpdatedAt();

                final CharSequence[] dialogItem = {"Edit", "Hapus"};

                dialog = new AlertDialog.Builder(getContext());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getContext(), EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("title", title);
                                intent.putExtra("category", category);
                                intent.putExtra("description", description);
                                intent.putExtra("created_at", created_at);
                                intent.putExtra("updated_at", updated_at);

                                startActivity(intent);
                                break;
                            case 1:
                                db.Delete(Integer.parseInt(id));
                                list.clear();
                                GetData();
                                break;
                        }
                    }
                }).show();

                return false;
            }
        });
        GetData();

        return view;
    }
    private void GetData(){
        ArrayList<HashMap<String, String>> rows = db.GetAll();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String title = rows.get(i).get("title");
            String category = rows.get(i).get("category");
            String description = rows.get(i).get("description");
            String created_at = rows.get(i).get("created_at");
            String updated_at = rows.get(i).get("updated_at");

            Data data = new Data();
            data.setId(Integer.parseInt(id));
            data.setTitle(title);
            data.setCategory(category);
            data.setDescription(description);
            data.setCreatedAt(created_at);
            data.setUpdatedAt(updated_at);

            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        GetData();
    }

}