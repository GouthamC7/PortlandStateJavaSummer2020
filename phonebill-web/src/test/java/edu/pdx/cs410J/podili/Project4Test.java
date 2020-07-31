package edu.pdx.cs410J.podili;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project4Test {

    /**@Test
    public void readmeCanBeReadAsResource() throws IOException {
        try (
                InputStream readme = Project4.class.getResourceAsStream("README.txt");
        ) {
            assertThat(readme, not(nullValue()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
            String line = reader.readLine();
            assertThat(line, containsString("Project3"));
        }
    } */

    @Test
    public void validNumberReturnsTrue() {
        Boolean value = Project4.validNumber("111-222-3333");
        assertThat(value, equalTo(true));
    }

    @Test
    public void validDateReturnsTrue() {
        Boolean value = Project4.checkDate("12/11/2011 11:00 AM");
        assertThat(value, equalTo(true));
    }

    @Test
    public void compareDatesReturnTrueForValidDates() {
        assertThat(Project4.compareDates("09/09/2018 12:00 AM", "09/09/2019 11:00 PM"), equalTo(true));
    }

}
