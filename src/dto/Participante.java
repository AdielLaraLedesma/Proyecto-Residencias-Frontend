package dto;


public class Participante {

    private String nombreCompleto;
    private String horaUnion;
    private String horaSalida;
    private String duracion;
    private String email;
    private String rol;

    //Modificar ya que implemente el consumo de POST
    private boolean asistencia = false;

    public Participante(String nombreCompleto, String horaUnion, String horaSalida, String duracion, String email, String rol, boolean asistencia) {
        this.nombreCompleto = nombreCompleto;
        this.horaUnion = horaUnion;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.email = email;
        this.rol = rol;
        this.asistencia = asistencia;
    }

    public Participante(String nombreCompleto, String horaUnion, String horaSalida, String duracion, String email, String rol) {
        this.nombreCompleto = nombreCompleto;
        this.horaUnion = horaUnion;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
        this.email = email;
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getHoraUnion() {
        return horaUnion;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", horaUnion='" + horaUnion + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", duracion='" + duracion + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
