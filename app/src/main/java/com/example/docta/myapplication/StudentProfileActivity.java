package com.example.docta.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.docta.myapplication.util.Constants;

public class StudentProfileActivity extends AppCompatActivity {

    private TextInputEditText tie_avatar_name;
    private Button btn_back;
    private Button btn_login;
    private Button btn_create_account;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        if(savedInstanceState==null){
            String title = getString(R.string.Titlu_Autentificare);
            this.setTitle(title);
        }
        initComponents();

         btn_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onBackPressed();
             }
         });



         btn_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(isValid()){
                     String name = tie_avatar_name.getText().toString();
                     intent = new Intent(getApplicationContext(), HomePageActivity.class);
                     intent.putExtra(Constants.NAME_KEY, name);
                     startActivity(intent);
                 }
             }
         });



         btn_create_account.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 intent=new Intent(getApplicationContext(),LoginStudentActivity.class);
                 startActivity(intent);
             }
         });

    }
    private void initComponents(){
        btn_login =findViewById(R.id.loginelev_btn_autentificare);
        btn_create_account =findViewById(R.id.loginelev_btn_crearecont);
        tie_avatar_name = findViewById(R.id.autentificare_elev_tid_numeavatar);
        btn_back=findViewById(R.id.loginelev_btn_back);
    }

    public boolean isValid(){
        if(tie_avatar_name.getText() == null || tie_avatar_name.getText().toString().contains(" ") || tie_avatar_name.getText().toString().trim().isEmpty()){
            tie_avatar_name.setError(getText(R.string.cont_elev_numeavatar_eroare));
            Toast.makeText(getApplicationContext(),R.string.cont_elev_numeavatar_eroare,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}