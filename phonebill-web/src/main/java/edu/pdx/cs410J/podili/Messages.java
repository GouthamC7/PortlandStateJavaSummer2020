package edu.pdx.cs410J.podili;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{

    /**
     * returns error message with parameter
     * @param parameterName
     * @return
     */
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /**
     * returns error message with parameter
     * @param customerName contains name of customer
     * @return
     */
    static String noPhoneBillForCustomer(String customerName) {
        return String.format("No phone bill for customer %s", customerName);
    }

}
