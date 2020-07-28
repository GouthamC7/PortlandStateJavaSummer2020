package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class PhoneCall extends AbstractPhoneCall
 */

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

    /**
     * Validates caller number and throws InvalidArgumentException if number is invalid.
     * @return returns caller number
     */

    @Override
    public String getCaller() {
        String callerString = this.caller;
        String[] callerStringParts = this.caller.split("-");
        if(callerStringParts.length != 3 || callerStringParts[0].length() !=3 ||
                callerStringParts[1].length() !=3 || callerStringParts[2].length() !=4) {
            throw new InvalidArgumentException("Invalid Caller Number");
        }
        callerString = callerString.replace("-","");
        String regex = "\\d+";
        if(callerString.length() != 10 || !callerString.matches(regex)) {
            throw new InvalidArgumentException("Invalid Caller Number");
        }
        return this.caller;
    }

    /**
     * Validates callee number and throws InvalidArgumentException if number is invalid.
     * @return returns callee number
     */

    @Override
    public String getCallee() {
        String calleeString = this.callee;
        String[] calleeStringParts = this.callee.split("-");
        if(calleeStringParts.length != 3 || calleeStringParts[0].length() !=3 ||
                calleeStringParts[1].length() !=3 || calleeStringParts[2].length() !=4) {
            throw new InvalidArgumentException("Invalid Callee Number");
        }
        calleeString = calleeString.replace("-","");
        String regex = "\\d+";
        if(calleeString.length() != 10 || !calleeString.matches(regex)) {
            throw new InvalidArgumentException("Invalid Callee Number");
        }
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
        String time = validateDate(this.endTime);
        compareDates(this.startTime, this.endTime);
        return time;
    }

    /**
     *
     * @return start date object
     */
    @Override
    public Date getStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        try {
            Date date = sdf.parse(this.startTime);
            return date;
        } catch (ParseException e) {
            throw new InvalidArgumentException("Please enter valid date");
        }
    }

    /**
     *
     * @return end date object
     */
    @Override
    public Date getEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        try {
            Date date = sdf.parse(this.startTime);
            return date;
        } catch (ParseException e) {
            throw new InvalidArgumentException("Please enter valid date");
        }
    }

    /**
     * Validates date and time of the call and throws InvalidArgumentException
     * if date and time is invalid.
     * @param startTimeString array containing date and time of the call
     *
     */

    public String validateDate(String startTimeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            sdf.setLenient(false);
            sdf.parse(startTimeString);
            DateFormat dateFormatter;
            dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            //DateFormat today = new Date();
            String dateOut = dateFormatter.format(sdf.parse(startTimeString));
            return dateOut;
        } catch (Exception e) {
            // TODO: handle exception
            throw new InvalidArgumentException("Please enter valid date");
        }

    }

    public boolean compareDates(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);
            if (date1.compareTo(date2) > 0) {
                throw new InvalidArgumentException("Phone call's end time is before start time");
            }
            return true;
        } catch (Exception e) {
            throw new InvalidArgumentException("Please enter valid date");
        }
    }

    /**
     * used to compare the start time between two calls and sort accordingly
     * @param call contains call info
     * @return 1,-1 or 0
     */
    @Override
    public int compareTo(PhoneCall call) {
        Date date1;
        Date date2;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            date1 = sdf.parse(this.startTime);
            date2 = call.getStartTime();
        } catch (Exception e) {
            throw new InvalidArgumentException("Invalid date");
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
