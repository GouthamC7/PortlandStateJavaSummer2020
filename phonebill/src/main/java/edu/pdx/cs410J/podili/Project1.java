package edu.pdx.cs410J.podili;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public  static  void readMe() {
    System.out.println("Printing read me");
    System.exit(0);
  }

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
      if(args[0].equalsIgnoreCase("-README")) {
        readMe();
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length <7) {
      if(args[0].equalsIgnoreCase("-README") || args[1].equalsIgnoreCase("-README")) {
        readMe();
      }
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else if (args.length >9) {
      if(args[0].equalsIgnoreCase("-README") || args[1].equalsIgnoreCase("-README")) {
        readMe();
      }
      System.err.println("Too many arguments");
      System.exit(1);
    } else {
      if(args[0].equalsIgnoreCase("-README") || args[1].equalsIgnoreCase("-README")) {
        readMe();
      }
      if(args[0].equalsIgnoreCase("-print") || args[1].equalsIgnoreCase("-print")) {
        options++;
        printFlag = 1;
      }
      if((args.length-options) != 7) {
        System.err.println("Too many arguments");
        System.exit(1);
      }
      if(printFlag == 1) {
        call = new PhoneCall(args[options++],args[options++],args[options++]+args[options++],args[options++]+args[options++]);
        System.out.println("Printing object");
        System.exit(0);
      }
      call = new PhoneCall(args[options++],args[options++],args[options++]+args[options++],args[options++]+args[options++]);
      System.out.println("Success");
      System.exit(0);
    }

  }

}