package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {

  private final String caller;
  private final String callee;
  private final String startTime;
  private final String endTime;

  public PhoneCall(String caller, String callee, String startTime, String endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startTime = startTime;
    this.endTime = endTime;

  }

  @Override
  public String getCaller() {
    String callerString = this.caller;
    callerString = callerString.replace("-","");
    String regex = "\\d+";
    if(callerString.length() != 10 || !callerString.matches(regex)) {
      throw new IllegalArgumentException("Invalid Caller Number");
    }
    return this.caller;
  }

  @Override
  public String getCallee() {
    String calleeString = this.callee;
    calleeString = calleeString.replace("-","");
    String regex = "\\d+";
    if(calleeString.length() != 10 || !calleeString.matches(regex)) {
      throw new IllegalArgumentException("Invalid Callee Number");
    }
    return this.callee;
  }

  @Override
  public String getStartTimeString() {

    return this.startTime;
  }

  @Override
  public String getEndTimeString() {

    return this.endTime;
  }
}
