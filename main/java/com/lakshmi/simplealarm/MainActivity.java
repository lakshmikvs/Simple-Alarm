package com.lakshmi.simplealarm;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String currTime = sp.getString("currTime","4:00");
        ((TextView)findViewById(R.id.textView)).setText(currTime);

        Button bUp = (Button)findViewById(R.id.btnUp);

        bUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String[] arrCurrentTime = ((TextView)findViewById(R.id.textView)).getText().toString().split(":");

                double currTime = Double.valueOf(arrCurrentTime[0]).doubleValue() + (Double.valueOf(arrCurrentTime[1]).doubleValue())/60;
                if(currTime != 8.0) {
                    double newTime = currTime + 0.5;
                    String newStrTime = ((long)newTime) + ":" +String.format("%2d",(long) (((newTime - ((long) newTime))) * 60)).replace(" ","0");
                    ((TextView) findViewById(R.id.textView)).setText(newStrTime);
                }
            }
        });

        Button bDown = (Button)findViewById(R.id.btnDown);

        bDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrCurrentTime = ((TextView)findViewById(R.id.textView)).getText().toString().split(":");

                double currTime = Double.valueOf(arrCurrentTime[0]).doubleValue() + (Double.valueOf(arrCurrentTime[1]).doubleValue())/60;
                if(currTime != 4.0) {
                    double newTime = currTime - 0.5;
                    String newStrTime = ((long)newTime) + ":" +String.format("%2d",(long) (((newTime - ((long) newTime))) * 60)).replace(" ","0");
                    ((TextView) findViewById(R.id.textView)).setText(newStrTime);
                }
            }
        });

        Button bOk = (Button)findViewById(R.id.btnOk);
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putString("currTime", (((TextView) findViewById(R.id.textView)).getText()).toString());
                e.commit();

                AlarmManager am =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent i = new Intent(this,MainActivity.class);

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
