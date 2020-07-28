package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PhoneBillTextParserTest {

    @Test
    public void parseBill() throws ParserException {
        String content = "goutham"+"\n"+"111-222-3333,222-333-4444,11/11/2011 11:00 AM, 12/12/2012 11:00 AM";
        PhoneBillTextParser parser = new PhoneBillTextParser(new StringReader(content));
        PhoneBill bill = parser.parse();
        assertThat(bill.getCustomer(), containsString("goutham"));
    }
}
