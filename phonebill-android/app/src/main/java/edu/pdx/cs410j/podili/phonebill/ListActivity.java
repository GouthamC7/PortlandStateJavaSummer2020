package edu.pdx.cs410j.podili.phonebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();
        ArrayList<PhoneCall> callList = (ArrayList<PhoneCall>) extras.getSerializable("data");

        /**
        ArrayList<PhoneCall> callList = new ArrayList<>();
        callList.add(new PhoneCall("1234567890","1234567890","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("0987654321","0987654321","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("1234567890","1234567890","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("0987654321","0987654321","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("1234567890","1234567890","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("0987654321","0987654321","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("1234567890","1234567890","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        callList.add(new PhoneCall("0987654321","0987654321","11/11/2011 11:00 AM", "11/11/2011 11:30 AM"));
        */

        ListView mListView =  findViewById(R.id.listView);
        CallListAdapter adapter = new CallListAdapter(this, R.layout.adapter_view_layout, callList);
        mListView.setAdapter(adapter);
    }
}