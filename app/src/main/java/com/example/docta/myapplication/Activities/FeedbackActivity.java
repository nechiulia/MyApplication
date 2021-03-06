package com.example.docta.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docta.myapplication.R;
import com.example.docta.myapplication.Classes.util.Constants;

public class FeedbackActivity extends AppCompatActivity {
    private EditText et_feedback;
    private ProgressBar pb_mean;
    private RatingBar rb_review;
    private Button btn_send;
    private TextView tv_mean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState==null){
            String title = getString(R.string.Titlu_Pareri);
            this.setTitle(title);
        }
        initComps();
    }

    private void initComps(){
        et_feedback = findViewById(R.id.feedback_et_opinion);
        pb_mean = findViewById(R.id.feedback_progressB);
        rb_review = findViewById(R.id.feedback_ratingB);
        btn_send = findViewById(R.id.feedback_btn_save);
        tv_mean = findViewById(R.id.feedback_tv_average);
        tv_mean.setText(null);
        btn_send.setOnClickListener(save());
    }

    private View.OnClickListener save(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValid() && rb_review.getProgress() > 0){

                    Toast.makeText(getApplicationContext(),R.string.parere_multimum,Toast.LENGTH_LONG).show();
                    pb_mean.setProgress((int)rb_review.getRating());
                    tv_mean.setText(String.valueOf(pb_mean.getProgress()));
                    if(et_feedback.getText()==null)et_feedback.setText(R.string.feedback_et_opinion_hint);
                    Intent intent= new Intent(getApplicationContext(), HomePageActivity.class);
                    intent.putExtra(Constants.DOWNLOAD_DONE, true);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),R.string.parere_ratingbar_eroare,Toast.LENGTH_LONG).show();
                    pb_mean.setProgress((int)rb_review.getRating());
                    tv_mean.setText(null);

                }
            }
        };
    }

    private boolean isValid(){
        if(rb_review.getProgress() == 0) {
            Toast.makeText(getApplicationContext(), R.string.parere_ratingbar_eroare, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
