package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    /**
     * checks whether the number is valid or not
     * @param number conatins mobile number
     */
    public static boolean validNumber(String number) {
        String callerString = number;
        String[] callerStringParts = number.split("-");
        if(callerStringParts.length != 3 || callerStringParts[0].length() !=3 ||
                callerStringParts[1].length() !=3 || callerStringParts[2].length() !=4) {
            throw new InvalidArgumentException("Invalid Caller Number");
        }
        callerString = callerString.replace("-","");
        String regex = "\\d+";
        if(callerString.length() != 10 || !callerString.matches(regex)) {
            throw new InvalidArgumentException("Invalid mobile Number");
        }
        return true;
    }

    /**
     * checks whether date is valid or not
     * @param date contains date
     */
    public static boolean checkDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            throw new InvalidArgumentException("Please enter valid date");
        }
    }

    /**
     * checks whether start time is before end time
     */
    public static boolean compareDates(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date date1 = sdf.parse(start);
            Date date2 = sdf.parse(end);
            if (date1.compareTo(date2) > 0) {
                throw new InvalidArgumentException("End time is before start time");
            }
            return true;
        } catch (Exception e) {
            throw new InvalidArgumentException("Please enter valid date");
        }
    }

    /**
     * prints the readme to the console
     */
    public static void readMe() {
        PrintStream err = System.out;
        err.println("Name of the Assignment: Project 4");
        err.println();
        err.println("Author: Goutham Podili");
        err.println();
        err.println("Usage: java edu.pdx.cs410J.<login-id>.Project4 [options] <args>");
        err.println("options are -README, -host hostname, -port port, -search, -print");
        err.println("Arguments are customer, caller, callee, start date, end date.");
        err.println("when bill is specified it will add the bill to the server");
        err.println("when a customer name is specified it will print the calls made by the customer");
        err.println("when -search is specified with start date and end date it will print calls made ");
        err.println("between those days");

        System.exit(0);
    }

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String[] args) {

        //String[] args = new String[]{"localhost","8080","goutham"};

        String hostName = null;
        String portString = null;
        String customer = null;
        String caller = null;
        String callee = null;
        String startTime = null;
        String endTime = null;

        int port = 0;
        int options = 0;
        int printFlag = 0;
        int searchFlag = 0;
        String portNumber = "";

        if(args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        } else if(args.length < 5) {
            for(int i=0; i< args.length; i++) {
                if(args[i].equals("-README")) {
                    readMe();
                }
            }
            System.err.println("Missing command line arguments");
            System.exit(1);
        } else if(args.length>=5 && args.length<=6) {
            for(int i=0; i< args.length; i++) {
                if (args[i].equals("-README")) {
                    readMe();
                } else if (args[i].equals("-print")) {
                    printFlag = 1;
                    options++;
                } else if (args[i].equals("-search")) {
                    searchFlag = 1;
                    options++;
                } else if(args[i].equals("-host")) {
                    //System.out.println("Inside host");
                    hostName = args[++i];
                    //System.out.println(hostName);
                    options = options + 2;
                } else if(args[i].equals("-port")) {
                    //System.out.println("Inside port");
                    portNumber = args[++i];
                    options = options + 2;
                    try {
                        port = Integer.parseInt( portNumber );

                    } catch (NumberFormatException ex) {
                        usage("Port \"" + portNumber + "\" must be an integer");
                        return;
                    }
                }
            }
        }
            else {
            for (int i = 0; i < 7; i++) {
                if (args[i].equals("-README")) {
                    readMe();
                } else if (args[i].equals("-print")) {
                    printFlag = 1;
                    options++;
                } else if (args[i].equals("-search")) {
                    searchFlag = 1;
                    options++;
                } else if(args[i].equals("-host")) {
                    hostName = args[++i];
                    options = options + 2;
                } else if(args[i].equals("-port")) {
                    portNumber = args[++i];
                    options = options + 2;
                    try {
                        port = Integer.parseInt( portNumber );

                    } catch (NumberFormatException ex) {
                        usage("Port \"" + portNumber + "\" must be an integer");
                        return;
                    }
                }
            }
        }
        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
        if (args.length - options >= 1) {
            customer = args[options++];
        }
        else {
            usage("Invalid command line arguments");
            return;
        }

        try {
            if (args.length - options == 0) {
                try {
                    PhoneBill bill = client.getPhoneBill(customer);
                    //System.out.println("got bill" + bill);
                    PrintWriter pw = new PrintWriter(System.out, true);
                    PhoneBillPrettyPrinter pretty = new PhoneBillPrettyPrinter(pw);
                    pretty.dump(bill);

                } catch (ParserException e) {
                    System.err.println("Could not parse response!");
                    System.exit(1);

                } catch (PhoneBillRestClient.PhoneBillRestException ex) {
                    switch (ex.getHttpStatusCode()) {
                        case HttpURLConnection.HTTP_NOT_FOUND:
                            System.err.println("No phone bill for customer " + customer);
                            System.exit(1);
                            return;
                        default:

                    }
                }
            } else if (args.length - options == 6) {
                startTime = args[options++] +" "+ args[options++] +" "+ args[options++];
                endTime = args[options++] +" "+ args[options++] +" "+ args[options++];
                compareDates(startTime, endTime);
                //System.out.println("Get info of the customer based on name, start date and end date");
                try {
                    PhoneBill bill = client.getPhoneBillBetweenTime(customer, startTime, endTime);
                    //System.out.println("got bill" + bill);
                    if(bill == null) {
                        System.out.println("No call records found between the given dates");
                        System.exit(1);
                    }
                    //System.out.println("pretty printing");
                    PrintWriter pw = new PrintWriter(System.out, true);
                    PhoneBillPrettyPrinter pretty = new PhoneBillPrettyPrinter(pw);
                    pretty.dump(bill);

                } catch (ParserException e) {
                    System.err.println("Could not parse response!");
                    System.exit(1);

                } catch (PhoneBillRestClient.PhoneBillRestException ex) {
                    switch (ex.getHttpStatusCode()) {
                        case HttpURLConnection.HTTP_NOT_FOUND:
                            System.err.println("No phone bill for customer " + customer);
                            System.exit(1);
                            return;
                        default:

                    }
                }
            } else if (args.length - options == 8) {
                //System.out.println("post the customer");
                caller = args[options++];
                validNumber(caller);
                callee = args[options++];
                validNumber(callee);
                startTime = args[options++] +" "+ args[options++] +" "+ args[options++];
                checkDate(startTime);
                endTime = args[options++] +" "+ args[options++] +" "+ args[options++];
                checkDate(endTime);
                compareDates(startTime, endTime);
                client.addPhoneCall(customer, caller, callee, startTime, endTime);
                if(printFlag == 1) {
                    System.out.println("Phone call from "+caller+" to "+callee+" from "+startTime+" to "+endTime);
                }

            } else {
                System.err.println("Invalid command line arguments");
                System.exit(1);
            }
        } catch (IOException e) {
            error("While contacting server: " + e);
            return;
        }
        System.exit(0);

    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                    response.getCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }


}