package co.edu.Telefonia.modelos;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
public class ServicioTelefonia extends Servicio{

    public ServicioTelefonia(String id, String nombre, String descripcion, float precio) {
        super(id, nombre, descripcion, precio);
    }
}
