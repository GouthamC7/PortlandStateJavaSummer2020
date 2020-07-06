package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private String customerName;
    ArrayList<PhoneCall> calls = new ArrayList<PhoneCall>();

    @Override
    public String getCustomer() {

        return this.customerName;
    }

    @Override
    public void addPhoneCall(PhoneCall call) {
        calls.add(call);
    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {

        return this.calls;
    }
}
