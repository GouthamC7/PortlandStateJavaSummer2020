package edu.pdx.cs410J.podili;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrettyPrinterTest {

    @Test
    public void dumpToValidLocationExitsWithStatus0() throws IOException {
        PhoneCall call = new PhoneCall("123-123-1234","123-123-1234","11/11/2010 1:30 AM","11/11/2010 1:30 AM");
        PhoneBill bill = new PhoneBill("dumpertest",call);
        PrettyPrinter dumper = new PrettyPrinter("prettytest.txt",bill);
        dumper.dump(bill);
        File f = new File("prettytest.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        line = br.readLine();
        assertThat(line, containsString("dumpertest"));
        br.close();
        f.delete();
    }
}
