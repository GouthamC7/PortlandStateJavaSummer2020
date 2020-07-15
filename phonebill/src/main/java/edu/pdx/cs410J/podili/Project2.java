package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.ParserException;

import java.io.*;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project2 {

  /**
   * prints the content of readme.txt to console and exits.
   * Throws Exception if file is missing.
   */

  public  static  void readMe() {
    try {
      InputStream readme = Project2.class.getResourceAsStream("README.txt");
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

    //String[] args = new String[]{"Goutham","111-222-3333","999-333-4444","09/09/2020","13:00","09/09/2020","13:40"};
    //-textFile "phonebill/Goutham.txt" -print Goutham 111-111-1111 222-222-2222 11/11/1111 12:30 12/21/2012 11:30
    int printFlag = 0;
    int options = 0;
    int textFlag = 0;
    String fileName="";
    String customerName="";
    PhoneBill bill;
    PhoneCall call;

    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length < 7) {
      for(int i = 0; i < args.length; i++) {
        if(args[i].equals("-README")) {
          readMe();
        }
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length > 11) {
      for(int i = 0; i < 4; i++) {
        if(args[i].equals("-README")) {
          readMe();
        }
      }
      System.err.println("Too many command line arguments");
      System.exit(1);
    } else {
      for(int i = 0; i < 4; i++) {
        if(args[i].equals("-README")) {
          readMe();
        } else if (args[i].equals("-print")) {
          printFlag = 1;
          options++;
        } else {
          //if -textfile option is present
          if(args[i].equals("-textFile")) {
            fileName = args[++i];
            textFlag = 1;
            options = options + 2;
          }
        }
      }
      if((args.length-options) != 7) {
        System.err.println("Invalid command line arguments");
        System.exit(1);
      }
      String name = args[options++];
      if(name.startsWith("-")) {
        System.err.println("Invalid option");
        System.exit(1);
      }
      call = new PhoneCall(args[options++],args[options++],args[options++]+" "+args[options++],args[options++]+" "+args[options++]);
      bill = new PhoneBill(name, call);
      String checkArguments = bill.getCustomer()+call.getCallee()+call.getCaller()+call.getStartTimeString()+call.getEndTimeString();
      if(textFlag == 1) {

        TextDumper dumper = new TextDumper(fileName);
        try {
        File f = new File(fileName);

        // If given file exists
        if(f.exists()) {
          FileReader fr=new FileReader(f);
          TextParser parser = new TextParser(fr, name);
          parser.parse();
        }

          dumper.dump(bill);
        } catch (Exception ex) {
            System.err.println("Error while parsing");
            System.exit(1);
        }
      }
      if(printFlag == 1) {
        System.out.println(call.toString());
      }
      System.exit(0);

    }

    /**PhoneCall call;
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
      call = new PhoneCall(args[options++],args[options++],args[options++]+" "+args[options++],args[options++]+" "+args[options++]);
      bill = new PhoneBill(args[0], call);
      String checkArguments = bill.getCustomer()+call.getCallee()+call.getCaller()+call.getStartTimeString()+call.getEndTimeString();
      System.exit(0);
    } */

  }

}