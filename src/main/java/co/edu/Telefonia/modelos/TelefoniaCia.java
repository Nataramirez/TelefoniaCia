package co.edu.Telefonia.modelos;

import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;
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

    @Override
    public ServicioTelefonia crearServicio(TipoServicioTelefonia servicioTelefonia) throws Exception {
        try {
            return new ServicioTelefonia(
                    UUID.randomUUID().toString(),
                    servicioTelefonia.getCategoria(),
                    servicioTelefonia.getDescripcion(),
                    servicioTelefonia.getPrecio()
            );
        } catch (Exception e){
            throw new Exception("No se puede crear el servicio telefonia");
        }
    }

    @Override
    public ServicioTv crearServicio(TipoServicioTv servicioTv) throws Exception {
        try {
            return new ServicioTv(
                    UUID.randomUUID().toString(),
                    servicioTv.getCategoria(),
                    servicioTv.getDescripcion(),
                    servicioTv.getPrecio()
            );
        } catch (Exception e){
            throw new Exception("No se puede crear el servicio televisión");
        }
    }

    @Override
    public ServicioInternet crearServicio(TipoServicioInternet servicioInternet) throws Exception {
        try {
            return new ServicioInternet(
                    UUID.randomUUID().toString(),
                    servicioInternet.getCategoria(),
                    servicioInternet.getDescripcion(),
                    servicioInternet.getPrecio()
            );
        } catch (Exception e){
            throw new Exception("No se puede crear el servicio internet");
        }
    }
}
