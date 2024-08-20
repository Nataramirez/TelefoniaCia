package co.edu.Telefonia.modelos;

import java.util.ArrayList;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Cliente {
    private String id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private ArrayList<Plan> planes;

    public void agregarNuevoPlan(Plan nuevoPlan){
        planes.add(nuevoPlan);

    @Override
    public String toString() {
        return
                "id: '" + id + '\n' +
                "nombre: '" + nombre + '\n' +
                "cedula: '" + cedula + '\n' +
                "telefono: '" + telefono + '\n' +
                "correo: '" + correo + '\n' +
                "planes:" + planes;
    }
}
