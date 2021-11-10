package dto;

import java.util.List;

public class Reunion {

    //idReunion sera igual a 1 mientras se implemente el BACKEND
    private int idReunion = 1;

    private String numeroParticipantes;

    private String titulo;
    private String horaInicio;
    private String horaFin;

    private String idDepuracion;


    private List<Participante> participantes;

    public Reunion(){

    }

    public Reunion(int idReunion, String numeroParticipantes, String titulo, String horaInicio, String horaFin, String idDepuracion, List<Participante> participantes) {
        this.idReunion = idReunion;
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

    public int getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idReunion\": " + idReunion + "," +
                "\" numeroParticipantes\": " + numeroParticipantes + ',' +
                "\" titulo\": " + titulo + ',' +
                "\" horaInicio\": " + horaInicio + ',' +
                "\" horaFin\": " + horaFin + ',' +
                "\" idDepuracion\": " + idDepuracion + + ',' +
                "\" participantes\": " + participantes +
                '}';
    }
}
