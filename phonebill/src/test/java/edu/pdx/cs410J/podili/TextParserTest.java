package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextParserTest {

    @Test
    public void validateNumberReturnsTrueForValidNumber() {
        TextParser parser = new TextParser("","");
        assertThat(String.valueOf(parser.validateNumber("111-222-3333")),true);
    }

    @Test
    public void validateDateReturnsTrueForValidDate() {
        TextParser parser = new TextParser("","");
        assertThat(parser.validateDate("11/11/2011 11:00 PM"), equalTo(true));
        //assertThat(String.valueOf(parser.validateDate(new String[]{"11/11/2011", "12:12"})),true);
    }

    @Test
    public void parseReturnsBill() throws IOException, ParserException {
        PhoneCall call = new PhoneCall("123-123-1234","123-123-1234","11/11/2010 1:30 AM","11/11/2010 1:30 AM");
        PhoneBill bill = new PhoneBill("dumpertest",call);
        TextDumper dumper = new TextDumper("dumpertest.txt",bill);
        dumper.dump(bill);
        File f = new File("dumpertest.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;
        line = br.readLine();
        assertThat(line, containsString("dumpertest"));
        br.close();
        File f1 = new File("dumpertest.txt");
        FileReader fr1 = new FileReader(f1);
        TextParser tp = new TextParser(fr1, "dumpertest");
        assertThat(tp.parse().getCustomer(), containsString("dumpertest"));
        f.delete();
    }


}
