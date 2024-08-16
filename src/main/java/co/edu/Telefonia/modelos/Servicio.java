package co.edu.Telefonia.modelos;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Servicio {
    private String id;
    private String nombre;
    private String descripcion;
    private float precio;
}
