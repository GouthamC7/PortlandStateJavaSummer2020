package edu.pdx.cs410J.podili;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextDumperTest {

    /**
    @Test
    public void dumpToValidLocationExitsWithStatus0() throws IOException {
        PhoneCall call = new PhoneCall("123-123-1234","123-123-1234","11/11/2010 12:30","11/11/2010 12:30");
        PhoneBill bill = new PhoneBill("dumpertest",call);
        TextDumper dumper = new TextDumper("dumpertest.txt",bill);
        dumper.dump(bill);
        File f = new File("dumpertest.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        line = br.readLine();
        assertThat(line, containsString("dumpertest"));

    }**/



}
