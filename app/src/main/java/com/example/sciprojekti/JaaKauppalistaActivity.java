package com.example.sciprojekti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class JaaKauppalistaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaa_kauppalista);

        /**Siirry kauppalista lähetetty-screeniin*/
        Button jaabtn = (Button)findViewById(R.id.jaa);

        jaabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JaaKauppalistaActivity.this, KauppalistaLahetetty.class));
            }
        });

    }
}
