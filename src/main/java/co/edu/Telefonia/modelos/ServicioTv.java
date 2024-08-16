package co.edu.Telefonia.modelos;

import lombok.*;

@Setter
@Getter
@ToString
public class ServicioTv extends Servicio {
    public ServicioTv(String id, String nombre, String descripcion, float precio) {
        super(id, nombre, descripcion, precio);
    }
}
