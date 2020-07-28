package edu.pdx.cs410J.podili;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneCallTest {

    private PhoneCall initializePhoneCallOnject() {
        return new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
    }

    @Test
    public void compareDatesReturnTrueForValidDates() {
        PhoneCall call = initializePhoneCallOnject();
        assertThat(call.compareDates("09/09/2019 12:00 AM", "09/09/2019 11:00 PM"), equalTo(true));
    }

    @Test
    public void validDateReturnsDateIfValid() {
        PhoneCall call = initializePhoneCallOnject();
        assertThat(call.validateDate("09/09/2019 12:00 AM"), containsString("9/9/19, 12:00 AM"));
    }

    @Test
    public void getStartTimeReturnStartTime() {
        PhoneCall call = initializePhoneCallOnject();
        assertThat(call.getStartTimeString(), containsString("9/9/19, 12:00 AM"));
    }

    @Test
    public void toStringContainsEndTime() {
        PhoneCall call = initializePhoneCallOnject();
        assertThat(call.getEndTimeString(), containsString("11:00"));
    }

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

    @Test
    public void firstCallStartTimeIsEarlierReturnsMinus1() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        PhoneCall call2 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2020 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.compareTo(call2),equalTo(-1));

    }

    @Test
    public void SecondCallStartTimeIsEarlierReturns1() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        PhoneCall call2 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2018 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.compareTo(call2),equalTo(1));
    }

    @Test
    public void TwoCallsHavingSameStartTimeAndSameNumberReturns0() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        PhoneCall call2 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.compareTo(call2),equalTo(0));
    }

    @Test
    public void comapreDatesReturnTrueIfStartTimeIsEarlier() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.compareDates("09/09/2019 12:00 AM", "09/09/2019 11:00 PM"),equalTo(true));
    }

    @Test
    public void validateDatesReturnsDateInShortFormat() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.validateDate("09/09/2019 12:00 AM"), containsString("9/9/19, 12:00 AM"));
    }

    @Test
    public void getStartReturnsStartTime() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.getStart(), containsString("09/09/2019 12:00 AM"));
    }

    @Test
    public void getEndReturnsStartTime() {
        PhoneCall call1 = new PhoneCall("111-222-3333", "222-333-4444", "09/09/2019 12:00 AM", "09/09/2019 11:00 PM");
        assertThat(call1.getEnd(), containsString("09/09/2019 11:00 PM"));
    }

}

