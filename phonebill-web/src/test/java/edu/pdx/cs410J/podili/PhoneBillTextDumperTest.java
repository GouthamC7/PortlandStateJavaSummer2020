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

public class PhoneBillTextDumperTest {

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
    public void dumpEverything() throws IOException {
        PhoneBill bill = new PhoneBill("goutham");
        bill.addPhoneCall(new PhoneCall("111-222-3333","222-333-4444","11/11/2011 10:00 AM","11/11/2011 11:00 AM"));
        PrintWriter pw = new PrintWriter(System.out, true);
        PhoneBillTextDumper dumper = new PhoneBillTextDumper(pw);
        dumper.dump(bill);
        assertThat(outContent.toString(), containsString("goutham"));
    }

    @Test
    public void dumpBetweenDates() throws IOException {
        PhoneBill bill = new PhoneBill("goutham");
        bill.addPhoneCall(new PhoneCall("111-222-3333","222-333-4444","11/11/2011 10:00 AM","11/11/2011 11:00 AM"));
        PrintWriter pw = new PrintWriter(System.out, true);
        PhoneBillTextDumper dumper = new PhoneBillTextDumper(pw,"09/09/2009 10:00 AM","20/20/2020 10:00 AM");
        dumper.dump(bill);
        assertThat(outContent.toString(), containsString("goutham"));
    }
}
