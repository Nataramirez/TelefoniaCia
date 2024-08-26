package co.edu.Telefonia.modelos;

import co.edu.Telefonia.modelos.enums.TipoServicio;
import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;
import lombok.*;
import co.edu.Telefonia.servicios.ServiciosEmpresa;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TelefoniaCia implements ServiciosEmpresa {
    private List<Cliente> clientes;
    private ArrayList<Factura> facturas;
    private ArrayList<Servicio> serviciosInternet;
    private ArrayList<Servicio> serviciosTelefonia;
    private ArrayList<Servicio> serviciosTv;

    public TelefoniaCia() {
        clientes = new ArrayList<>();
        serviciosInternet = new ArrayList<>();
        serviciosTelefonia = new ArrayList<>();
        serviciosTv = new ArrayList<>();
        facturas = new ArrayList<>();
        serviciosIniciales();
        System.out.println(serviciosTv.get(0).getId());
        System.out.println(serviciosInternet.get(0));
    }

    private void serviciosIniciales(){
        for(TipoServicioInternet servicioInternet : TipoServicioInternet.values()){
            ServicioInternet servicio = new ServicioInternet(UUID.randomUUID().toString(), servicioInternet.getCategoria(), servicioInternet.getDescripcion(), servicioInternet.getPrecio());
            serviciosInternet.add(servicio);
        }
        for(TipoServicioTv servicioTV : TipoServicioTv.values()){
            ServicioTv servicio = new ServicioTv(UUID.randomUUID().toString(), servicioTV.getCategoria(), servicioTV.getDescripcion(), servicioTV.getPrecio());
            serviciosTv.add(servicio);
        }
        for(TipoServicioTelefonia servicioTelefonia : TipoServicioTelefonia.values()){
            ServicioTelefonia servicio = new ServicioTelefonia(UUID.randomUUID().toString(), servicioTelefonia.getCategoria(), servicioTelefonia.getDescripcion(), servicioTelefonia.getPrecio());
            serviciosTelefonia.add(servicio);
        }
    }

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
    public Servicio crearServicio(String nombre, String descripcion, float precio, TipoServicio tipoServicio) throws Exception {
        try {
            switch (tipoServicio) {
                case SERVICIO_TV -> {
                    return new ServicioTv( UUID.randomUUID().toString(), nombre, descripcion, precio);
                }
                case SERVICIO_TELEFONIA -> {
                    return new ServicioTelefonia( UUID.randomUUID().toString(), nombre, descripcion, precio);
                }
                case SERVICIO_INTERNET -> {
                    return new ServicioInternet( UUID.randomUUID().toString(), nombre, descripcion, precio);
                }
                default -> throw new Exception("No se puede crear el servicio");
            }
        }catch (Exception e){
            throw new Exception("No se puede crear el servicio");
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
        return !cadena.isEmpty() && !cadena.isBlank();
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

    @Override
    public int contarMesesPlan(Plan plan) throws Exception {
        LocalDate fechaInicio = plan.getUltimaFechaFacturacion() != null
                ? plan.getUltimaFechaFacturacion()
                : plan.getFechaCreacion();
        LocalDate fechaFin = LocalDate.now();

        LocalDate fechaInicioAjustada = fechaInicio.withDayOfMonth(Math.min(fechaInicio.getDayOfMonth(), fechaFin.lengthOfMonth()));

        int mesesCompletos = (int) ChronoUnit.MONTHS.between(fechaInicioAjustada, fechaFin);

        if (fechaFin.getDayOfMonth() >= fechaInicio.getDayOfMonth()) {
            mesesCompletos++;
        }

        return mesesCompletos;
    }



    @Override
    public Factura crearFactura(Cliente cliente, Plan plan) throws Exception {
        try {
            LocalDate fechaFactura = null;
            Factura factura = null;

            if (plan.getUltimaFechaFacturacion() == null) {
                if (plan.getFechaCreacion().plusMonths(1).isBefore(LocalDate.now())) {
                    fechaFactura = plan.getFechaCreacion().plusMonths(1);
                }
            }else{
                fechaFactura = plan.getUltimaFechaFacturacion().plusMonths(1);
            }

            if (fechaFactura != null){
                factura  = Factura.builder()
                        .plan(plan)
                        .costoTotal(calcularCostoTotalMensual(plan))
                        .fecha(fechaFactura)
                        .id(UUID.randomUUID().toString())
                        .build();
                facturas.add(factura);
                plan.setUltimaFechaFacturacion(fechaFactura);
            }
            return factura;
        }catch (Exception e){
            throw new Exception("No se pudo enviar la factura correctamente");
        }
    }


    @Override
    public void enviarFacturas() throws Exception {
        for (Cliente cliente : clientes) {
            for (Plan plan : cliente.getPlanes()) {

                int cantidadFacturasFaltantes = contarMesesPlan(plan);

                for (int i = 0; i<cantidadFacturasFaltantes; i++){
                    Factura factura = crearFactura(cliente, plan);
                    facturas.add(factura);
                }
            }
        }
    }

    @Override
    public ArrayList<Factura> generarReporteMensual() throws Exception {
        ArrayList<Factura> facturasDelMesAnterior = new ArrayList<>();

        try {
            YearMonth mesAnterior = YearMonth.now().minusMonths(1);;
            LocalDate inicioMesAnterior = mesAnterior.atDay(1);
            LocalDate finMesAnterior = mesAnterior.atEndOfMonth();

            if (facturas != null && !facturas.isEmpty()){
                for (Factura factura : facturas) {
                    LocalDate fechaFactura = factura.getFecha();

                    if ((fechaFactura.isEqual(inicioMesAnterior) || fechaFactura.isAfter(inicioMesAnterior)) &&
                            (fechaFactura.isEqual(finMesAnterior) || fechaFactura.isBefore(finMesAnterior))) {

                        facturasDelMesAnterior.add(factura);
                    }
                }
            }

            return facturasDelMesAnterior;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("No se pudo generar el reporte mensual de facturas", e);
        }
    }

    @Override
    public Servicio buscarServicio(String idServicio) throws Exception {
        try {
            for (Servicio servicio : serviciosInternet) {
                if (servicio.getId().equals(idServicio)) {
                    return servicio;
                }
            }

            for (Servicio servicio : serviciosTelefonia) {
                if (servicio.getId().equals(idServicio)) {
                    return servicio;
                }
            }

            for (Servicio servicio : serviciosTv) {
                if (servicio.getId().equals(idServicio)) {
                    return servicio;
                }
            }

            return null;
        } catch (Exception e) {
            throw new Exception("No se pudo buscar el servicio", e);
        }
    }

    @Override
    public Servicio actualizarServicio(Servicio servicio, String nombre, String descripcion, Float precio) throws Exception {
        try {
            if (servicio != null) {
                servicio.setNombre(escogerPrimeroNoNulo(nombre, servicio.getNombre()));
                servicio.setDescripcion(escogerPrimeroNoNulo(descripcion, servicio.getDescripcion()));
                servicio.setPrecio(precio != null ? precio : servicio.getPrecio());
            } else {
                throw new Exception("Se debe enviar un servicio");
            }

            return servicio;
        } catch (Exception e) {
            throw new Exception("No se pudo actualizar el servicio", e);
        }
    }



    @Override
    public ArrayList<Plan> listarPlanesPorCliente(String cedula) throws Exception {
        try {
            Cliente cliente = buscarCliente(cedula);
            return cliente.getPlanes();
        } catch (Exception e) {
            throw new Exception("El cliente no cuenta con planes asociados");

        }
    }
}
