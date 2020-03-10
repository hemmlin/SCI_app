package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profiilinluonti extends AppCompatActivity {
    /**messaget*/
    public static final String nimi = "com.example.sciprojekti.MESSAGE";
    EditText etNimi, etIkaryhma, etSukupuoli, etKoulutus, etRuokavalio, etKulutustottumukset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiilinluonti);

        etNimi = findViewById(R.id.syota_nimi);
        etIkaryhma = findViewById(R.id.syota_ikaryhma);
        etSukupuoli = findViewById(R.id.syota_sukupuoli);
        etKoulutus = findViewById(R.id.syota_koulutus);
        etRuokavalio = findViewById(R.id.syota_ruokavalio);
        etKulutustottumukset = findViewById(R.id.syota_kulutustottumukset);

        Button ok_nappi = findViewById(R.id.ok_nappi);
        ok_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enimi = etNimi.getText().toString().trim();
                String eikaryhma = etIkaryhma.getText().toString().trim();
                String esukupuoli = etSukupuoli.getText().toString().trim();
                String ekoulutus = etKoulutus.getText().toString().trim();
                String eruokavalio = etRuokavalio.getText().toString().trim();
                String ekulutustottumukset = etKulutustottumukset.getText().toString().trim();

                Intent intent = new Intent(Profiilinluonti.this, ProfiiliActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("key_nimi", enimi);
                bundle.putString("key_ikaryhma", eikaryhma);
                bundle.putString("key_sukupuoli", esukupuoli);
                bundle.putString("key_koulutus", ekoulutus);
                bundle.putString("key_ruokavalio", eruokavalio);
                bundle.putString("key_kulutustottumukset", ekulutustottumukset);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /** Kutsutaan, kun käyttäjä painaa ok-nappia (etsitään tieto nimikentästä)*/
    /**public void sendAlltoProfile(View view) {
        Intent intent = new Intent(this, ProfiiliActivity.class);
        EditText editText = (EditText) findViewById(R.id.syota_nimi);
        String message = editText.getText().toString();
        intent.putExtra(nimi, message);
        startActivity(intent);
    }*/
}
