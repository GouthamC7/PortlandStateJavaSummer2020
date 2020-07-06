package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
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
        assertThat(result.getTextWrittenToStandardOut(), containsString("Printing object"));
    }

    @Test
    public void validNumberOfArgumentsWithREADMEAndWithPrint() {
        MainMethodResult result = invokeMain("-README","-print","Goutham","111-222-3333","222-333-4444","09/09/2020","13:30","09/09/2020","13:40");
        assertThat(result.getExitCode(), equalTo(0));
        //assertThat(result.getTextWrittenToStandardOut(), containsString("Printing read me"));
    }


}