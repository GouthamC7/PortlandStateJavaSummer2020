package edu.pdx.cs410j.podili.phonebill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CallListAdapter extends ArrayAdapter<PhoneCall> {



    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */


    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CallListAdapter(Context context, int resource, ArrayList<PhoneCall> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String caller = getItem(position).getCaller();
        String callee = getItem(position).getCallee();
        String startTime = getItem(position).getStartTimeString();
        String endTime = getItem(position).getEndTimeString();


        PhoneCall call = new PhoneCall(caller,callee,startTime,endTime);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvcallee = (TextView) convertView.findViewById(R.id.tv2);
        TextView tvcaller = (TextView) convertView.findViewById(R.id.tv1);
        TextView tvstartTime = (TextView) convertView.findViewById(R.id.tv3);
        TextView tvendTime = (TextView) convertView.findViewById(R.id.tv4);
        TextView tvduration = (TextView) convertView.findViewById(R.id.tv5);

        tvcaller.setText("Caller : " + caller);
        tvcallee.setText("Callee : " + callee);
        tvstartTime.setText("From : " + startTime);
        tvendTime.setText("To : " + endTime);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date date1 = sdf.parse(startTime);
            Date date2 = sdf.parse(endTime);
            long result = ((date2.getTime() / 60000) - (date1.getTime() / 60000));
            tvduration.setText("Call duration : " + String.valueOf(result) + " minutes");
        } catch (Exception e) {

        }

        return convertView;
    }


}
