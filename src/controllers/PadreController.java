package controllers;

public abstract class PadreController {

    private Object objecto = null;

    public abstract void Inicializador();

    public void setObjeto(Object objecto) {
        this.objecto = objecto;
    }
}
