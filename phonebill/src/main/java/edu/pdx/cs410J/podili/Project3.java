package edu.pdx.cs410J.podili;

import java.io.*;
import java.util.TreeSet;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project3 {

  /**
   * prints the content of readme.txt to console and exits.
   * Throws Exception if file is missing.
   */

  public  static  void readMe() {
    try {
      InputStream readme = Project3.class.getResourceAsStream("README.txt");
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

  public static void main(String[] args) throws IOException {

    //String[] args = new String[]{"-textFile","phonebill/test1.txt","-pretty","-","-print","Goutham","999-222-3333","999-333-4444","09/09/2000","3:00","AM","09/09/2020","3:40","PM"};

    /**PhoneCall call1 = new PhoneCall("111-222-3332","999-333-4444","09/09/2020 1:01 AM","09/09/2020 3:40 AM");
    PhoneCall call2 = new PhoneCall("111-222-3333","999-333-4444","09/09/2021 1:00 AM","09/09/2021 2:40 AM");
    PhoneCall call3 = new PhoneCall("111-222-3333","999-333-4444","09/09/2024 1:00 AM","09/09/2024 1:40 AM");
    PhoneCall call4 = new PhoneCall("111-222-3333","999-333-4444","09/09/2020 1:00 AM","09/09/2020 1:20 AM");
    TreeSet<PhoneCall> sorted = new TreeSet<>();
    sorted.add(call1);
    sorted.add(call2);
    sorted.add(call3);
    sorted.add(call4);
    for(PhoneCall str:sorted){
      System.out.println(str.getCaller()+ "---" +str.getStartTimeString());
    }
    PhoneBill pb = new PhoneBill("Goutham", sorted);
    PrettyPrinter p = new PrettyPrinter("phonebill/prettytest.txt");
    p.dump(pb);
  System.out.println("-------");*/
    int printFlag = 0;
    int options = 0;
    int textFlag = 0;
    int prettyFlag = 0;
    String fileName="";
    String prettyFileName="";
    String customerName="";
    String caller;
    String callee;
    String startDateString;
    String endDateString;
    PhoneBill bill;
    PhoneCall call;
    PhoneBill totalBill = null;

    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length < 9) {
      for(int i = 0; i < args.length; i++) {
        if(args[i].equals("-README")) {
          readMe();
        }
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length > 15) {
      for(int i = 0; i < 6; i++) {
        if(args[i].equals("-README")) {
          readMe();
        }
      }
      System.err.println("Too many command line arguments");
      System.exit(1);
    } else {
      for(int i = 0; i < 6; i++) {
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
          if(args[i].equals("-pretty")) {
            prettyFileName = args[++i];
            prettyFlag = 1;
            options = options + 2;
          }
        }
      }
      if((args.length-options) != 9) {
        System.err.println("Invalid command line arguments");
        System.exit(1);
      }
      String name = args[options++];
      if(name.startsWith("-")) {
        System.err.println("Invalid option");
        System.exit(1);
      }
      caller = args[options++];
      callee = args[options++];
      startDateString = args[options++] +" "+ args[options++] +" "+ args[options++];
      endDateString = args[options++] +" "+ args[options++] +" "+ args[options++];
      //System.out.println(startDateString);
      //System.out.println(endDateString);
      call = new PhoneCall(caller,callee,startDateString,endDateString);
      bill = new PhoneBill(name, call);
      String checkArguments = bill.getCustomer()+call.getCallee()+call.getCaller()+call.getStartTimeString()+call.getEndTimeString();
      if(textFlag == 1) {

        TextDumper dumper = new TextDumper(fileName);
        //System.out.println(fileName);
        try {
          File f = new File(fileName);

          // If given file exists
          if(f.exists()) {
            FileReader fr=new FileReader(f);
            TextParser parser = new TextParser(fr, name);
            totalBill = parser.parse();
          }

          dumper.dump(bill);
        } catch (Exception ex) {
          System.err.println("Error while parsing the file");
          System.exit(1);
        }
      }
      if(totalBill == null) {
        totalBill = new PhoneBill(name);
      }
      totalBill.addPhoneCall(call);

      //pretty falg
      //System.out.println("pretty printing");
      if(prettyFlag == 1) {
        PrettyPrinter pretty = new PrettyPrinter(prettyFileName);
        //System.out.println("called pretty printing");
        pretty.dump(totalBill);
      }

      if(printFlag == 1) {
        System.out.println(call.toString());
      }
      System.exit(0);
    }



    //String[] args = new String[]{"Goutham","111-222-3333","999-333-4444","09/09/2020","13:00","09/09/2020","13:40"};
    //-textFile "phonebill/Goutham.txt" -print Goutham 111-111-1111 222-222-2222 11/11/1111 12:30 12/21/2012 11:30
    /**int printFlag = 0;
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

    } */



  }

}