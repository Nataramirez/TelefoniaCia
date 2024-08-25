package co.edu.Telefonia.modelos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Sesion {

    private Cliente cliente;
    public Sesion() {
         cliente = null;
    }

    public void cerrarSesion(){
        cliente = null;
    }
}
