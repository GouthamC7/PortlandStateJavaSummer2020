package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {
    String fileName;
    PhoneBill phoneBill;

    /**
     * class constructor with two arguments
     *
     * @param name contains name of the file
     * @param pb contains phonebill
     */

    PrettyPrinter(String name, PhoneBill pb) {
        this.fileName = name;
        this.phoneBill = pb;
    }

    /**
     * class constructor with on argument
     *
     * @param name contains the name of the file
     */

    PrettyPrinter(String name) {
        this.fileName = name;
    }

    /**
     * Dumps the phonebill to the file.
     *
     * @param bill contains phonebill
     * @throws IOException
     */

    @Override
    public void dump(PhoneBill bill) throws IOException {
        //System.out.println(this.fileName);
        Date date1 = null;
        Date date2 = null;
        if(this.fileName.equals("-")) {
            TreeSet<PhoneCall> call = (TreeSet<PhoneCall>)bill.getPhoneCalls();
            String customer = bill.getCustomer();
            String billString = "";
            //System.out.println("length of calls: " + call.size());
            for(PhoneCall call1 :call) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, hh:mm aa");
                try {
                    date1 = sdf.parse(call1.getEndTimeString());
                    date2 = sdf.parse(call1.getStartTimeString());
                } catch (Exception e) {
                    System.err.println("Error occured while parsing date");
                    System.exit(1);
                }
                long result = ((date1.getTime()/60000) - (date2.getTime()/60000));
                //System.out.println("printing pretyy ------------------------");
                System.out.println("Name: "+customer + ", caller: " + call1.getCaller() + ", callee: " + call1.getCallee() + ", Start time: " + call1.getStartTimeString() + ", End time: " + call1.getEndTimeString()+ ", Duration: "+ result +" minutes");
                //System.out.println("printed pretyy ------------------------");
                //billString = billString+customer + "@" + call1.getCaller() + "@" + call1.getCallee() + "@" + call1.getStartTimeString() + "@" + call1.getEndTimeString()+ "@"+ result +" minutes"+"\n";
            }
        } else {
            try {
                File f = new File(this.fileName);
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
                //String absolutePath = "phonebill"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"edu"+File.separator+"pdx"+File.separator+"cs410J"+File.separator+"podili";
                //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(absolutePath+File.separator+this.fileName, true)));
                TreeSet<PhoneCall> call = (TreeSet<PhoneCall>)bill.getPhoneCalls();
                String customer = bill.getCustomer();
                String billString = "";
                for(PhoneCall call1 :call) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy, hh:mm aa");
                    date1 = sdf.parse(call1.getEndTimeString());
                    date2 = sdf.parse(call1.getStartTimeString());
                    long result = ((date1.getTime()/60000) - (date2.getTime()/60000));
                    billString = billString+customer + "@" + call1.getCaller() + "@" + call1.getCallee() + "@" + call1.getStartTimeString() + "@" + call1.getEndTimeString()+ "@"+ result +" minutes"+"\n";
                }
                out.write(billString);
                out.close();
            } catch(Exception e) {
                System.err.println("Please check the given path ");
                System.exit(1);
            }
        }

    }
}
