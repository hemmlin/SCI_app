package com.example.sciprojekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import static com.example.sciprojekti.Profiilinluonti.sIkaryhma;
import static com.example.sciprojekti.Profiilinluonti.sAllergiat;
import static com.example.sciprojekti.Profiilinluonti.sNimi;
import static com.example.sciprojekti.Profiilinluonti.sRuokavalio;
import static com.example.sciprojekti.Profiilinluonti.sSukupuoli;
import static com.example.sciprojekti.Profiilinluonti.sKotimaisuus;

public class ProfiiliActivity extends AppCompatActivity {

    public static final String sProfiilikuva = "key_save_profiilikuva";
    /**private Bitmap bitmap;
    String selectedImagePath;*/
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView imageView;
    /**Bitmap imageBitMap;*/

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

        /**Avaa kuvagallerian, kun käyttäjä klikkaa kameranappia*/
        imageView = (ImageView)findViewById(R.id.profile);
        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        /**TO DO
         * ostoslistan jakamisnappi
         * mainiin neljäs nappi?? kauppalistan jakamiseen liittyen*/

        /**Siirry muokkausscreeniin*/
        Button editbtn = (Button)findViewById(R.id.edit);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfiiliActivity.this, Profiilinluonti.class));
            }
        });
    }

    /**Avaa gallerian*/
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    /**Asettaa kuvan profiiliin*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            /**if (imageUri != null) {
                selectedImagePath = imageUri.getPath();
                selectedImagePath = getRealPathFromURI(imageUri);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(sProfiilikuva, selectedImagePath);
                editor.apply();
            }*/
            imageView.setImageURI(imageUri);
        }
    }

    /**public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }*/

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

        /**String uriBackStr = sharedPreferences.getString(sProfiilikuva, "");
        ImageView profView = findViewById(R.id.profile);
        profView.setImageResource(R.drawable.vesi);
        Uri uriBack = Uri.parse(uriBackStr);
        profView.setImageURI(uriBack);
        File imgFile = new  File(sharedPreferences.getString(sProfiilikuva, ""));

        if (imgFile.exists()){
            ImageView profView = findViewById(R.id.profile);
            profView.setImageURI(Uri.fromFile(imgFile));

        }*/

        /**if (uriBackStr != null) {
            Uri uriBack = Uri.parse(uriBackStr);
            profView.setImageURI(uriBack);
        } else {
            profView.setImageResource(R.drawable.vesi);
        }*/
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
        /**myEdit.putString(sProfiilikuva, selectedImagePath);*/

        myEdit.apply();
    }
}
