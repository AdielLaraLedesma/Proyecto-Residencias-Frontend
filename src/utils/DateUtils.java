package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public Date castStringtoDate(String stringDate){
        try{
            SimpleDateFormat format = null;
            if(stringDate.length() == 18){
                format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            }else{
                format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            }

            format.setTimeZone(TimeZone.getTimeZone("UTC"));


            return format.parse(stringDate);
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
