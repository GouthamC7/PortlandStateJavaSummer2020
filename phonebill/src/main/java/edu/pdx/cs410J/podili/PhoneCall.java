package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * class PhoneCall extends AbstractPhoneCall
 */

public class PhoneCall extends AbstractPhoneCall {

  /**
   * @param caller stores caller number
   * @param callee stores callee number
   * @param startTime stores start time of the call
   * @param endTime stores end time of the call
   */

  private final String caller;
  private final String callee;
  private final String startTime;
  private final String endTime;

  /**
   * class constructor
   * @param caller
   * @param callee
   * @param startTime
   * @param endTime
   */

  public PhoneCall(String caller, String callee, String startTime, String endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startTime = startTime;
    this.endTime = endTime;

  }

  /**
   * Validates caller number and throws InvalidArgumentException if number is invalid.
   * @return returns caller number
   */

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

  /**
   * Validates callee number and throws InvalidArgumentException if number is invalid.
   * @return returns callee number
   */

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

  /**
   * calls validateDate for validating start date
   * @return return start time of call
   */

  @Override
  public String getStartTimeString() {
    String[] startTimeString = this.startTime.split(" ");
    validateDate(startTimeString);
    return this.startTime;
  }

  /**
   * calls validateDate for validating end date
   * @return return end time of call
   */

  @Override
  public String getEndTimeString() {
    String[] startTimeString = this.endTime.split(" ");
    validateDate(startTimeString);
    return this.endTime;
  }

  /**
   * Validates date and time of the call and throws InvalidArgumentException
   * if date and time is invalid.
   * @param startTimeString array containing date and time of the call
   *
   */

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
