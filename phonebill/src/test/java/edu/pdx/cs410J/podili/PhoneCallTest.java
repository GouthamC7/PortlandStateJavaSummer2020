package edu.pdx.cs410J.podili;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneCallTest {

  @Test
  public void toStringContainsCaller() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.toString(), containsString("111-222-3333"));
  }

  @Test
  public void toStringContainsCallee() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.toString(), containsString("222-333-4444"));
  }

  @Test
  public void toStringContainsStartTime() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.toString(), containsString("12:00"));
  }

  @Test
  public void toStringContainsEndTime() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.toString(), containsString("11:00"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCallerNumberThrowsIllegalArgumentException() {
    PhoneCall call = new PhoneCall("111-222-333", "222-333-4444", "12:00", "11:00");
    call.getCaller();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCalleeNumberThrowsIllegalArgumentException() {
    PhoneCall call = new PhoneCall("111-222-3333", "222-333-444", "12:00", "11:00");
    call.getCallee();
  }

  /**@Test(expected = UnsupportedOperationException.class)
  public void getStartTimeStringNeedsToBeImplemented() {
    PhoneCall call = initializePhoneCallOnject();
    call.getStartTimeString();
  }*/

  private PhoneCall initializePhoneCallOnject() {
    return new PhoneCall("111-222-3333", "222-333-4444", "12:00", "11:00");
  }

  /**@Test
  public void initiallyAllPhoneCallsHaveTheSameCallee() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.getCallee(), containsString("222-333-4444"));
  }

  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
    PhoneCall call = initializePhoneCallOnject();
    //assertThat(call.getStartTime(), is(nullValue()));
  }*/
  
}
