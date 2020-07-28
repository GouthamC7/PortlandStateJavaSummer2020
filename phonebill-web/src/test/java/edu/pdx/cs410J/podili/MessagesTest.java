package edu.pdx.cs410J.podili;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MessagesTest {

  @Test
  public void missingRequiredParameter() {
    assertThat(Messages.missingRequiredParameter("customer"), containsString("missing"));
  }

  @Test
  public void noPhoneBillForCustomer() {
    assertThat(Messages.noPhoneBillForCustomer("customer"), containsString("No phone bill"));
  }

}
