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

  @Test
  public void getCalleeReturnsCalleeNumber() {
    PhoneCall call = initializePhoneCallOnject();
    call.getCaller();
    assertThat(call.getCallee(),equalTo("222-333-4444"));
  }

  @Test
  public void getCallerReturnsCallerNumber() {
    PhoneCall call = initializePhoneCallOnject();
    call.getCaller();
    assertThat(call.getCaller(),equalTo("111-222-3333"));
  }


  private PhoneCall initializePhoneCallOnject() {
    return new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00", "09/09/2019 11:00");
  }
  

  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
    PhoneCall call = initializePhoneCallOnject();
    assertThat(call.getStartTime(), is(nullValue()));
  }
  
}
