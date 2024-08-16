package co.edu.Telefonia.servicios;

import co.edu.Telefonia.modelos.*;
import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;


public interface ServiciosEmpresa {
    Cliente crearCliente(String nombre, String cedula, String telefono, String correo) throws Exception;
    Cliente buscarCliente(String cedula) throws Exception;
    ServicioTelefonia crearServicio(TipoServicioTelefonia servicioTelefonia) throws Exception;
    ServicioTv crearServicio(TipoServicioTv servicioTv) throws Exception;
    ServicioInternet crearServicio(TipoServicioInternet servicioInternet) throws Exception;
    //Plan crearPlan(Cliente cliente, String direccion, String correo) throws Exception;
}
