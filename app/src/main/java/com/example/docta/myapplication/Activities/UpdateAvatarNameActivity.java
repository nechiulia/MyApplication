package com.example.docta.myapplication.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.docta.myapplication.Classes.util.Constants;
import com.example.docta.myapplication.R;

public class UpdateAvatarNameActivity extends AppCompatActivity {
    private EditText tidName;
    Button btnSave;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_avatar_name);
        intent = getIntent();

        initComponents();
    }
    private void initComponents(){
        tidName=findViewById(R.id.update_avatar_tid_name);
        btnSave=findViewById(R.id.update_avatar_btn_save);

        btnSave.setOnClickListener(saveChanges());

    }
    private View.OnClickListener saveChanges(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.SET_NAME_KEY, tidName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }
}