package co.edu.Telefonia.controlador;

import javafx.event.ActionEvent;

public class InicioControlador {
    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        System.out.println("CLIENTES: " + controladorPrincipal.getTelefoniaCia().getClientes());
    }

    public void registrarUsuario(ActionEvent actionEvent) {
    }
}
