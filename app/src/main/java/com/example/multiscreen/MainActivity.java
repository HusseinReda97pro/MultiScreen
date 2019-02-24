package com.example.multiscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OpenNumpersActivity(View v){
        Intent i = new Intent(this,NumberActivity.class);
        startActivity(i);
    }
    public void OpenFamilyActivity(View v){
        Intent i = new Intent(this,FamilyActivity.class);
        startActivity(i);
    }
    public void OpenColorsActivity(View v){
        Intent i = new Intent(this,ColorsActivity.class);
        startActivity(i);
    }
    public void OpenPhrasesActivity(View v){
        Intent i = new Intent(this,PhrasesActivity.class);
        startActivity(i);
    }
}
