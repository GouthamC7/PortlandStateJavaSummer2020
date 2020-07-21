package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.TreeSet;

public class TextDumper implements PhoneBillDumper<PhoneBill> {

    /**
    public  static  void main(String[] args) throws IOException {
        PhoneCall pc = new PhoneCall("123-456-7890","123-456-7890","12/12/2019 12:30","11/11/2020 11:30");
        PhoneBill pb = new PhoneBill("Goutham", pc);
        TextDumper t = new TextDumper("phonebill/Goutham.txt", pb);
        t.dump(pb);
        System.exit(0);
        File f = new File("phonebill/abc.txt");
        File f = new File("phonebill/abc.txt");
        System.out.println(f.exists());
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
        out.write("Hello");
        out.close();
        InputStream readme = Project2.class.getResourceAsStream("phonebill/abc.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
        String line = reader.readLine();
        System.out.println(line);
        FileReader fr=new FileReader(f);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line;
        while((line=br.readLine())!=null)
        {
            sb.append(line);      //appends line to string buffer
            sb.append("\n");     //line feed
        }
        fr.close();    //closes the stream and release the resources
        System.out.println("Contents of File: ");
        System.out.println(sb.toString());
    }*/

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
