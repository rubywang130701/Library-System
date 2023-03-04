package ca.shopping.springbootlibrary.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
// TODO
//  This code is a Java method for extracting information from a JSON Web Token (JWT).
//  The token is passed as an argument, as well as the key of the information to be extracted (extraction).
//  The method first removes the "Bearer " prefix from the token, if present.
//  It then splits the token into three parts using the '.' character,
//  decodes the second part (the payload) using Base64 URL decoder,and converts it into a string.
//  The payload string is then split into individual entries separated by commas and stored in a map as key-value pairs.
//  Finally, the method returns the value of the key specified by the extraction argument, if it exists in the map. If the key is not found, the method returns null.
public class ExtractJWT {

    public static String payloadJWTExtraction(String token, String extraction) {

        token.replace("Bearer ", "");

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");
        Map<String, String> map = new HashMap<String, String>();

        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals(extraction)) {

                int remove = 1;
                if (keyValue[1].endsWith("}")) {
                    remove = 2;
                }
                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);

                map.put(keyValue[0], keyValue[1]);
            }
        }
        if (map.containsKey(extraction)) {
            return map.get(extraction);
        }
        return null;
    }
}
