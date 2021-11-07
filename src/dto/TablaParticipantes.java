package dto;

import javafx.beans.property.SimpleStringProperty;

public class TablaParticipantes {

    //Assume each record have 6 elements, all String

    private SimpleStringProperty nombreCompleto;
    private SimpleStringProperty horaUnion;
    private SimpleStringProperty horaSalida;
    private SimpleStringProperty duracion;
    private SimpleStringProperty email;
    private SimpleStringProperty rol;
    private SimpleStringProperty asistencia;


    public TablaParticipantes(String nombreCompleto,
                              String horaUnion,
                              String horaSalida,
                              String duracion,
                              String email,
                              String rol,
                              String asistencia) {
        this.nombreCompleto =   new SimpleStringProperty(nombreCompleto);
        this.horaUnion =        new SimpleStringProperty(horaUnion);
        this.horaSalida =       new SimpleStringProperty(horaSalida);
        this.duracion =         new SimpleStringProperty(duracion);
        this.email =            new SimpleStringProperty(email);
        this.rol =              new SimpleStringProperty(rol);
        this.asistencia =        new SimpleStringProperty(asistencia);
    }

    public TablaParticipantes() {
    }

    public String getNombreCompleto() {
        return nombreCompleto.get();
    }



    public String getHoraUnion() {
        return horaUnion.get();
    }


    public String getHoraSalida() {
        return horaSalida.get();
    }



    public String getDuracion() {
        return duracion.get();
    }



    public String getEmail() {
        return email.get();
    }


    public String getRol() {
        return rol.get();
    }

    public String getAsistencia() {
        return asistencia.get();
    }


    public void setAsistencia(String asistencia) {
        this.asistencia.set(asistencia);
    }

    @Override
    public String toString() {
        return "TablaParticipantes{" +
                "nombreCompleto=" + nombreCompleto +
                ", horaUnion=" + horaUnion +
                ", horaSalida=" + horaSalida +
                ", duracion=" + duracion +
                ", email=" + email +
                ", rol=" + rol +
                ", asistencia=" + asistencia +
                '}';
    }
}
