package edu.pdx.cs410J.podili;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  /**
   * prints the content of readme.txt to console and exits.
   * Throws Exception if file is missing.
   */

  public  static  void readMe() {
    try {
      InputStream readme = Project1.class.getResourceAsStream("README.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      while (line!=null) {
        System.out.println(line);
        line = reader.readLine();
      }
    } catch(Exception e) {
      System.out.println("README does not exist");
      System.exit(1);
    }
    System.exit(0);
  }

  /**
   * checks for valid number number of arguments and
   * creates phonebill and phonecall objects for valid inputs
   * @param args contains options and arguments specified by user.
   */

  public static void main(String[] args) {
    PhoneCall call;
    PhoneBill bill;
    int printFlag = 0;
    int options = 0;
    //PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length == 1) {
      if(args[0].equals("-README")) {
        readMe();
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length <7) {
      if(args[0].equals("-README") || args[1].equals("-README")) {
        readMe();
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length >9) {
      if(args[0].equals("-README") || args[1].equals("-README")) {
        readMe();
      }
      System.err.println("Too many arguments");
      System.exit(1);
    } else {
      if(args[0].equals("-README") || args[1].equals("-README")) {
        readMe();
      }
      if(args[0].equals("-print") || args[1].equals("-print")) {
        options++;
        printFlag = 1;
      }
      if((args.length-options) != 7) {
        System.err.println("Too many arguments");
        System.exit(1);
      }
      if(printFlag == 1) {
        options++;
        call = new PhoneCall(args[options++],args[options++],args[options++]+" "+args[options++],args[options++]+" "+args[options++]);
        bill = new PhoneBill(args[1], call);
        String checkArguments = bill.getCustomer()+call.getCallee()+call.getCaller()+call.getStartTimeString()+call.getEndTimeString();
        System.out.println(call.toString());
        System.exit(0);
      }
      options++;
      call = new PhoneCall(args[options++],args[options++],args[options++]+args[options++],args[options++]+args[options++]);
      bill = new PhoneBill(args[0], call);
      String checkArguments = bill.getCustomer()+call.getCallee()+call.getCaller()+call.getStartTimeString()+call.getEndTimeString();
      System.exit(0);
    }

  }

}