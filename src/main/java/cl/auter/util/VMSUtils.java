package cl.auter.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class VMSUtils {
    private static final String DATE_FORMAT      = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-ddTHH:mm:ss";
    
    

    
    
    public static String SQLString(String text) {
        text = "'" + text.replaceAll("'", "''") + "'";
        return text;
    }

    
    public static String SQLDate(LocalDateTime date) {
        return "'" + date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "'";
    }


    public static String SQLStartingDate(LocalDateTime date) {
        return "'" + date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "T00:00:00.000'";
    }

    
    public static String SQLEndingDate(LocalDateTime date) {
        return "'" + date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "T23:59:59.999'";
    }

    
    public static String SQLFechaHora(LocalDateTime fechaHora) {
        return "'" + fechaHora.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + "'";
    }


    public static String CharsAsStringList(String text) {
        List<Character> charArray = new ArrayList<Character>();
        
        for (int i = 0; i < text.length(); i ++) {
            if (! charArray.contains(text.charAt(i))) {
                charArray.add(text.charAt(i));
            }
        }
        Collections.sort(charArray);
        
        String characters = "";
        for (int i = 0; i < charArray.size(); i ++) {
            Character c = charArray.get(i);
            if ((c != '\n') && (c != '\r')) {
                characters += SQLString(c.toString()) + ", ";
            }
        }
        if (characters.length() > 2) {
            characters = characters.substring(0, characters.length() - 2);
        }
        
        return characters;
    }

    public static String LeftPad(String text, int length, Character character) {
        while (text.length() < length) {
            text = character + text;
        }
        return text;
    }
    
    public static String RightPad(String text, int length, Character character) {
        while (text.length() < length) {
            text = text + character;
        }
        return text;
    }
    
    public static String ZeroPad(int number, int length) {
        String numberStr = Integer.toString(number);

	while (numberStr.length() < length) {
            numberStr = "0" + numberStr;
	}
	return numberStr;
    }

    
    public static int BytesToInt(Byte[] bytes, Integer index, Integer numBytes) {
        int conversion = 0;
        int factor     = 1;                        
        
        if (index + numBytes <= bytes.length) {
            for (int i = index + numBytes - 1; i >= index; i --, factor *= 10) {
                Byte item = bytes[i];
                if ((item >= 0x30) && (item <= 0x39)) {
                    conversion += ((int) (item - 0x30)) * factor;
                } else {
                    conversion = 0;
                    break;
                }
            }
        }
        
        return conversion;
    }

    public static int BytesToInt(Byte[] bytes, Integer index) {
        return VMSUtils.BytesToInt(bytes, index, 2);
    }
    
    
    public static String Base64Fix(String b64) {
        if ((b64 != null) && (! b64.isEmpty())) {
            return b64.replaceAll("\\R", "").replaceAll("\\\\", "");
        } else {
            return b64;
        }
    }

}
