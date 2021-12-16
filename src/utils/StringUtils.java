package utils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtils {


    public String[] splitString(String cadena){
        String [] fields = cadena.split("\t");
        for (String field : fields){
        }
        return fields;
    }

    /*public static void main(String[] args) {
        StringUtils stringUtils = new StringUtils();
        stringUtils.splitString("Hola  Aqui estoy bien    Agusto, fierro");
    }*/

    public static boolean isBlankOrNull(String str){
        if(null==str)return true;
        return str.length()==0;
    }
    /*public static String replaceSpecialtyStr(String str,String pattern,String replace){
        if(isBlankOrNull(pattern))
            pattern="\\s*|\t|\r|\n";
        if(isBlankOrNull(replace))
            replace="";
        return Pattern.compile(pattern).matcher(str).replaceAll(replace);
    }*/


    public static String replaceSpecialtyStr(String str){
        str = str.replaceAll("[^\\p{Graph}\n\r\t ]", "");
        return str.replaceAll("\"", "\'");
    }


}
