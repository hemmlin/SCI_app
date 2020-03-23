package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.sciprojekti.ProfiiliActivity.MyPREFERENCES;

public class Profiilinluonti extends AppCompatActivity {
    /**keyt, käytetään luonnissa ja profiiliactivityssä*/
    public static final String sNimi = "key_save_nimi";
    public static final String sIkaryhma = "key_save_ikaryhma";
    public static final String sSukupuoli = "key_save_sukupuoli";
    public static final String sKoulutus = "key_save_koulutus";
    public static final String sRuokavalio = "key_save_ruokavalio";
    public static final String sKulutustottumukset = "key_save_kulutustottumukset";

    /**kirjotettavat ja valittavat laatikot*/
    EditText etNimi, etKoulutus, etKulutustottumukset;
    Spinner spIkaryhma, spSukupuoli, spRuokavalio;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiilinluonti);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        /**hae oikeet id:t*/
        etNimi = findViewById(R.id.syota_nimi);
        spIkaryhma = findViewById(R.id.syota_ikaryhma);
        spSukupuoli = findViewById(R.id.syota_sukupuoli);
        etKoulutus = findViewById(R.id.syota_koulutus);
        spRuokavalio = findViewById(R.id.syota_ruokavalio);
        etKulutustottumukset = findViewById(R.id.syota_kulutustottumukset);

        /**lähetä tiedot ProfiiliActivityyn, kun kayttaja painaa OK-nappia**/
        Button ok_nappi = findViewById(R.id.ok_nappi);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        ok_nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**hae Viewistä syötetty teksti*/
                String strNimi = etNimi.getText().toString().trim();
                String strIkaryhma = spIkaryhma.getSelectedItem().toString().trim();
                String strSukupuoli = spSukupuoli.getSelectedItem().toString().trim();
                String strKoulutus = etKoulutus.getText().toString().trim();
                String strRuokavalio = spRuokavalio.getSelectedItem().toString().trim();
                String strKulutustottumukset = etKulutustottumukset.getText().toString().trim();

                Intent intent = new Intent(Profiilinluonti.this, ProfiiliActivity.class);
                Bundle bundle = new Bundle();

                /**lisää sharedPreferencesiin tiedot*/
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(sNimi, strNimi);
                editor.putString(sIkaryhma, strIkaryhma);
                editor.putString(sSukupuoli, strSukupuoli);
                editor.putString(sKoulutus, strKoulutus);
                editor.putString(sRuokavalio, strRuokavalio);
                editor.putString(sKulutustottumukset, strKulutustottumukset);
                editor.apply();

                /**lähetä tiedot ProfiiliActivityyn**/
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**Haetaan datat, kun äpsi avataan uudestaan*/
    @Override
    protected void onResume()
    {
        super.onResume();

        /**Haetaan tiedot SharedPreferencesseistä*/
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_MULTI_PROCESS);

        etNimi = findViewById(R.id.syota_nimi);
        etKoulutus = findViewById(R.id.syota_koulutus);
        etKulutustottumukset = findViewById(R.id.syota_kulutustottumukset);

        /**Asetetaan haluttu teksti oikeeseen boksiin*/
        etNimi.setText(sharedPreferences.getString(sNimi,""));
        etKoulutus.setText(sharedPreferences.getString(sKoulutus,""));
        etKulutustottumukset.setText(sharedPreferences.getString(sKulutustottumukset,""));
    }

    /**Tallentaa datan, kun käyttäjä sulkee äpsin*/
    @Override
    protected void onPause()
    {
        super.onPause();

        etNimi = findViewById(R.id.syota_nimi);
        etKoulutus = findViewById(R.id.syota_koulutus);
        etKulutustottumukset = findViewById(R.id.syota_kulutustottumukset);

        sharedPreferences
                = getSharedPreferences(MyPREFERENCES,
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

        myEdit.putString(sNimi,etNimi.getText().toString().trim());
        myEdit.putString(sKoulutus,etKoulutus.getText().toString().trim());
        myEdit.putString(sKulutustottumukset,etKulutustottumukset.getText().toString().trim());

        myEdit.apply();
    }
}
