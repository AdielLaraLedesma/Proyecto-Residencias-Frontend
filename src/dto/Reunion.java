package dto;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Reunion {

    //idReunion sera igual a 1 mientras se implemente el BACKEND
    private int id = 1;

    private String numeroParticipantes;

    private String titulo;
    private String horaInicio;
    private String horaFin;

    private String idDepuracion;


    private List<Participante> participantes;

    public Reunion(){

    }

    public Reunion(int idReunion, String numeroParticipantes, String titulo, String horaInicio, String horaFin, String idDepuracion, List<Participante> participantes) {
        this.id = idReunion;
        this.numeroParticipantes = numeroParticipantes;
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idDepuracion = idDepuracion;
        this.participantes = participantes;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(String numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getIdDepuracion() {
        return idDepuracion;
    }

    public void setIdDepuracion(String idDepuracion) {
        this.idDepuracion = idDepuracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{\"id\":" + id + "," +
                "\"numeroParticipantes\": \"" + numeroParticipantes + "\"," +
                "\"titulo\": \"" + titulo + "\"," +
                "\"horaInicio\": \"" + horaInicio + "\"," +
                "\"horaFin\": \"" + horaFin + "\"," +
                "\"idDepuracion\": \"" + idDepuracion + "\"," +
                "\"participantes\": " + participantes + "" +
                '}';
    }



    public Reunion format(){
        Reunion reunion = new Reunion();
        reunion.setId(this.id);
        reunion.setNumeroParticipantes(StringUtils.replaceSpecialtyStr(this.numeroParticipantes));
        reunion.setTitulo(StringUtils.replaceSpecialtyStr(this.titulo));
        reunion.setHoraInicio(StringUtils.replaceSpecialtyStr(this.horaInicio));
        reunion.setHoraFin(StringUtils.replaceSpecialtyStr(this.horaFin));
        reunion.setIdDepuracion(StringUtils.replaceSpecialtyStr(this.idDepuracion));


        List<Participante> participantes = new ArrayList();
        this.participantes.forEach( element -> {
            participantes.add(new Participante(
                            StringUtils.replaceSpecialtyStr(element.getNombreCompleto()),
                            StringUtils.replaceSpecialtyStr(element.getHoraUnion()),
                            StringUtils.replaceSpecialtyStr(element.getHoraSalida()),
                            StringUtils.replaceSpecialtyStr(element.getDuracion()),
                            StringUtils.replaceSpecialtyStr(element.getEmail()),
                            StringUtils.replaceSpecialtyStr(element.getRol()),
                            element.isAsistencia()));
        });
        reunion.setParticipantes(participantes);


        return reunion;
    }


}
