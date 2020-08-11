package edu.pdx.cs410j.podili.phonebill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall implements java.lang.Comparable<PhoneCall> {


    private final String caller;
    private final String callee;
    private final String startTime;
    private final String endTime;

    /**
     * class constructor create object
     *
     * @param caller stores caller number
     * @param callee stores callee number
     * @param startTime stores start time of the call
     * @param endTime stores end time of the call
     */

    public PhoneCall(String caller, String callee, String startTime, String endTime) {
        this.caller = caller;
        this.callee = callee;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public String validateDate(String startTimeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            sdf.setLenient(false);
            sdf.parse(startTimeString);
            DateFormat dateFormatter;
            dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            //DateFormat today = new Date();
            String dateOut = dateFormatter.format(sdf.parse(startTimeString));
            return dateOut;
        } catch (Exception e) {
            // TODO: handle exception
            //throw new InvalidArgumentException("Please enter valid date");
        }
        return startTimeString;
    }

    /**
     * returns start time in original format
     * @return start time
     */
    public String getStart() {
        return this.startTime;
    }

    /**
     * returns end time in original format
     * @return end time
     */
    public String getEnd() {
        return this.endTime;
    }


    @Override
    public String getCaller() {
        return  this.caller;
    }

    @Override
    public String getCallee() {
        return this.callee;
    }

    /**
     * calls validateDate for validating start date
     * @return return start time of call
     */

    @Override
    public String getStartTimeString() {
        return validateDate(this.startTime);

    }

    /**
     * calls validateDate for validating end date
     * @return return end time of call
     */

    @Override
    public String getEndTimeString() {
        return validateDate(this.endTime);
    }




    @Override
    public int compareTo(PhoneCall call) {
        Date date1 = null;
        Date date2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            date1 = sdf.parse(this.startTime);
            date2 = sdf.parse(call.getStartTimeString());
        } catch (Exception e) {
            //throw new InvalidArgumentException("Invalid date");
        }
        if (date1.compareTo(date2) > 0) {
            return 1;
        } else if (date1.compareTo(date2) < 0) {
            return -1;
        } else {
            String caller1String = this.caller.replace("-","");
            String caller2String = call.getCaller().replace("-","");
            long caller1 = Long.parseLong(caller1String);
            long caller2 = Long.parseLong(caller2String);
            if(caller2 < caller1) {
                return 1;
            } else if (caller2 > caller1) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
