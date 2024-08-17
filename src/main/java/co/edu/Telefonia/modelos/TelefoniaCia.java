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
    public Plan validarDireccion(String direccion) throws Exception {
        try {
            for (Cliente cliente : clientes) {
                for (Plan plan : cliente.getPlanes()) {
                    if (plan.getDireccion().equals(direccion)) {
                        throw new Exception("La dirección ya está en uso");
                    }
                }
            }
            return null;
        }catch (Exception e){
            throw new Exception("No se puede encontrar la dirección");
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

    @Override
    public Cliente actuallizarCliente(String nombre, String telefono, String correo, String cedulaActual, String cedulaNueva) throws Exception {
        try {
            Cliente cliente = buscarCliente(cedulaActual);

            if (cliente != null){
                cliente.setNombre(escogerPrimeroNoNulo(nombre, cliente.getNombre()));
                cliente.setTelefono(escogerPrimeroNoNulo(telefono, cliente.getTelefono()));
                cliente.setCedula(escogerPrimeroNoNulo(cedulaNueva, cedulaActual));
                cliente.setCorreo(escogerPrimeroNoNulo(correo, cliente.getCorreo()));

                return cliente;
            }

            return null;

        } catch (Exception e) {
            throw new Exception("No se pudo actualizar el cliente");
        }
    }

    @Override
    public Boolean validarString(String cadena) throws Exception {
        if(cadena.isEmpty() || cadena.isBlank()) {
            return false;
        }
        return  true;
    }

    @Override
    public String escogerPrimeroNoNulo(String cadena1, String cadena2) throws Exception {
        if (validarString(cadena1)) {
            return cadena1;
        }
        return cadena2;
    }
    public float calcularCostoTotalMensual(Plan plan) throws Exception {
        float total = 0;
        try{
            for (Servicio servicio : plan.getServicios()){
                total += servicio.getPrecio();
            }
            if (plan.getServicios().size() == 2){
                total *= 0.9F;
            }
            else if (plan.getServicios().size() == 3){
                total *= 0.8F;
            }
            return total;
        }catch (Exception e){
            throw new Exception("No se puede calcular el costo total mensual");
        }
    }
}
