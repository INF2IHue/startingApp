package com.example.martijn.startingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.martijn.startingapp.Library.Connect;
import com.example.martijn.startingapp.Library.HueChangeState;
import com.example.martijn.startingapp.Library.LampState;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Vanaf hier kun je beginnen met coderen




        //sla de button op het scherm op in een variabele om zo de knop te kunnen gebruiken
        //Er wordt gezocht naar een button met als ID 'button'
        Button button = (Button) findViewById(R.id.button);
        //zodra er op de button gedrukt wordt, reageer met een actie
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //hierin kun je bepalen wat er moet gebeuren bij een druk op de button




            }
        });
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
