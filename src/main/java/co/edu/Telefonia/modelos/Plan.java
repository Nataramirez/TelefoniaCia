package co.edu.Telefonia.modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Plan {
    private String id;
    private String direccion;
    private LocalDate fechaCreacion;
    private ArrayList<Servicio> servicios;
    private float costoTotal;
}
