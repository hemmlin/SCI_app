package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class ProfiiliActivity extends AppCompatActivity {
    public static final String edit = "com.example.sciprojekti.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiili);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String nimi = bundle.getString("key_nimi");
            String ikaryhma = bundle.getString("key_ikaryhma");
            String sukupuoli = bundle.getString("key_sukupuoli");
            String koulutus = bundle.getString("key_koulutus");
            String ruokavalio = bundle.getString("key_ruokavalio");
            String kulutustottumukset = bundle.getString("key_kulutustottumukset");

            TextView omaprofiili_nimi = findViewById(R.id.omaprofiili_nimi);
            TextView prof_ikaryhma = findViewById(R.id.prof_ikaryhma);
            TextView prof_sukupuoli = findViewById(R.id.prof_sukupuoli);
            TextView prof_koulutus = findViewById(R.id.prof_koulutus);
            TextView prof_ruokavalio = findViewById(R.id.prof_ruokavalio);
            TextView prof_kulutustottumukset = findViewById(R.id.prof_kulutustottumukset);

            omaprofiili_nimi.setText(nimi);
            prof_ikaryhma.setText(ikaryhma);
            prof_sukupuoli.setText(sukupuoli);
            prof_koulutus.setText(koulutus);
            prof_ruokavalio.setText(ruokavalio);
            prof_kulutustottumukset.setText(kulutustottumukset);
        }

        /** Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profiilinluonti.nimi);

        Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.omaprofiili_nimi);
        textView.setText(message);*/


        /**Siirry muokkausscreeniin*/
        Button editbtn = (Button)findViewById(R.id.edit);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfiiliActivity.this, Profiilinluonti.class));
            }
        });


    }
}
