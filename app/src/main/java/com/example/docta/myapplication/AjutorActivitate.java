package com.example.docta.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AjutorActivitate extends AppCompatActivity {
    private TextView primaIntrebare;
    private TextView primulRaspuns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajutor_activitate);
        initComponents();
    }
    private void initComponents(){
        primaIntrebare=findViewById(R.id.ajutor_tv_prima_intrebare);
        primulRaspuns=findViewById(R.id.ajutor_tv_primul_raspuns);
        primaIntrebare.setOnClickListener(deschidePrimulRaspuns());
    }
    public View.OnClickListener deschidePrimulRaspuns(){
        return  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (primulRaspuns.getVisibility() == View.INVISIBLE) {
                    primulRaspuns.setVisibility(View.VISIBLE);
                }
                else {
                    primulRaspuns.setVisibility(View.INVISIBLE);
                }
            }
        };

    }
}
