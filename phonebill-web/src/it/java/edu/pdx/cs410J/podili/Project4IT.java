package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.podili.PhoneBillRestClient.PhoneBillRestException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    @Test
    public void test2ReadmeOptionPrintsToOutput() {
        MainMethodResult result = invokeMain( Project4.class, "-README" );
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Goutham"));
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString("Missing"));
    }

    @Test
    public void test3CustomerWithNoBill() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"abc" );
        //assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString("No phone bill for customer abc"));
    }

    @Test
    public void test4PostingBill() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"xyz","111-222-3333","222-333-4444","11/11/2011","11:00","AM",
                "11/11/2011","11:30","AM");
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardError();
        assertThat(out, out, containsString(""));
    }

    @Test
    public void test5CustomerWithBill() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"xyz","111-222-3333","222-333-4444","11/11/2011","11:00","AM",
                "11/11/2011","11:30","AM");
        MainMethodResult result2 = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"xyz" );
        assertThat(result2.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result2.getTextWrittenToStandardOut();
        assertThat(out, out, containsString("xyz"));
    }

    @Test
    public void test6BillWithInRange() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"xyz","111-222-3333","222-333-4444","11/11/2011","11:00","AM",
                "11/11/2011","11:30","AM");
        MainMethodResult result2 = invokeMain( Project4.class, "-host",HOSTNAME,"-port", PORT,"-search","xyz","10/10/2010","10:00","AM","11/11/2013","10:00","AM" );
        assertThat(result2.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result2.getTextWrittenToStandardOut();
        assertThat(out, out, containsString("xyz"));
    }

    @Test
    public void test7PostingBillWithPrint() {
        MainMethodResult result = invokeMain( Project4.class,"-print", "-host",HOSTNAME,"-port", PORT,"xyz","111-222-3333","222-333-4444","11/11/2011","11:00","AM",
                "11/11/2011","11:30","AM");
        assertThat(result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString("111-222-3333"));
    }

    @Test
    public void readMEReadsAndExitsWith0() {
        MainMethodResult result = invokeMain( Project4.class,"-README");
        assertThat(result.getExitCode(), equalTo(0));
    }




    /**
    @Test
    public void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatWordCount(0)));
    }

    @Test(expected = PhoneBillRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "WORD";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);

        } catch (UncaughtExceptionInMain ex) {
            throw ex.getCause();
        }
    }

    @Test
    public void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, word, definition );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
    } */
    /**
    @Test
    public void test0RemoveAllMappings() throws IOException {
        PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
        client.removeAllPhoneBills();
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        //assertThat(result.getExitCode(), equalTo(1));
        //assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }

    @Test
    public void test3UnknownPhoneBillIssuesUnknownPhoneBillError() throws Throwable {
        String customer = "Customer";
        MainMethodResult result = invokeMain(Project4.class, HOSTNAME, PORT, customer);
        //assertThat(result.getTextWrittenToStandardError(), containsString("No phone bill for customer " + customer));
        //assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void test4AddPhoneCall() {
        String customer = "Customer";
        String caller = "234-567-8901";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, customer, caller );
        //assertThat(result.getTextWrittenToStandardOut(), equalTo(""));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void test5PhoneBillIsPrettyPrinted() {
        String customer = "Customer";
        String caller = "234-567-8901";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, customer );
        assertThat(result.getExitCode(), equalTo(0));
        String pretty = result.getTextWrittenToStandardOut();
        //assertThat(pretty, containsString(customer));
        //assertThat(pretty, containsString("  " + caller));
    } */
}