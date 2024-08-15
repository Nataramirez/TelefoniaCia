package co.edu.Telefonia.servicios;

import co.edu.Telefonia.modelos.Cliente;


public interface ServiciosEmpresa {
    Cliente crearCliente(String nombre, String cedula, String telefono, String correo) throws Exception;
    Cliente buscarCliente(String cedula) throws Exception;
}
