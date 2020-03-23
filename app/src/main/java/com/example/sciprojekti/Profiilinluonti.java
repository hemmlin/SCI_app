package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import static com.example.sciprojekti.ProfiiliActivity.MyPREFERENCES;

public class Profiilinluonti extends AppCompatActivity {
    /**keyt, käytetään luonnissa ja profiiliactivityssä*/
    public static final String sNimi = "key_save_nimi";
    public static final String sIkaryhma = "key_save_ikaryhma";
    public static final String sSukupuoli = "key_save_sukupuoli";
    public static final String sRuokavalio = "key_save_ruokavalio";
    public static final String sAllergiat = "key_save_allergiat";
    public static final String sKotimaisuus = "key_save_kotimaisuus";

    /**kirjotettavat ja valittavat laatikot*/
    EditText etNimi, etAllergiat;
    Spinner spIkaryhma, spSukupuoli, spRuokavalio;
    Switch swKotimaisuus, swEettisyys, swEdullisuus, swHiili, swVesi;

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
        spRuokavalio = findViewById(R.id.syota_ruokavalio);
        etAllergiat = findViewById(R.id.syota_allergiat);
        swKotimaisuus = findViewById(R.id.switch_kotimaisuus);
        swEettisyys = findViewById(R.id.switch_eettisyys);
        swEdullisuus = findViewById(R.id.switch_edullisuus);
        swHiili = findViewById(R.id.switch_hiilijalanjalki);
        swVesi = findViewById(R.id.switch_vesijalanjalki);

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
                String strAllergiat = etAllergiat.getText().toString().trim();
                String strRuokavalio = spRuokavalio.getSelectedItem().toString().trim();

                Intent intent = new Intent(Profiilinluonti.this, ProfiiliActivity.class);
                Bundle bundle = new Bundle();

                /**lisää sharedPreferencesiin tiedot*/
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(sNimi, strNimi);
                editor.putString(sIkaryhma, strIkaryhma);
                editor.putString(sSukupuoli, strSukupuoli);
                editor.putString(sAllergiat, strAllergiat);
                editor.putString(sRuokavalio, strRuokavalio);

                String finalStr = new String();

                if (swKotimaisuus.isChecked()) {
                    finalStr += "Kotimaisuus ";
                }

                if (swEettisyys.isChecked()) {
                    finalStr += "Eettisyys ";
                }

                if (swEdullisuus.isChecked()) {
                    finalStr += "Edullisuus ";
                }

                if (swHiili.isChecked()) {
                    finalStr += "Hiilijalanjäljen minimointi ";
                }

                if (swVesi.isChecked()) {
                    finalStr += "Vesijalanjäljen minimointi ";
                }

                editor.putString(sKotimaisuus, finalStr);
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
        etAllergiat = findViewById(R.id.syota_allergiat);

        /**Asetetaan haluttu teksti oikeeseen boksiin*/
        etNimi.setText(sharedPreferences.getString(sNimi,""));
        etAllergiat.setText(sharedPreferences.getString(sAllergiat,""));
    }

    /**Tallentaa datan, kun käyttäjä sulkee äpsin*/
    @Override
    protected void onPause()
    {
        super.onPause();

        etNimi = findViewById(R.id.syota_nimi);
        etAllergiat = findViewById(R.id.syota_allergiat);

        sharedPreferences
                = getSharedPreferences(MyPREFERENCES,
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

        myEdit.putString(sNimi,etNimi.getText().toString().trim());
        myEdit.putString(sAllergiat,etAllergiat.getText().toString().trim());

        myEdit.apply();
    }
}
