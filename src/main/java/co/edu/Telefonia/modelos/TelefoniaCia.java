package co.edu.Telefonia.modelos;

import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;
import lombok.*;
import co.edu.Telefonia.servicios.ServiciosEmpresa;

import java.time.LocalDate;
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

    @Override
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
    public Plan crearPlan(String cedulaCliente, String direccion, TipoServicioTelefonia servicioTelefono, TipoServicioTv servicioTv, TipoServicioInternet servicioInternet) throws Exception {
        Plan nuevoPlan;
        ArrayList<Servicio> servicios = new ArrayList<>();
        try {
            Cliente cliente = buscarCliente(cedulaCliente);
            if(cliente == null){
                throw new Exception("El cliente no existe");
            }

            if(validarDireccion(direccion) != null){
                throw new Exception("El plan ya existe con la dirección ingresada");
            }

            if(servicioTelefono != null){
                ServicioTelefonia telefono = crearServicio(servicioTelefono);
                servicios.add(telefono);
            }
            if(servicioTv != null){
                ServicioTv television = crearServicio(servicioTv);
                servicios.add(television);
            }
            if(servicioInternet != null){
                ServicioInternet internet = crearServicio(servicioInternet);
                servicios.add(internet);
            }

             nuevoPlan = Plan.builder()
                     .id(UUID.randomUUID().toString())
                     .fechaCreacion(LocalDate.now())
                     .servicios(servicios)
                     .direccion(direccion)
                     .build();
            nuevoPlan.setCostoTotal(calcularCostoTotalMensual(nuevoPlan));
            cliente.agregarNuevoPlan(nuevoPlan);
            return nuevoPlan;
        }catch (Exception e){
            throw new Exception("No se puede crear el plan");
        }
    }
}
