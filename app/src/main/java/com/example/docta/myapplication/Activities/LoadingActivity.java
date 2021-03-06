package com.example.docta.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.docta.myapplication.R;

public class LoadingActivity extends AppCompatActivity {
    private ImageView loading_iv_tranz;



//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        startActivity(new Intent(getApplicationContext(),LoginPageActivity.class));
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loading_iv_tranz = findViewById(R.id.loading_imgSplash);

        Animation tranz = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tranzitie_poza);
        loading_iv_tranz.startAnimation(tranz);

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(getApplicationContext(),LoginPageActivity.class));
                    finish();
                }
            }
        };
        timer.start();

    }
}
