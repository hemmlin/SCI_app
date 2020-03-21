package com.example.sciprojekti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class AddCountryActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    //private EditText subjectEditText;
    private EditText descEditText;
    private Spinner spinner1;
    //private Button btnSubmit;

    private DBManager dbManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);


        //subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);

        List<String> searchablespinner_list = new ArrayList<String>();
        SearchableSpinner searchablespinner = (SearchableSpinner) findViewById(R.id.searchablespinner);


        searchablespinner_list.add("Maito Valio");
        searchablespinner_list.add("Omenamehu Pirkka");
        searchablespinner_list.add("Ruisleipä Vaasan");
        searchablespinner_list.add("Banaani");
        searchablespinner_list.add("Omena");
        searchablespinner_list.add("Suklaalevy Fazer");
        searchablespinner_list.add("Nyhtökaura");
        searchablespinner_list.add("Härkis");
        searchablespinner_list.add("Jauhelliha");
        searchablespinner_list.add("Riisi");
        searchablespinner_list.add("Vessapaperi");

        searchablespinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, searchablespinner_list));
        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
       // addListenerOnSpinnerItemSelection();



    }
   // public void addListenerOnSpinnerItemSelection() {
      //  spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        //spinner2 = (Spinner) findViewById(R.id.searchablespinner);


   // }
    // get the selected dropdown list value
   // public void addListenerOnButton() {

       // spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2= (Spinner) findViewById(R.id.searchablespinner);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
   // }

    //spinnerihommat loppuu
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                SearchableSpinner spinner;
                spinner = (SearchableSpinner) findViewById(R.id.searchablespinner);
                spinner.setTitle("Tuote");
                spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                final String tuote= String.valueOf(spinner.getSelectedItem());
                //final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                dbManager.insert(tuote, desc);

                Intent main = new Intent(AddCountryActivity.this, CountryListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }



}
