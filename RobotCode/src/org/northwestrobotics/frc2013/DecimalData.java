/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.northwestrobotics.frc2013;

import com.sun.squawk.util.Arrays;

/**
 *
 * @author AgentOrange
 */
public class DecimalData implements DisplayData {

    private String formattedString;
    private String floatHeader;
    private double floatData;
    private int precision;
    private boolean updateString;

    public DecimalData() {
        floatData = 0;
        precision = 2;
        floatHeader = "";
    }
    
    public DecimalData(String header) {
        updateString = true;
        floatHeader = header;
        precision = 2;
        floatData = 0;
    }

    
    public void setData(double data) {
        updateString = true;
        floatData = data;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setHeader(String header) {
        updateString = true;
        floatHeader = header;
    }

    public String getFormattedString(int zoneLength) {
        if (updateString) {
            updateString = false;
            String tmpString = formatDecimal(floatData);

            if (floatHeader.length() + tmpString.length() > zoneLength) {
                if (tmpString.length() > zoneLength) {
                    tmpString = tmpString.substring(0, (tmpString.length() - (tmpString.length() - zoneLength) - 1));
                }
                formattedString = floatHeader.substring(0, zoneLength - tmpString.length()) + tmpString;
            } else {
                byte[] tmp = new byte[zoneLength - (floatHeader.length() + tmpString.length())];
                Arrays.fill(tmp, (byte) ' ');
                formattedString = floatHeader + new String(tmp).concat(tmpString);
            }
        }
        return formattedString;
    }

    private String formatDecimal(double convert) {
        int exp = 10;
        for (int i = 1; i < precision; i++) {
            exp *= 10;
        }
        int wholePart = (int) convert;
        double decimalPart = convert - wholePart;
        int convertedDecimal = (int) ((decimalPart * exp) + .5);
        String tmpDecimal = Integer.toString(convertedDecimal);
        if (tmpDecimal.length() < precision) {
            byte[] extraZeros = new byte[precision - tmpDecimal.length()];
            Arrays.fill(extraZeros, (byte) '0');
            tmpDecimal = new String(extraZeros).concat(tmpDecimal);
        }
        return new String(Integer.toString(wholePart) + '.' + tmpDecimal);
    }
}
