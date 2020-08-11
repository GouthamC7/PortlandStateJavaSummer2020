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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddCallActivity extends AppCompatActivity {

    EditText etCustomerName, etCallerNumber, etCalleeNumber, etStartTime, etEndTime;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);

        etCustomerName = findViewById(R.id.etCustomerName);
        etCallerNumber = findViewById(R.id.etCallerNumber);
        etCalleeNumber = findViewById(R.id.etCalleeNumber);
        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        btnAdd = findViewById(R.id.btnAdd);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = etCustomerName.getText().toString();
                String callerNumber = etCallerNumber.getText().toString();
                String calleeNumber = etCalleeNumber.getText().toString();
                String startTime = etStartTime.getText().toString();
                String endTime = etEndTime.getText().toString();
                if(customerName.isEmpty()) {
                    Snackbar.make(view, "Invalid customer name", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!checkNumber(callerNumber)) {
                    Snackbar.make(view, "Invalid caller number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!checkNumber(calleeNumber)) {
                    Snackbar.make(view, "Invalid callee number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!validateDate(startTime)) {
                    Snackbar.make(view, "Invalid start time", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!validateDate(endTime)) {
                    Snackbar.make(view, "Invalid end time", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (!compareDates(startTime,endTime)) {
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
                        PhoneBill bill = new PhoneBill(customerName);
                        bill.addPhoneCall(new PhoneCall(callerNumber, calleeNumber, startTime, endTime));
                        if(!phoneBills.containsKey(customerName)) {
                            phoneBills.put(customerName, bill);
                        } else {
                            phoneBills.get(customerName).addPhoneCall(new PhoneCall(callerNumber, calleeNumber, startTime, endTime));
                        }
                        ois.close();
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(phoneBills);
                        oos.close();
                        fos.close();
                    } catch (Exception e) {
                        Snackbar.make(view, "Error occurred while writing to a file", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    Toast.makeText(AddCallActivity.this, new PhoneCall(callerNumber, calleeNumber, startTime, endTime).toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddCallActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public boolean checkNumber(String number) {
        return number.length() == 10;
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