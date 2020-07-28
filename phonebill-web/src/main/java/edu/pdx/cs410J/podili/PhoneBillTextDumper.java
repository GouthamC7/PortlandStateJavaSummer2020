package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneBillTextDumper implements PhoneBillDumper<PhoneBill> {
    private final PrintWriter writer;
    public String startTime = "";
    public String endTime = "";

    PhoneBillTextDumper(PrintWriter writer) {
        this.writer = writer;

    }

    PhoneBillTextDumper(PrintWriter writer, String startTime, String endTime) {
        this.writer = writer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void dump(PhoneBill bill) throws IOException {

        if(this.startTime.isEmpty()) {
            this.writer.println(bill.getCustomer());
            for(PhoneCall call : bill.getPhoneCalls()) {
                this.writer.println(call.getCaller()+","+ call.getCallee()+","+call.getStart()+","+call.getEnd());
            }
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                Date date1 = sdf.parse(this.startTime);
                Date date2 = sdf.parse(this.endTime);
                //System.out.println("Start date from customer ---- " + date1);
                //System.out.println("End date from customer ---- " + date2);
                /**if (date1.compareTo(date2) > 0) {
                    throw new InvalidArgumentException("Phone call's end time is before start time");
                }*/
                this.writer.println(bill.getCustomer());
                for(PhoneCall call : bill.getPhoneCalls()) {
                    Date temp = call.getStartTime();
                    //System.out.println("current date from customer ---- " + temp);
                    //System.out.println(date1.compareTo(temp) + "||------||" + date2.compareTo(temp));
                    if(date1.compareTo(temp) <= 0 && date2.compareTo(temp) >= 0) {
                        this.writer.println(call.getCaller()+","+ call.getCallee()+","+call.getStart()+","+call.getEnd());
                    }
                    //this.writer.println(call.getCaller()+","+ call.getCallee()+","+call.getStartTime()+","+call.getEndTime());
                }

            } catch (Exception e) {
                //throw new InvalidArgumentException("Please enter valid date");
            }
        }

        /**this.writer.println(bill.getCustomer());

        for(PhoneCall call : bill.getPhoneCalls()) {
            this.writer.println(call.getCaller());
        }*/
    }
}
