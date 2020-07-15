package edu.pdx.cs410J.podili;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TextParser implements PhoneBillParser<PhoneBill> {

    String fileName;
    String customerName;
    FileReader fr;

    /**
     * class constructor with filename and customer name as arguments
     *
     * @param fname contains name of the file
     * @param custName contains name of the customer
     */

    TextParser(String fname, String custName) {
        this.fileName = fname;
        this.customerName = custName;
    }

    /**
     * class constructor with reader and customer name as arguments
     *
     * @param reader
     * @param custName
     */

    TextParser(FileReader reader, String custName) {
        this.fr = reader;
        this.customerName = custName;
    }

    /**
     * parses the file and check whether file is malformatted or not
     *
     * @return phonebill if bill exists in the file
     * @throws ParserException if file is empty
     */

    @Override
    public PhoneBill parse() throws ParserException {
        PhoneBill bill = new PhoneBill(this.customerName);
        try {
            /**File f = new File(this.fileName);
            if(!f.exists()) {
                return null;
            }
            FileReader fr=new FileReader(f);*/
            BufferedReader br=new BufferedReader(fr);
            String line;
            line = br.readLine();
            if(line == null) {
                // throw an error here
                throw new ParserException("Found file with no records", new InvalidArgumentException("Found file with no records"));
            }
            while (line!=null) {
                //String sample = "Goutham@111-222-3333@222-333-4444@11/12/1996 12:30@11/12/1996 12:30";
                if(line.length() == 0) {
                    throw new ParserException("Found file with no records", new InvalidArgumentException("Found file with no records"));
                }
                String[] ags = line.split("@");
                if(ags.length != 5) {
                    throw new ParserException("The text file is malformatted", new InvalidArgumentException("The text file is malformatted"));
                }
                if(ags[0].isEmpty() || !ags[0].equalsIgnoreCase(this.customerName)) {
                    throw new ParserException("The name in the text file and customer name are not matching", new InvalidArgumentException("The name in the text file and customer name are not matching"));
                }
                validateNumber(ags[1]);
                validateNumber(ags[2]);
                String[] startTimeString = ags[3].split(" ");
                validateDate(startTimeString);
                String[] endTimeString = ags[4].split(" ");
                validateDate(endTimeString);
                line = br.readLine();
                PhoneCall call = new PhoneCall(ags[1], ags[2], ags[3], ags[4]);
                bill.addPhoneCall(call);
            }
        } catch(IOException e) {
            throw new ParserException("Error while parsing the file", e);
        }
        return bill;
    }

    /**
     * sets the name of the file
     *
     * @param name name of the file
     */

    public void setFile(String name) {
        this.fileName = name;
    }

    /**
     * checks whether the number is valid or not
     *
     * @param number contains mobile number
     * @return return true if number is valid
     */

    public boolean validateNumber(String number) {
        String callString = number;
        String[] callStringParts = number.split("-");
        if(callStringParts.length != 3 || callStringParts[0].length() !=3 ||
                callStringParts[1].length() !=3 || callStringParts[2].length() !=4) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        callString = callString.replace("-","");
        String regex = "\\d+";
        if(callString.length() != 10 || !callString.matches(regex)) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        return true;
    }

    /**
     * checks whether date is valid or not
     *
     * @param dateTimeString
     * @return true if date is valid
     */

    public boolean validateDate(String[] dateTimeString) {
        String dateString = dateTimeString[0];
        String[] dateStringParts = dateString.split("/");
        if(dateStringParts.length != 3) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        String timeString = dateTimeString[1];
        String[] timeStringParts = timeString.split(":");
        if(timeStringParts.length != 2) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        if(dateStringParts[0].length() >2 || dateStringParts[1].length() >2 || dateStringParts[2].length() != 4 ||
                timeStringParts[0].length() >2 || timeStringParts[1].length() >2) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        String regex = "\\d+";
        dateString = dateString.replace("/", "");
        timeString = timeString.replace(":", "");
        if (!(dateString.length() > 5 && dateString.length() < 9 && dateString.matches(regex)) ||
                !(timeString.length() > 1 && timeString.length() < 5 && timeString.matches(regex))) {
            throw new InvalidArgumentException("The text file is malformatted");
        }
        return true;
    }
}
