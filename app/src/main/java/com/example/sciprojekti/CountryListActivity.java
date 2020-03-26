package com.example.sciprojekti;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private RelativeLayout totalBar;
    private int imageToBeShown=R.drawable.vesi1;
    private int imageToBeShown2=R.drawable.fp1;



    private SimpleCursorAdapter adapter;
    //private SimpleCursorAdapter.bindView() listAdapter;
    //private ArrayList<DatabaseGet> userModelArrayList;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.DESC, DatabaseHelper.VESI, DatabaseHelper.HIILI };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc, R.id.vesilataa, R.id.hiililataa };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        final Cursor cursor = dbManager.fetch();


        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        totalBar= (RelativeLayout) findViewById(R.id.total_bar);

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        final int[] imgs = new int[] { R.drawable.vesi1,R.drawable.vesi2,R.drawable.vesi3,R.drawable.vesi4,R.drawable.vesi5 };
        final int[] imgs2 = new int[] { R.drawable.fp1,R.drawable.fp2,R.drawable.fp3,R.drawable.fp4,R.drawable.fp5 };

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor,
                                        int columnIndex) {
                switch (view.getId()) {
                    case R.id.vesilataa:
                        String imageType = cursor.getString(3);//getInt(columnIndex);//columnIndex
                        //int imageToBeShown=imgs[0];
                    switch (imageType) {
                        case "0":
                            imageToBeShown=imgs[0];
                            break;
                        case "1":
                            imageToBeShown=imgs[1];
                            break;
                        case "2":
                            imageToBeShown=imgs[2];
                            break;
                        case "3":
                            imageToBeShown=imgs[3];
                            break;
                        case "4":
                            imageToBeShown=imgs[4];
                            break;
                        default:
                            break;
                        }
                        ((ImageView)view).setImageResource(imageToBeShown);
                        return true;

                    case R.id.hiililataa:
                        String imageType2 = cursor.getString(4);//getInt(columnIndex);//columnIndex
                        switch (imageType2) {
                            case "0":
                                imageToBeShown2=imgs2[0];
                                break;
                            case "1":
                                imageToBeShown2=imgs2[1];
                                break;
                            case "2":
                                imageToBeShown2=imgs2[2];
                                break;
                            case "3":
                                imageToBeShown2=imgs2[3];
                                break;
                            case "4":
                                imageToBeShown2=imgs2[4];
                                break;
                            default:
                                break;
                        }
                        ((ImageView)view).setImageResource(imageToBeShown2);
                        final int noh= imageToBeShown2;
                        return true;
                }
                return false;
            }
        });
        adapter.notifyDataSetChanged();


        TextView sijoitus = (TextView) findViewById(R.id.total_vesi_number);
        sijoitus.setText("0");

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);
                //TextView vesiTextView =(TextView) view.findViewById(R.id.vesi);


                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();
                int vetta = imageToBeShown;
                int hiili = imageToBeShown2;

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("vetta", vetta);
                modify_intent.putExtra("hiili", hiili);

                startActivity(modify_intent);
                //paivitetaan totalit

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
/*
    DatabaseHelper db = new DatabaseHelper(this);


    public String getTotVesi(){
        int vesi = 0;
        List<String> kolumni = db.VEDET;
        //new String[] {  DatabaseHelper.VESI };
        if(kolumni.size()>1){
            int[] sailo=convert(kolumni);
            for (int i : sailo)
                vesi += i;
        }
        return getString(vesi);
    }

    public int[] convert(List<String> string) {
        int number[] = new int[string.size()];

        for (int i = 0; i < string.size(); i++) {
            number[i] = Integer.parseInt(string.get(i));
        }
        return number;
    }
    private void  tiedonLataus () {
        getApplicationContext();
        DatabaseHelper db = new DatabaseHelper(this);

        List<String> kolumni = db.getAllVedet();

        Log.d("total_count", "getTotVesi: "+kolumni);
        TextView sijoitus = (TextView) findViewById(R.id.total_vesi_number);
        sijoitus.setText(getTotVesi());
    }
    */
    //Kokonaiskulutuksen laskeminen
    //muuttaa jonon Stringeja numeroiks


}