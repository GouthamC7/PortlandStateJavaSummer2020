package edu.pdx.cs410j.podili.phonebill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SearchActivity extends AppCompatActivity {

    EditText etName, etStart, etEnd;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etName = findViewById(R.id.etSearchName);
        etStart = findViewById(R.id.etSearchStartTime);
        etEnd = findViewById(R.id.etSearchEndTime);
        btnSearch = findViewById(R.id.btnCallSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = etName.getText().toString();
                String startTime = etStart.getText().toString();
                String endTime = etEnd.getText().toString();

                if(customerName.isEmpty()) {
                    Snackbar.make(view, "Invalid customer name", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!startTime.isEmpty() && !validateDate(startTime)) {
                    Snackbar.make(view, "Invalid start time", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!endTime.isEmpty() && !validateDate(endTime)) {
                    Snackbar.make(view, "Invalid end time", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!startTime.isEmpty() && !endTime.isEmpty() && !compareDates(startTime,endTime)) {
                    Snackbar.make(view, "End time is before start time", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    Map<String, PhoneBill> phoneBills = new HashMap<>();
                    File dir = getDataDir();
                    File file = new File(dir, "phonebills.txt");
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        phoneBills = (Map<String, PhoneBill>) ois.readObject();
                        if(phoneBills.containsKey(customerName)) {
                            PhoneBill bill = phoneBills.get(customerName);

                            TreeSet<PhoneCall> phoneCalls = (TreeSet<PhoneCall>) bill.getPhoneCalls();
                            ArrayList<PhoneCall> calls = new ArrayList<>();
                            /** Logic to get the call list from customer */
                            if(startTime.isEmpty() && endTime.isEmpty()) {
                                calls.addAll(phoneCalls);
                            } else if (!startTime.isEmpty() && endTime.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                                    Date date1 = sdf.parse(startTime);
                                    for(PhoneCall call : phoneCalls) {
                                        Date temp = sdf.parse(call.getStart());
                                        if(date1.compareTo(temp) <= 0 ) {
                                            calls.add(call);
                                        }
                                    }

                                } catch (Exception e) {
                                }

                            } else if (startTime.isEmpty() && !endTime.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                                    Date date1 = sdf.parse(endTime);
                                    for(PhoneCall call : phoneCalls) {
                                        Date temp = sdf.parse(call.getEnd());
                                        if(date1.compareTo(temp) >= 0 ) {
                                            calls.add(call);
                                        }
                                    }

                                } catch (Exception e) {
                                }
                            } else {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                                    Date date1 = sdf.parse(startTime);
                                    Date date2 = sdf.parse(endTime);
                                    for(PhoneCall call : phoneCalls) {
                                        Date temp = sdf.parse(call.getStart());
                                        if(date1.compareTo(temp) <= 0 && date2.compareTo(temp) >= 0) {
                                            calls.add(call);
                                        }
                                    }

                                } catch (Exception e) {
                                }
                            }

                            if(calls.isEmpty()) {
                                Toast.makeText(SearchActivity.this, " No call records found",Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(SearchActivity.this, ListActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("data", calls);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(SearchActivity.this, " No call records found",Toast.LENGTH_LONG).show();
                        }
                        ois.close();
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }

    public boolean validateDate(String startTimeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            sdf.setLenient(false);
            sdf.parse(startTimeString);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

    public boolean compareDates(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);
            if (date1.compareTo(date2) > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}