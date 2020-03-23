package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import static com.example.sciprojekti.Profiilinluonti.sIkaryhma;
import static com.example.sciprojekti.Profiilinluonti.sAllergiat;
import static com.example.sciprojekti.Profiilinluonti.sNimi;
import static com.example.sciprojekti.Profiilinluonti.sRuokavalio;
import static com.example.sciprojekti.Profiilinluonti.sSukupuoli;
import static com.example.sciprojekti.Profiilinluonti.sKotimaisuus;

public class ProfiiliActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiili);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            /**etitään paikka, mihin getStringillä saatu tieto laitetaan setTextissä*/
            TextView nimi = findViewById(R.id.omaprofiili_nimi);
            TextView ikaryhma = findViewById(R.id.prof_ikaryhma);
            TextView sukupuoli = findViewById(R.id.prof_sukupuoli);
            TextView allergiat = findViewById(R.id.prof_allergiat);
            TextView ruokavalio = findViewById(R.id.prof_ruokavalio);
            TextView kotimaisuus = findViewById(R.id.prof_arvot);


            nimi.setText(sharedPreferences.getString(sNimi, ""));
            ikaryhma.setText(sharedPreferences.getString(sIkaryhma, ""));
            sukupuoli.setText(sharedPreferences.getString(sSukupuoli, ""));
            allergiat.setText(sharedPreferences.getString(sAllergiat, ""));
            ruokavalio.setText(sharedPreferences.getString(sRuokavalio, ""));
            kotimaisuus.setText(sharedPreferences.getString(sKotimaisuus, ""));

            /**Toasti*/
            CharSequence text = "Profiilin tiedot tallennettu!";
            Toast toast = Toast.makeText(ProfiiliActivity.this,text, Toast.LENGTH_LONG);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.profiiliactivity_toast_drawable);
            TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
            toastMessage.setTextColor(Color.rgb(0,58,89));
            toast.show();
        }

        /**TO DO
         * ruokakunta, allergiat, slaiderit vesi- ja hiilijalanjäljelle
         * ostoslistan jakamisnappi
         * mainiin neljäs nappi? kauppalistan jakamiseen liittyen
         * RGB:t taltee*/

        /**Siirry muokkausscreeniin*/
        Button editbtn = (Button)findViewById(R.id.edit);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfiiliActivity.this, Profiilinluonti.class));
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

        TextView nimi = findViewById(R.id.omaprofiili_nimi);
        TextView ikaryhma = findViewById(R.id.prof_ikaryhma);
        TextView sukupuoli = findViewById(R.id.prof_sukupuoli);
        TextView allergiat = findViewById(R.id.prof_allergiat);
        TextView ruokavalio = findViewById(R.id.prof_ruokavalio);
        TextView kotimaisuus = findViewById(R.id.prof_arvot);

        /**Asetetaan haluttu teksti oikeeseen boksiin*/
        nimi.setText(sharedPreferences.getString(sNimi,""));
        ikaryhma.setText(sharedPreferences.getString(sIkaryhma,""));
        sukupuoli.setText(sharedPreferences.getString(sSukupuoli,""));
        allergiat.setText(sharedPreferences.getString(sAllergiat,""));
        ruokavalio.setText(sharedPreferences.getString(sRuokavalio,""));
        kotimaisuus.setText(sharedPreferences.getString(sKotimaisuus, ""));
    }

    /**Tallentaa datan, kun käyttäjä sulkee äpsin*/
    @Override
    protected void onPause()
    {
        super.onPause();

        TextView nimi = findViewById(R.id.omaprofiili_nimi);
        TextView ikaryhma = findViewById(R.id.prof_ikaryhma);
        TextView sukupuoli = findViewById(R.id.prof_sukupuoli);
        TextView allergiat = findViewById(R.id.prof_allergiat);
        TextView ruokavalio = findViewById(R.id.prof_ruokavalio);
        TextView kotimaisuus = findViewById(R.id.prof_arvot);

        sharedPreferences
                = getSharedPreferences(MyPREFERENCES,
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

        myEdit.putString(sNimi,nimi.getText().toString().trim());
        myEdit.putString(sIkaryhma,ikaryhma.getText().toString().trim());
        myEdit.putString(sSukupuoli,sukupuoli.getText().toString().trim());
        myEdit.putString(sAllergiat,allergiat.getText().toString().trim());
        myEdit.putString(sRuokavalio,ruokavalio.getText().toString().trim());
        myEdit.putString(sKotimaisuus,kotimaisuus.getText().toString().trim());

        myEdit.apply();
    }
}
