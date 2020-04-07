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
import android.widget.Toast;

public class viivakoodiTulokset extends Activity implements OnClickListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;
    private TextView otsikko;
    private ImageView kuva;

    private long _id;

    private DBManager dbManager;

    //suositukset
    private Button korvaa1, korvaa2;
    private TextView suositus1otsikko;
    private TextView suositus1kuvaus;
    private TextView suositus2otsikko;
    private TextView suositus2kuvaus;
    private ImageView suositus1kuva;
    private ImageView suositus2kuva;
    private ImageView vesikuva;
    private ImageView hiilikuva;
    private String suosots1="";
    private String suosots2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Tuote");

        setContentView(R.layout.activity_viivakoodi_tulokset);

        dbManager = new DBManager(this);
        dbManager.open();

        otsikko= (TextView) findViewById(R.id.tuote_otsikko);
        kuva=(ImageView) findViewById(R.id.tuotte_kuva);
        //Intent intent = new Intent(this, NextActivity.class);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        //titleText = (EditText) findViewById(R.id.subject_edittext);
        //descText = (EditText) findViewById(R.id.description_edittext);


        korvaa1= (Button) findViewById(R.id.susosituskorvaa1) ;
        korvaa2= (Button) findViewById(R.id.suosituskorvaa2);
        suositus1kuvaus=(TextView)  findViewById(R.id.suosituskuvaus1);
        suositus1otsikko=(TextView)  findViewById(R.id.suositus1);
        suositus2kuvaus=(TextView)  findViewById(R.id.suosituskuvaus2);
        suositus2otsikko=(TextView)  findViewById(R.id.suositus2);
        suositus1kuva=(ImageView) findViewById(R.id.suosituskuva1);
        suositus2kuva =(ImageView) findViewById(R.id.suosituskuva2);
        vesikuva=(ImageView) findViewById(R.id.vesikuvaus);
        hiilikuva=(ImageView) findViewById(R.id.hiilikuvaus);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        int vesi = intent.getIntExtra("vetta",0);
        int hiili = intent.getIntExtra("hiili",0);


        if(intent.toString().matches("6409531659413")) {
            _id = Long.parseLong("hernekeittoperinteinenjalostaja");}

        String name1 = "Hernekeitto perinteinen, Jalostaja";
        String desc = "1";
        //String vesi = intent.getStringExtra("vetta");
        //String test = name.toLowerCase().trim().replace(",","").replace("ä","a");


        otsikko.setText(name1);
        //kuvan asetus
        try {
            Resources res = getResources();
            String mDrawableName = name.toLowerCase().trim().replace(",","").replace("ä","a").replace(" ","");
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            Drawable drawable = res.getDrawable(resID);
            kuva.setImageDrawable(drawable);
        }catch(Exception ex) {
            kuva.setImageResource(R.drawable.hernekeittoperinteinenjalostaja);
        };
        hiilikuva.setImageResource(R.drawable.fp4);
        vesikuva.setImageResource(R.drawable.vesi3);



        //suositusten populeittaus

        String tuote = "hernekeitto";
        int suos1kuv= R.drawable.empty_view_bg;
        int suos2kuv=R.drawable.empty_view_bg;
        //String suosots1="";
        //String suosots2="";
        String suostext1="";
        String suostext2="";

        switch (tuote) {
            case "hernekeitto":
                suos1kuv=R.drawable.hernekeittoluomujalostaja;
                suos2kuv=R.drawable.hernekeittoharkisjalostaja;
                suosots1="Hernekeitto Luomu, Jalostaja";
                suosots2="Hernekeitto Härkis, Jalostaja";
                suostext1="Valitse pienempi vesijalanjälki ja luomu tuote";
                suostext2="Valitse lihaton vaihtoehto";

                break;
            //default:
            //break;
        }
        suositus1kuva.setImageResource(suos1kuv);
        suositus2kuva.setImageResource(suos2kuv);
        suositus1otsikko.setText(suosots1);
        suositus2otsikko.setText(suosots2);
        suositus1kuvaus.setText(suostext1);
        suositus2kuvaus.setText(suostext2);


        //updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        korvaa1.setOnClickListener(this);
        korvaa2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        String title="";
        CharSequence text = "Tuote lisätty!";
        Toast toast = Toast.makeText(viivakoodiTulokset.this,text, Toast.LENGTH_LONG);
        View toastView = toast.getView();
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        //toastMessage.setTextColor(Color.rgb(0,58,89));
        switch (v.getId()) {
            case R.id.susosituskorvaa1:
                title = suosots1;
                String desc = "1";

                //dbManager.update(_id, title, desc);
                dbManager.insert(title, desc);

                toast.show();
                this.returnLista();
                break;

            case R.id.suosituskorvaa2:
                title = suosots2;
                String desc2 = "1";

                //dbManager.update(_id, title, desc);
                dbManager.insert(title, desc2);

                toast.show();
                this.returnLista();
                break;


            case R.id.btn_delete:
                title = "Hernekeitto perinteinen, Jalostaja";
                String desc3 = "1";

                //dbManager.update(_id, title, desc);
                dbManager.insert(title, desc3);
                toast.show();
                this.returnLista();
                break;

            case R.id.btn_update:
                this.returnHome();
                break;
        }
    }



    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ViivakoodiActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
    public void returnLista() {
        Intent home_intent = new Intent(getApplicationContext(), CountryListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
