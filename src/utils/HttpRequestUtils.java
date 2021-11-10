package utils;

import dto.Reunion;
import dto.dtoPrueba;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestUtils {

    private static HttpURLConnection connection;

    public void postValidateAttendance(dtoPrueba dtoPrueba){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("http://localhost:8080/Prueba");
            connection = (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
            //outStreamWriter.write(JSONObject.valueToString(dtoPrueba));
            outStreamWriter.write(createJson(dtoPrueba).toString());
            System.out.println(createJson(dtoPrueba).toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();


            int status = connection.getResponseCode();

            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            //parseSuccess(responseContent.toString());
            System.out.println(responseContent.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
    }



    public void Prueba(){

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            parseSuccess(responseContent.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }


    }

    //public static void main(String[] args) {
    //    new HttpRequestUtils().postValidateAttendance(null);
    //}

    public String parseSuccess(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id + " " + title + " " + userId);
        }
        return null;
    }

    public JSONObject createJson(dtoPrueba dtoPrueba){
        JSONObject json = new JSONObject();

        String prueba = dtoPrueba.titulo.toString();

        prueba = prueba.replace("\u0000", "");
        prueba = prueba.replace("\\u0000", "");
        json.put("title", prueba);

        return json;
    }


}
