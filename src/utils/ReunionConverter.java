package utils;

import dto.Reunion;
import javafx.util.StringConverter;

public class ReunionConverter extends StringConverter<Reunion> {


    @Override
    public String toString(Reunion object) {
        return object == null ? "" : object.getTitulo() + "\n " + object.getNumeroParticipantes() + " \n " + object.getIdDepuracion();
    }

    @Override
    public Reunion fromString(String string) {
        return null;
    }
}
