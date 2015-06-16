package hr.bpervan.mt.utils;

/**
 * Created by Branimir on 15.6.2015..
 */
public class StringUtils {

    public static int countOccurrences(String in, char c){
        int count = 0;

        for (int i = 0; i < in.length(); i++) {
            if(in.charAt(i) == c){
                count++;
            }
        }

        return count;
    }
}
