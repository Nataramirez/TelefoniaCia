package co.edu.Telefonia.modelos;

import java.util.ArrayList;
import java.util.UUID;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Plan {
    private UUID id;
    private String direccion;
    private ArrayList<Servicio> servicios;
    private float costoTotal;
}
