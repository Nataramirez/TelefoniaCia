package co.edu.Telefonia.modelos;

import lombok.*;

@Setter
@Getter
@ToString
public class ServicioInternet extends Servicio{
    public ServicioInternet(String id, String nombre, String descripcion, float precio) {
        super(id, nombre, descripcion, precio);
    }
}
