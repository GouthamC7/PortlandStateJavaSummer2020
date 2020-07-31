package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }



  /**@Test
  public void test0RemoveAllDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllDictionaryEntries();
  }

  @Test
  public void test1EmptyServerContainsNoDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    Map<String, String> dictionary = client.getAllDictionaryEntries();
    assertThat(dictionary.size(), equalTo(0));
  }

  @Test
  public void test2DefineOneWord() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String testWord = "TEST WORD";
    String testDefinition = "TEST DEFINITION";
    client.addDictionaryEntry(testWord, testDefinition);

    String definition = client.getDefinition(testWord);
    assertThat(definition, equalTo(testDefinition));
  }

  @Test
  public void test4MissingRequiredParameterReturnsPreconditionFailed() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    HttpRequestHelper.Response response = client.postToMyURL(Map.of());
    assertThat(response.getContent(), containsString(Messages.missingRequiredParameter("word")));
    assertThat(response.getCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
  } */

  @Test
  public void test0RemoveAllPhoneBills() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllPhoneBills();
  }

  @Test
  public void test1NonexistentPhoneBillThrowsException() throws IOException, ParserException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    try {
      client.getPhoneBill("Dave");
      fail("Should have thrown a PhoneBillRestException");

    } catch (PhoneBillRestClient.PhoneBillRestException ex) {
      assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
    }
  }

  @Test
  public void test2AddPhoneCall() throws IOException, ParserException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String customer = "Customer";
    String caller = "123-456-7890";
    String calleePhoneNumber = "503-123-4568";
    String startDate = "11/11/2011 10:00 AM";
    String endDate = "11/11/2011 11:00 AM";
    client.addPhoneCall(customer, caller, calleePhoneNumber,startDate,endDate);


    PhoneBill phoneBill = client.getPhoneBill(customer);
    assertThat(phoneBill.getCustomer(), equalTo(customer));

    //PhoneCall phoneCall = phoneBill.getPhoneCalls().iterator().next();
    //assertThat(phoneCall.getCaller(), equalTo(caller));
  }
  
}
