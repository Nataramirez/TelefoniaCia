package co.edu.Telefonia.modelos;

import java.util.ArrayList;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Cliente {
    private String id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private ArrayList<Plan> planes;
}
