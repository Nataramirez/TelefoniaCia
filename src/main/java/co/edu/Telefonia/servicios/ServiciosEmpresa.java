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
    Plan validarDireccion(String direccion) throws Exception;
    float calcularCostoTotalMensual(Plan plan) throws Exception;
    Plan crearPlan(String cedulaCliente, String direccion, TipoServicioTelefonia servicioTelefono, TipoServicioTv servicioTv, TipoServicioInternet servicioInternet) throws Exception;
}
