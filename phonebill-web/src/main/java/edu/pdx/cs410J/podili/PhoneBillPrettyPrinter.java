package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;

public class PhoneBillPrettyPrinter implements PhoneBillDumper<PhoneBill> {
    private final PrintWriter writer;

    public PhoneBillPrettyPrinter(PrintWriter pw) {
        this.writer = pw;
    }

    @Override
    public void dump(PhoneBill bill) throws IOException {
        // this.writer.println(bill.getCustomer());
        String customer = bill.getCustomer();
        for(PhoneCall call : bill.getPhoneCalls()) {
            this.writer.println("Name: "+customer+", Caller: "+call.getCaller()+", Callee: "+call.getCallee()
            +", Start time: "+call.getStart()+", End time: "+call.getEnd());
        }

        this.writer.flush();
    }
}
