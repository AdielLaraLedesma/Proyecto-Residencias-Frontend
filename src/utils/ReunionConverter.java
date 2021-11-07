package utils;

import dto.Reunion;
import javafx.util.StringConverter;

public class ReunionConverter extends StringConverter<Reunion> {


    @Override
    public String toString(Reunion object) {
        return object == null ? "" : object.getTitulo() + ' ' + object.getNumeroParticipantes() + ' ' + object.getIdDepuracion();
    }

    @Override
    public Reunion fromString(String string) {
        return null;
    }
}
