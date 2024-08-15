package co.edu.Telefonia.modelos;

import lombok.*;
import co.edu.Telefonia.servicios.ServiciosEmpresa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TelefoniaCia implements ServiciosEmpresa {
    private List<Cliente> clientes;

    /**
     * Método para crear un nuevo cliente
     * @param nombre
     * @param cedula
     * @param telefono
     * @param correo
     * @return
     * @throws Exception
     */
    @Override
    public Cliente crearCliente(String nombre, String cedula, String telefono, String correo) throws Exception {
        if(nombre.isEmpty() || nombre.isBlank()) {
            throw new Exception("El nombre es requerido");
        }

        if(cedula.isEmpty() || cedula.isBlank()) {
            throw new Exception("La cédula es requerida");
        }else {
            if(buscarCliente(cedula) != null){
                throw new Exception("El cliente ya existe");
            }
        }

        if(telefono.isEmpty() || telefono.isBlank()) {
            throw new Exception("El número telefónico es requerido");
        }

        if(correo.isEmpty() || correo.isBlank()) {
            throw new Exception("El correo es requerido");
        }

        Cliente nuevoCliente;
        try{
            nuevoCliente = Cliente.builder()
                    .id(UUID.randomUUID().toString())
                    .nombre(nombre)
                    .cedula(cedula)
                    .telefono(telefono)
                    .correo(correo)
                    .planes(new ArrayList<Plan>())
                    .build();
            clientes.add(nuevoCliente);
        } catch (Exception e) {
            throw new Exception("No se pudo crear el cliente");
        }
        return nuevoCliente;
    }

    @Override
    public Cliente buscarCliente(String cedula) throws Exception {
        try {
            for (Cliente cliente : clientes) {
                if (cliente.getCedula().equals(cedula)) {
                    return cliente;
                }
            }
            return null;
        }catch (Exception e){
            throw new Exception("No se puede buscar cliente");
        }
    }
}
