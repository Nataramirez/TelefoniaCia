package co.edu.Telefonia.servicios;

import co.edu.Telefonia.modelos.*;
import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;
import co.edu.Telefonia.modelos.Cliente;
import co.edu.Telefonia.modelos.Plan;


public interface ServiciosEmpresa {
    Cliente crearCliente(String nombre, String cedula, String telefono, String correo) throws Exception;
    Cliente buscarCliente(String cedula) throws Exception;
    ServicioTelefonia crearServicio(TipoServicioTelefonia servicioTelefonia) throws Exception;
    ServicioTv crearServicio(TipoServicioTv servicioTv) throws Exception;
    ServicioInternet crearServicio(TipoServicioInternet servicioInternet) throws Exception;
    Cliente actuallizarCliente(String nombre, String telefono, String correo, String cedulaActual, String cedulaNueva) throws Exception;
    Boolean validarString(String cadena) throws  Exception;
    //Plan crearPlan(Cliente cliente, String direccion, String correo) throws Exception;
    String escogerPrimeroNoNulo(String cadena1, String cadena2) throws  Exception;
    Plan validarDireccion(String direccion) throws Exception;
    float calcularCostoTotalMensual(Plan plan) throws Exception;
}
