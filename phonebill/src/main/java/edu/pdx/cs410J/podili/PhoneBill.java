package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

/**
 * class PhoneBill extends AbstractPhoneBill and implements all the methods.
 */

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {


    private String customerName;
    TreeSet<PhoneCall> calls = new TreeSet<>();

    /**
     * class constructor for storing the name and information about calls
     * @param name contains customer name
     */
    public PhoneBill(String name) {
        this.customerName = name;
    }

    /**
     * class constructor for storing the name and information about single call
     * @param name contains customer name
     * @param call PhoneCall object
     */

    public PhoneBill(String name, PhoneCall call) {
        this.customerName = name;
        addPhoneCall(call);
    }

    /**
     * class constructor for storing the name and information about multiple calls
     * @param name contains customer name
     * @param calls contains calls
     */
    public PhoneBill(String name, TreeSet<PhoneCall> calls) {
        this.customerName = name;
        this.calls.addAll(calls);
    }

    /**
     * This method throws InvalidArgumentException if name is empty,
     * else returns the customer name.
     * @return name of the customer
     */

    @Override
    public String getCustomer() {

        if(this.customerName.isEmpty()) {
            throw new InvalidArgumentException("Name should not be empty");
        }
        return this.customerName;
    }

    /**
     * adds call information to the PhoneBill object.
     * @param call object of PhoneCall
     */

    @Override
    public void addPhoneCall(PhoneCall call) {
        calls.add(call);
    }

    /**
     * Returns the list of calls
     * @return list of calls
     */

    @Override
    public Collection<PhoneCall> getPhoneCalls() {

        return this.calls;
    }
}
