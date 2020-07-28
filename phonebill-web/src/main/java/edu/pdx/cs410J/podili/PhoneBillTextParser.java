package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class PhoneBillTextParser implements PhoneBillParser<PhoneBill> {
    private final Reader reader;

    public PhoneBillTextParser(Reader reader) {
        this.reader = reader;
    }

    @Override
    public PhoneBill parse() throws ParserException {
        BufferedReader br = new BufferedReader(this.reader);
        try {
            String customer = br.readLine();
            if(customer.isEmpty()) {
                return null;
            }
            PhoneBill bill = new PhoneBill(customer);

            while (br.ready()) {
                String callString = br.readLine();
                if (callString == null) {
                    break;
                }
                String[] arguments = callString.split(",");
                bill.addPhoneCall(new PhoneCall(arguments[0], arguments[1], arguments[2], arguments[3]));
                /**if (caller == null) {
                    break;
                }
                bill.addPhoneCall(new PhoneCall(caller)); */
            }

            return bill;

        } catch (IOException e) {
            throw new ParserException("While parsing", e);
        }
    }
}
