package utils;

import dto.Participante;
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
import java.util.ArrayList;
import java.util.List;

public class HttpRequestUtils {

    private static HttpURLConnection connection;

    private String API = "http://localhost:8080/";

    public Reunion postTakeAttendance(Reunion reunion, boolean revisarAsistencia){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = revisarAsistencia ? new URL(API + "revisarAsistencia?revisarAsistencia=true") : new URL(API + "revisarAsistencia?revisarAsistencia=false");
            connection = createConnection(url, "POST");
            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");


            Reunion prueba = reunion.format();

            outStreamWriter.write(prueba.toString());
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

                reunion = revisarAsistencia ? parseSuccessReunion("[" + responseContent.toString() + "]") : null;
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return reunion;
    }

    public void postConfirmAttendance(Reunion reunion){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL(API + "confirmarAsistencia");
            connection = createConnection(url, "POST");
            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");


            Reunion prueba = reunion.format();

            outStreamWriter.write(prueba.toString());
            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();


            int status = connection.getResponseCode();

            if (status > 299)
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            else
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
    }

    public List<Reunion> getAttendances() {

        List<Reunion> reuniones = new ArrayList();

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL(API + "obtener-reuniones");
            connection = createConnection(url, "GET");

            OutputStream outStream = connection.getOutputStream();
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");

            outStreamWriter.flush();
            outStreamWriter.close();
            outStream.close();


            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                reuniones = parseSucessReuniones(responseContent.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return reuniones;
    }

    public HttpURLConnection createConnection(URL url, String type) {
        try{
            connection = (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setDoOutput(true);
            connection.setRequestMethod(type);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);


            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Reunion> parseSucessReuniones(String responseBody){

        List<Reunion> reuniones = new ArrayList();

        JSONArray JSONresponse = new JSONArray(responseBody);
        for (int i = 0; i < JSONresponse.length(); i++) {
            JSONObject JSONReunion = JSONresponse.getJSONObject(i);
            reuniones.add(parseSuccessReunion("[" + JSONReunion.toString() + "]"));
        }
        return reuniones;
    }



    public Reunion parseSuccessReunion(String responseBody){

        Reunion reunion = new Reunion();

        JSONArray JSONresponse = new JSONArray(responseBody);
        JSONObject JSONreunion = JSONresponse.getJSONObject(0);
        int id = JSONreunion.getInt("id");
        int numeroParticipantes = JSONreunion.getInt("numeroParticipantes");
        String titulo = JSONreunion.getString("titulo");
        String horaInicio = JSONreunion.getString("horaInicio");
        String horaFin = JSONreunion.getString("horaFin");
        String idDepuracion = JSONreunion.getString("idDepuracion");

        List<Participante> participantes = new ArrayList();

        JSONArray JSONparticipantes = JSONreunion.getJSONArray("participantes");
        for (int i = 0; i < JSONparticipantes.length(); i++){
            JSONObject JSONparticipante = JSONparticipantes.getJSONObject(i);
            String nombreCompleto = JSONparticipante.getString("nombreCompleto");
            String horaUnion = JSONparticipante.getString("horaUnion");
            String horaSalida = JSONparticipante.getString("horaSalida");
            String duracion = JSONparticipante.getString("duracion");
            String email = JSONparticipante.getString("email");
            String rol = JSONparticipante.getString("rol");
            boolean asistencia = JSONparticipante.getBoolean("asistencia");

            participantes.add(new Participante(
                    nombreCompleto,
                    horaUnion,
                    horaSalida,
                    duracion,
                    email,
                    rol,
                    asistencia
                    //true
            ));
            reunion.setId(id);
            reunion.setNumeroParticipantes(String.valueOf(numeroParticipantes));
            reunion.setTitulo(titulo);
            reunion.setHoraInicio(horaInicio);
            reunion.setHoraFin(horaFin);
            reunion.setIdDepuracion(idDepuracion);

            reunion.setParticipantes(participantes);
        }
        return reunion;
    }

}
