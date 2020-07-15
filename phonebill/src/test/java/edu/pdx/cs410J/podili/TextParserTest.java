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
        assertThat(String.valueOf(parser.validateDate(new String[]{"11/11/2011", "12:12"})),true);
    }

    @Test
    public void phoneBillFromTextFileContainsCustomerName() throws ParserException, FileNotFoundException {
        File f = new File("Goutham.txt");
        FileReader fr=new FileReader(f);
        TextParser parser = new TextParser(fr, "Goutham");
        PhoneBill bill = parser.parse();
        assertThat(bill.getCustomer(), equalTo("Goutham"));
    }
}
