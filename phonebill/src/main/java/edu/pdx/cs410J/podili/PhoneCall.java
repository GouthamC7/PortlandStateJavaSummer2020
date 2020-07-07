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
      throw new InvalidArgumentException("Invalid Caller Number");
    }
    return this.caller;
  }

  @Override
  public String getCallee() {
    String calleeString = this.callee;
    calleeString = calleeString.replace("-","");
    String regex = "\\d+";
    if(calleeString.length() != 10 || !calleeString.matches(regex)) {
      throw new InvalidArgumentException("Invalid Callee Number");
    }
    return this.callee;
  }

  @Override
  public String getStartTimeString() {
    String[] startTimeString = this.startTime.split(" ");
    validateDate(startTimeString);
    return this.startTime;
  }

  @Override
  public String getEndTimeString() {
    String[] startTimeString = this.endTime.split(" ");
    validateDate(startTimeString);
    return this.endTime;
  }

  private void validateDate(String[] startTimeString) {
    String dateString = startTimeString[0];
    String[] dateStringParts = dateString.split("/");
    if(dateStringParts.length != 3) {
      throw new InvalidArgumentException("Please enter valid date");
    }
    String timeString = startTimeString[1];
    String[] timeStringParts = timeString.split(":");
    if(timeStringParts.length != 2) {
      throw new InvalidArgumentException("Please enter valid date");
    }
    String regex = "\\d+";
    dateString = dateString.replace("/", "");
    timeString = timeString.replace(":", "");
    if (!(dateString.length() > 5 && dateString.length() < 9 && dateString.matches(regex)) ||
            !(timeString.length() > 1 && timeString.length() < 5 && timeString.matches(regex))) {
      throw new InvalidArgumentException("Please enter valid date");
    }
  }
}
