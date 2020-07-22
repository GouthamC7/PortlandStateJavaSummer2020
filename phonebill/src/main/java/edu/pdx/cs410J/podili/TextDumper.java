package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeSet;

public class TextDumper implements PhoneBillDumper<PhoneBill> {

    String fileName;
    PhoneBill phoneBill;

    /**
     * class constructor with two arguments
     *
     * @param name contains name of the file
     * @param pb contains phonebill
     */

    TextDumper(String name, PhoneBill pb) {
        this.fileName = name;
        this.phoneBill = pb;
    }

    /**
     * class constructor with on argument
     *
     * @param name contains the name of the file
     */

    TextDumper(String name) {
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
        try {
            File f = new File(this.fileName);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
            //String absolutePath = "phonebill"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"edu"+File.separator+"pdx"+File.separator+"cs410J"+File.separator+"podili";
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(absolutePath+File.separator+this.fileName, true)));
            TreeSet<PhoneCall> call = (TreeSet<PhoneCall>)bill.getPhoneCalls();
            String billString = bill.getCustomer();
            for(PhoneCall call1 :call) {

                billString = billString + "@" + call1.getCaller() + "@" + call1.getCallee() + "@" + call1.getStart() + "@" + call1.getEnd()+"\n";
            }
            out.write(billString);
            out.close();
        } catch(Exception e) {
            System.err.println("Error while dumping to the file");
            System.exit(1);
        }
    }
}
