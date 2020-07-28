package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;

public class PhoneBillPrettyPrinter implements PhoneBillDumper<PhoneBill> {

    private final PrintWriter writer;

    /**
     * class constructor for initializing writer
     * @param pw print writer object
     */
    public PhoneBillPrettyPrinter(PrintWriter pw) {
        this.writer = pw;
    }

    /**
     * dumps the phonebill to the writer
     * @param bill contains phone bill
     * @throws IOException
     */
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
