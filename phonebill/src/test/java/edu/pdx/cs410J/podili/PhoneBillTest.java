package edu.pdx.cs410J.podili;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneBill} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */

public class PhoneBillTest {

    private PhoneCall initializePhoneCall() {
        return new PhoneCall("111-222-333", "222-333-4444", "12:00", "11:00");
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
        ArrayList<PhoneCall> savedCall = (ArrayList<PhoneCall>) bill.getPhoneCalls();
        assertThat(savedCall.get(0), equalTo(call));
    }

}
