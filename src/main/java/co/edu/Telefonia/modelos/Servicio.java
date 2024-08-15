package co.edu.Telefonia.modelos;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
public class Servicio {
    private UUID id;
    private Enum<?> nombre;
    private String descripcion;
    private float precio;
}
