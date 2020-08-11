package edu.pdx.cs410j.podili.phonebill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    EditText etCustomerName, etCallerNumber, etCalleeNumber, etStartDate, etStartTime, etEndDate, etEndTime;
    Button btnAdd;

    @Override
    public View onCreateView(

            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        /**
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        etCustomerName = v.findViewById(R.id.etCustomerName);
        etCallerNumber = v.findViewById(R.id.etCallerNumber);
        etCalleeNumber = v.findViewById(R.id.etCalleeNumber);
        etStartDate = v.findViewById(R.id.etStartDate);
        etStartTime = v.findViewById(R.id.etStartTime);
        etEndDate = v.findViewById(R.id.etEndDate);
        etEndTime = v.findViewById(R.id.etEndTime);
        btnAdd = v.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bill added successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Inflate the layout for this fragment
        return v;*/

        return inflater.inflate(R.layout.fragment_first, container, false);
    }


}