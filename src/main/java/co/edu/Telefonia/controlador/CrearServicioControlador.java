package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Servicio;
import co.edu.Telefonia.modelos.Sesion;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import co.edu.Telefonia.modelos.Servicio;
import co.edu.Telefonia.modelos.enums.TipoServicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class CrearServicioControlador implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    @FXML
    private TextField idServicio;
    @FXML
    public ComboBox tipoServicio;
    @FXML
    public TextField nombreServicio;
    @FXML
    public TextField precioServicio;
    @FXML
    public TextField descripcionServicio;

    public CrearServicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoServicio.setItems(FXCollections.observableArrayList(TipoServicio.values()));
    }

    public void crearServicio() {
        if(tipoServicio.getValue() == null){
            controladorPrincipal.mostrarAlerta("El tipo de servicio es requerido.", Alert.AlertType.WARNING);
        } else if (nombreServicio.getText().isEmpty() || nombreServicio.getText().isBlank()) {
            controladorPrincipal.mostrarAlerta("El nombre del servicio es requerido.", Alert.AlertType.WARNING);
        } else if (descripcionServicio.getText().isBlank() || descripcionServicio.getText().isEmpty()) {
            controladorPrincipal.mostrarAlerta("La descripcion del servicio es requerido.", Alert.AlertType.WARNING);
        } else if (precioServicio.getText().isEmpty() || precioServicio.getText().isBlank()) {
            controladorPrincipal.mostrarAlerta("El precio del servicio es requerido.", Alert.AlertType.WARNING);
        }else {
            try{
                TipoServicio tipoEncontrado = null;
                for (TipoServicio tipo : TipoServicio.values()) {
                    if (tipo == tipoServicio.getValue()){
                        tipoEncontrado = tipo;
                    }
                }
                Servicio nuevoServicio = controladorPrincipal.crearServicio(nombreServicio.getText(), descripcionServicio.getText(), Float.parseFloat(precioServicio.getText()), tipoEncontrado);
                if (nuevoServicio != null) {
                    controladorPrincipal.mostrarAlerta("Servicio creado correctamente.", Alert.AlertType.INFORMATION);
                }
            } catch (Exception e){
                controladorPrincipal.mostrarAlerta("No se puede crear el servicio." + e, Alert.AlertType.ERROR);
            }
        }
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
