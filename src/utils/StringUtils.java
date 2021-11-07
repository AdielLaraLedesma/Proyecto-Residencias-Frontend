package utils;

import java.util.Arrays;

public class StringUtils {


    public String[] splitString(String cadena){
        String [] fields = cadena.split("\t");
        for (String field : fields){
            System.out.println("Este field "+ field);
        }
        return fields;
    }

    /*public static void main(String[] args) {
        StringUtils stringUtils = new StringUtils();
        stringUtils.splitString("Hola  Aqui estoy bien    Agusto, fierro");
    }*/

}
