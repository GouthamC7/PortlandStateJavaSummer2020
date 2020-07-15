

package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project2} main class.
 */
public class Project2ITBAckup extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    /**
     * Invokes the main method of {@link Project2} with the given arguments.




    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testingWithOneCommandLineArgumentWithoutREADME() {
        MainMethodResult result = invokeMain("Hello");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testingWithOneCommandLineArgumentWithREADME() {
        MainMethodResult result = invokeMain("-README");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardOut(), containsString("Printing read me"));
    }

    @Test
    public void testingLessNumberOfArgumentsWithoutREADME() {
        MainMethodResult result = invokeMain("-print","Goutham","111-222-3333");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testingLessNumberOfArgumentsWithREADME() {
        MainMethodResult result = invokeMain("-README","Goutham","111-222-3333");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardOut(), containsString("Printing read me"));
    }

    @Test
    public void testingMoreNumberOfArgumentsWithoutREADME() {
        MainMethodResult result = invokeMain("-print","-print","Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40","Hello");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Too many arguments"));
    }

    @Test
    public void testingMoreNumberOfArgumentsWithREADME() {
        MainMethodResult result = invokeMain("-print","-README","Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40","Hello");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardOut(), containsString("Printing read me"));
    }

    @Test
    public void validNumberOfArgumentsWithoutREADMEAndPrint() {
        MainMethodResult result = invokeMain("Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardError(), containsString("Too many arguments"));
    }

    @Test
    public void validNumberOfArgumentsWithoutREADMEAndWithPrint() {
        MainMethodResult result = invokeMain("-print","Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Phone call from 111-222-3333 to 222-333-4444 from 09/09/2020 13:30 to 09/09/2020 13:40"));
    }

    @Test
    public void validNumberOfArgumentsWithREADMEAndWithPrint() {
        MainMethodResult result = invokeMain("-README","-print","Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardOut(), containsString("Printing read me"));
    }

    @Test
    public void validNumberOfArgumentsWithoutPrintAndWithoutPrint() {
        MainMethodResult result = invokeMain("Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void emptyCustomerNameGivesError() {
        MainMethodResult result = invokeMain("-print","","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Name should not be empty"));
    }

    @Test
    public void invalidCallerNumberGivesError() {
        MainMethodResult result = invokeMain("-print","Goutham","1111-222-333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Caller Number"));
    }

    @Test
    public void invalidCalleeNumberGivesError() {
        MainMethodResult result = invokeMain("-print","Goutham","111-222-3333","2222-333-444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid Callee Number"));
    }

    @Test
    public void invalidStartTimeGivesError() {
        MainMethodResult result = invokeMain("-print","Goutham","111-222-3333","222-333-4444","09/09/20200"," 13:00","09/09/2020"," 13:40");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter valid date"));
    }

    @Test
    public void invalidEndTimeGivesError() {
        MainMethodResult result = invokeMain("-print","Goutham","111-222-3333","222-333-4444","09/09/2020"," 13:00","09/09/20202"," 13:40");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Please enter valid date"));
    }*/

}
