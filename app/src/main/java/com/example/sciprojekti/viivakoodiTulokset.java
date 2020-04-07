package com.example.sciprojekti;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class viivakoodiTulokset extends Activity implements OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;
    private TextView otsikko;
    private ImageView kuva;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_viivakoodi_tulokset);

        dbManager = new DBManager(this);
        dbManager.open();

        otsikko= (TextView) findViewById(R.id.tuote_otsikko);
        kuva=(ImageView) findViewById(R.id.tuotte_kuva);
        //Intent intent = new Intent(this, NextActivity.class);



        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();

        if(intent.toString().matches("6409531659413")) {
            _id = Long.parseLong("hernekeittoperinteinenjalostaja");}

        String name = "Hernekeitto perinteinen, Jalostaja";
        String desc = "1";
        //String vesi = intent.getStringExtra("vetta");
        //String test = name.toLowerCase().trim().replace(",","").replace("ä","a");


        otsikko.setText(name);
        //kuvan asetus
        try {
            Resources res = getResources();
            String mDrawableName = name.toLowerCase().trim().replace(",","").replace("ä","a").replace(" ","");
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            Drawable drawable = res.getDrawable(resID);
            kuva.setImageDrawable(drawable);
        }catch(Exception ex) {
            kuva.setImageResource(R.drawable.empty_view_bg);
        };

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                //dbManager.update(_id, title, desc);
                dbManager.insert(title, desc);

                Intent siirrymuok = new Intent(this, CountryListActivity.class);
                startActivity(siirrymuok);
                onBackPressed();
                //this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ViivakoodiActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
