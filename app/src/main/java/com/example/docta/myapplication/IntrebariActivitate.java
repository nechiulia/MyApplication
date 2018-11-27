package com.example.docta.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;
import com.example.docta.myapplication.clase.HttpManager;
import com.example.docta.myapplication.clase.Intrebare;
import com.example.docta.myapplication.clase.SetIntrebari;
import com.example.docta.myapplication.clase.SetIntrebariParser;
import com.example.docta.myapplication.util.Constante;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntrebariActivitate extends AppCompatActivity {
    private TextView tvNrIntrebare;
    private int nrCurent;
    private RadioGroup rg_raspunsuri;

    private RadioButton rb_raspuns1;
    private RadioButton rb_raspuns2;
    private RadioButton rb_raspuns3;
    private Button btn_confirm;
    private ImageView imgV_imagine;
    private TextView tvIntrebare;

    private double punctaj=0;
    private int nrIntrebariCorecte=0;

    private SetIntrebari set;
    private List<Intrebare> listaIntrebariUsoare;
    private List<Intrebare> listaIntrebariMedii;
    private List<Intrebare> listaIntrebariGrele;

    private List<Intrebare> listaIntrebariMatematica;
    private List<Intrebare> listaIntrebariAnimale;
    private List<Intrebare> listaIntrebariLitere;
    private List<Intrebare> listaIntrebariFructeSiLegume;
    private List<Intrebare> listaIntrebariViata;
    private ArrayList<Intrebare> listaIntrebari;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitate_intrebari);
        //set = (SetIntrebari) getIntent().getSerializableExtra("SetIntrebariKey");
        sharedPreferences = getSharedPreferences(Constante.SETARI_ELEV_PREF,MODE_PRIVATE);
       // listaIntrebari=new ArrayList<Intrebare>();
      //  listaIntrebari=(ArrayList)getIntent().getParcelableArrayListExtra("Intrebari matematica key");
       // listaIntrebari=(ArrayList<Intrebare>) getIntent().getSerializableExtra("lista intrebari key");
      //  initializareListePeCategorie();

        listaIntrebari=SaNeJucamActivitate.listaIntrebariTest;
        Collections.shuffle(listaIntrebari);
        initComponents();
        initializarePrimaIntrebare();
    }



    private View.OnClickListener confirmRaspuns(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (isValid()) {
                    RadioButton rbCorect;
                    for(int i=0;i<3;i++){
                        if(listaIntrebari.get(nrCurent-1).getRaspunsuri().get(i).isCorect()) {
                            rbCorect=(RadioButton)rg_raspunsuri.getChildAt(i);
                            if(rbCorect.getId()== rg_raspunsuri.getCheckedRadioButtonId()){
                                Toast.makeText(getApplicationContext(),getString(R.string.toast_raspuns_corect), Toast.LENGTH_LONG).show();
                                punctaj+=listaIntrebari.get(nrCurent-1).getOptiuni().getPunctaj();
                                nrIntrebariCorecte++;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),getString(R.string.toast_raspuns_gresit), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    nrCurent++;
                    if(nrCurent==4){
                        Intent intent = new Intent(getApplicationContext(), RezultatActivitate.class);
                        intent.putExtra(Constante.PUNCTAJ_KEY, punctaj);
                        intent.putExtra(Constante.NR_INTREBARI_CORECTE, nrIntrebariCorecte);
                        startActivity(intent);
                    }
                    else {
                            tvIntrebare.setText(listaIntrebari.get(nrCurent - 1).getTextIntrebare());
                            rb_raspuns1.setText(listaIntrebari.get(nrCurent - 1).getRaspunsuri().get(0).getTextRaspuns());
                            rb_raspuns2.setText(listaIntrebari.get(nrCurent - 1).getRaspunsuri().get(1).getTextRaspuns());
                            rb_raspuns3.setText(listaIntrebari.get(nrCurent - 1).getRaspunsuri().get(2).getTextRaspuns());
                            loadImageFromJson(listaIntrebari.get(nrCurent-1).getOptiuni().getImagine());
                        tvNrIntrebare.setText(nrCurent + getString(R.string.intrebari_tv_nr_intrebari));
                        rg_raspunsuri.clearCheck();
                    }
                    }
                }
        };
    }
    public boolean isValid(){
        if(rg_raspunsuri.getCheckedRadioButtonId()==-1){
            rb_raspuns3.setError(getString(R.string.intrebarea_zilei_raspuns_eroare));
            Toast.makeText(getApplicationContext(),getString(R.string.intrebarea_zilei_raspuns_eroare),Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            rb_raspuns3.setError(null);
            return true;
        }
    }
    private void initComponents(){
        tvNrIntrebare=findViewById(R.id.intrebari_tv_nr_intrebare);
        rg_raspunsuri = findViewById(R.id.intrebari_rg_raspunsuri);
        rb_raspuns1 = findViewById(R.id.intrebari_rb_raspuns1);
        rb_raspuns2 = findViewById(R.id.intrebari_rb_raspuns2);
        rb_raspuns3 = findViewById(R.id.intrebari_rb_raspuns3);
        btn_confirm = findViewById(R.id.intrebari_btn_confirm);
        imgV_imagine = findViewById(R.id.intrebari_iv_imagine);
        tvIntrebare = findViewById(R.id.intrebari_tv_intrebare);
        btn_confirm.setOnClickListener(confirmRaspuns());
        nrCurent=1;
        tvNrIntrebare.setText(nrCurent+getString(R.string.intrebari_tv_nr_intrebari));
    }


    private void initializarePrimaIntrebare(){
        tvIntrebare.setText(listaIntrebari.get(0).getTextIntrebare());
        rb_raspuns1.setText(listaIntrebari.get(0).getRaspunsuri().get(0).getTextRaspuns());
        rb_raspuns2.setText(listaIntrebari.get(0).getRaspunsuri().get(1).getTextRaspuns());
        rb_raspuns3.setText(listaIntrebari.get(0).getRaspunsuri().get(2).getTextRaspuns());
        loadImageFromJson(listaIntrebari.get(0).getOptiuni().getImagine());

            }

    private void loadImageFromJson(String urlJson){
        Picasso.with(getApplicationContext()).load(urlJson).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(imgV_imagine, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }
}



