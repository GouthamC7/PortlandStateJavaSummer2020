package edu.pdx.cs410J.podili;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PhoneBillPrettyPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void printingToStandardOut() throws IOException {
        PhoneBill bill = new PhoneBill("goutham");
        bill.addPhoneCall(new PhoneCall("111-222-3333","222-333-4444","11/11/2011 10:00 AM","11/11/2011 11:00 AM"));
        PrintWriter pw = new PrintWriter(System.out, true);
        PhoneBillPrettyPrinter pretty = new PhoneBillPrettyPrinter(pw);
        pretty.dump(bill);
        assertThat(outContent.toString(), containsString("goutham"));
    }
}
