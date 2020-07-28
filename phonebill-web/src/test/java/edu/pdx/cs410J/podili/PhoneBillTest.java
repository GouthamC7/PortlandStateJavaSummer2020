package edu.pdx.cs410J.podili;

import org.junit.Test;

import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneBillTest {

    private PhoneCall initializePhoneCall() {
        return new PhoneCall("111-222-3333", "222-333-4444", "11/11/11 11:11 AM", "11/11/11 11:11 PM");
    }

    @Test
    public void getCustomerReturnsCustomerName() {
        PhoneCall call = initializePhoneCall();
        PhoneBill bill = new PhoneBill("Goutham", call);
        assertThat(bill.getCustomer(),equalTo("Goutham"));
    }

    @Test
    public void getPhoneCallsReturnsCalls() {
        PhoneCall call = initializePhoneCall();
        PhoneBill bill = new PhoneBill("Goutham", call);
        TreeSet<PhoneCall> savedCall = (TreeSet<PhoneCall>) bill.getPhoneCalls();
        assertThat(savedCall.first(), equalTo(call));
    }

    @Test
    public void initializingPhoneBillWithName() {
        PhoneBill bill = new PhoneBill("Goutham");
        assertThat(bill.getCustomer(), equalTo("Goutham"));
    }

    @Test
    public void addingCallAddsItToList() {
        PhoneBill bill = new PhoneBill("Goutham");
        PhoneCall call = initializePhoneCall();
        bill.addPhoneCall(call);
        TreeSet<PhoneCall> savedCall = (TreeSet<PhoneCall>) bill.getPhoneCalls();
        assertThat(savedCall.first(), equalTo(call));
    }

}
