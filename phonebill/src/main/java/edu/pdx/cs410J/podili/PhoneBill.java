package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
    @Override
    public String getCustomer() {
        return null;
    }

    @Override
    public void addPhoneCall(PhoneCall call) {

    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return null;
    }
}
