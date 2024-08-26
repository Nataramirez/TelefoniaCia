package co.edu.Telefonia.modelos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Sesion {

    private Cliente cliente;
    private Servicio servicio;

    public Sesion() {
         cliente = null;
         servicio = null;
    }

    public void cerrarSesion(){
        cliente = null;
        servicio = null;
    }
}
