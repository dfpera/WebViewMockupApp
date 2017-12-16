package com.example.daniele.webviewmockup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPrefs;

    private EditText mockupURL;
    private EditText zoomLevel;
    private static final String DEFAULT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mockupURL = (EditText) findViewById(R.id.mockupURL);
        zoomLevel = (EditText) findViewById(R.id.zoomLevel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        mockupURL.setText(sharedPrefs.getString("url", DEFAULT));
        zoomLevel.setText(sharedPrefs.getString("zoom", DEFAULT));
    }

    public void loadMockup(View v) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("url", mockupURL.getText().toString());
        editor.putString("zoom", zoomLevel.getText().toString());
        editor.commit();
        Toast.makeText(this, "Loading URL: " + mockupURL.getText().toString(), Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, FullscreenActivity.class);
        startActivity(i);
    }
}
