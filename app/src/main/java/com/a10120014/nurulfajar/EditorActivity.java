package com.a10120014.nurulfajar;

/*
Nama    : Nurul Fajar
NIM     : 10120014
Kelas   : IF-1
Matkul  : Aplikasi Komputer Bergerak
*/


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a10120014.nurulfajar.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText EtTitle, EtCategory, EtDescription, EtCreatedAt, EtUpdatedAt;
    private Button BtnSave, Btnback;
    private Helper db = new Helper(this);

    private String Id, Title, Category, Description, CreatedAt, UpdatedAt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        EtTitle = findViewById(R.id.et_title);
        EtCategory = findViewById(R.id.et_category);
        EtDescription = findViewById(R.id.et_description);

        BtnSave = findViewById(R.id.btn_save);
        Btnback = findViewById(R.id.btn_back);
        Id = getIntent().getStringExtra("id");
        Title = getIntent().getStringExtra("title");
        Category = getIntent().getStringExtra("category");
        Description = getIntent().getStringExtra("description");

        if (String.valueOf(Id) == null || String.valueOf(Id).equals("")){
            setTitle("Tambah Notes");
        }else {
            setTitle("Edit Notes");
            EtTitle.setText(Title);
            EtCategory.setText(Category);
            EtDescription.setText(Description);
        }

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Id == null || Id.equals("")){
                        save();
                    }else{
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Saving ", e.getMessage());
                }
            }
        });

        Btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void save(){
        if (String.valueOf(EtTitle.getText()).equals("") || String.valueOf(EtCategory.getText()).equals("")|| String.valueOf(EtDescription.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else{
            db.Insert(EtTitle.getText().toString(),EtCategory.getText().toString(),EtDescription.getText().toString());
            finish();
        }
    }
    public void edit(){
        if (String.valueOf(EtTitle.getText()).equals("") ||String.valueOf(EtCategory.getText()).equals("")||String.valueOf(EtDescription.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else{
            db.Update(Integer.parseInt(Id), EtTitle.getText().toString(), EtCategory.getText().toString(), EtDescription.getText().toString());
            finish();
        }
    }

}