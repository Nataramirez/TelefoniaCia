package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Servicio;
import co.edu.Telefonia.modelos.Sesion;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CrearServicioControlador {
    private final ControladorPrincipal controladorPrincipal;
    @FXML
    private TextField idServicio;

    public CrearServicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @FXML
    private void buscarServicio(){
        try {
            Servicio servicio = controladorPrincipal.buscarServicio(idServicio.getText());
            if (servicio == null){
                controladorPrincipal.mostrarAlerta("No existe un servicio con el ID proporcionado", Alert.AlertType.INFORMATION);
            }else{
                Sesion sesion = controladorPrincipal.getSesion();
                sesion.setServicio(servicio);
                controladorPrincipal.navegarVentana(TipoPantalla.CONSULTAR_SERVICIO.getRuta(), TipoPantalla.CONSULTAR_SERVICIO.getNombre());
                idServicio.clear();
            }
        } catch (Exception e){
            controladorPrincipal.mostrarAlerta("lo sentimos. A ocurrido un error, no fue posible consultar el servicio", Alert.AlertType.ERROR);
        }
    }
}
