package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Servicio;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarServicioControlador implements Initializable {

    private ControladorPrincipal controladorPrincipal;
    private Servicio servicio;

    @FXML
    private TextField id;

    @FXML
    private TextField nombre;

    @FXML
    private TextField precio;

    @FXML
    private TextArea descripcion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        servicio = controladorPrincipal.getSesion().getServicio();

        if (servicio != null) {
            llenarCampos();
        } else {
            controladorPrincipal.mostrarAlerta("No se encontró información del cliente.", Alert.AlertType.WARNING);
        }
        id.setDisable(true);

        Platform.runLater(() -> {
            Stage stage = (Stage) nombre.getScene().getWindow();
            setStage(stage);
        });
    }

    private void llenarCampos(){
        id.setText(servicio.getId());
        nombre.setText(servicio.getNombre());
        descripcion.setText(servicio.getDescripcion());
        precio.setText(String.valueOf(servicio.getPrecio()));
    }

    public void setStage(Stage stage) {
        stage.setOnCloseRequest(event -> handleWindowClose(event));
    }

    private void handleWindowClose(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cierre");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas cerrar la ventana?");

        if (alert.showAndWait().get() != javafx.scene.control.ButtonType.OK) {
            event.consume();
        }else{
            controladorPrincipal.getSesion().cerrarSesion();
        }
    }

    @FXML
    private void actualizar() {
        String nombreServicio, descripcionServicio;
        Float precioServicio;

        try {
            if (validarCamposNumericos()) {
                nombreServicio = nombre.getText();
                descripcionServicio = descripcion.getText();
                precioServicio = Float.parseFloat(precio.getText());

                controladorPrincipal.actualizarServicio(servicio, nombreServicio, descripcionServicio, precioServicio);
                controladorPrincipal.mostrarAlerta("Servicio actualizado de manera exitosa", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) nombre.getScene().getWindow();
                stage.close();
            } else {
                controladorPrincipal.mostrarAlerta("Asegúrese de que el precio sea un dato numérico", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Lo sentimos. Ha ocurrido un problema y no fue posible actualizar el servicio", Alert.AlertType.ERROR);
        }
    }


    private boolean validarCamposNumericos(){
        try{
            if (!precio.getText().isBlank()){
                Float.parseFloat(precio.getText());
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
