package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private String customerName;
    ArrayList<PhoneCall> calls = new ArrayList<PhoneCall>();

    public PhoneBill(String name, PhoneCall call) {
        this.customerName = name;
        addPhoneCall(call);
    }

    @Override
    public String getCustomer() {
        if(this.customerName.isEmpty()) {
            throw new IllegalArgumentException("Name should not be empty");
        }
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
