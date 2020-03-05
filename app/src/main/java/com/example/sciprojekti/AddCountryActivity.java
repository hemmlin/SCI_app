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

public class AddCountryActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;
    private Spinner spinner1;
    private Button btnSubmit;

    private DBManager dbManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);


        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);


        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
        addListenerOnSpinnerItemSelection();


    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(AddCountryActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : " ,
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    //spinnerihommat loppuu
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                final String tuote= String.valueOf(spinner1.getSelectedItem());
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                dbManager.insert(tuote, desc);

                Intent main = new Intent(AddCountryActivity.this, CountryListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }



}
