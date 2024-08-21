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
public class Factura {
    private String id;
    private LocalDate fecha;
    private Plan plan;
    private float costoTotal;
}
