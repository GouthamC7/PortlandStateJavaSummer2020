package edu.pdx.cs410j.podili.phonebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelpActivity extends AppCompatActivity {

    TextView tvReadMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        tvReadMe = (TextView) findViewById(R.id.tvREADME);
        StringBuilder sb = new StringBuilder();

        sb.append("Author: Goutham Podili");
        sb.append("\n");
        sb.append("\n");
        sb.append("Application: Fully featured android application");
        sb.append("\n");
        sb.append("\n");
        sb.append("There are two features in this application adding a bill and searching for bills Add call feature adds the bill to the customer and search option can be used to search the bills of customer based on name and dates.");
        tvReadMe.setText(sb.toString());



    }
}