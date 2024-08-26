package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Cliente;
import co.edu.Telefonia.modelos.Plan;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaClienteControlador implements Initializable {

    private ControladorPrincipal controladorPrincipal;

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField cedulaCliente;

    @FXML
    private TextField telefonoCliente;

    @FXML
    private TextField correoCliente;

    @FXML
    private TableView tablaPlanes;

    @FXML
    private TableColumn<Plan, String> id;
    @FXML
    private TableColumn<Plan, String> direccion;

    @FXML
    private TableColumn<Plan, Float> costo;
    private Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        cliente = controladorPrincipal.getSesion().getCliente();

        if (cliente != null) {
            llenarDatos();
            llenarTabla();
        } else {
            controladorPrincipal.mostrarAlerta("No se encontró información del cliente.", Alert.AlertType.WARNING);
        }

        Platform.runLater(() -> {
            Stage stage = (Stage) nombreCliente.getScene().getWindow();
            setStage(stage);
        });
    }

    private void llenarDatos(){
        nombreCliente.setText(cliente.getNombre());
        cedulaCliente.setText(cliente.getCedula());
        telefonoCliente.setText(cliente.getTelefono());
        correoCliente.setText(cliente.getCorreo());
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
    private void actualizarCliente(){
        String nombre, cedula, correo, telefono, cedulaActual;

        try {
            nombre = nombreCliente.getText();
            cedula = cedulaCliente.getText();
            correo = correoCliente.getText();
            telefono = telefonoCliente.getText();
            cedulaActual = cliente.getCedula();

            if (validarCamposNumericos()) {
                controladorPrincipal.actuallizarCliente(nombre, telefono, correo, cedulaActual, cedula);
                controladorPrincipal.mostrarAlerta("Cliente actualizado de manera exitosa", Alert.AlertType.INFORMATION);
                llenarDatos();
            } else {
                controladorPrincipal.mostrarAlerta("Asegurate de que los campos cedula y telefono sean numericos", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Ha ocurrido un error, no se pudo actualizar el cliente. \n Error: " + e.getMessage(), Alert.AlertType.WARNING);
        }
    }

    private boolean validarCamposNumericos(){
        try{
            if (!cedulaCliente.getText().isBlank()){
                Integer.parseInt(cedulaCliente.getText());
            }
            if (!telefonoCliente.getText().isBlank()){
                Integer.parseInt(telefonoCliente.getText());
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private void llenarTabla(){
        try {
            ArrayList<Plan> planes = controladorPrincipal.buscarCliente(cliente.getCedula()).getPlanes();

            if (planes == null || planes.isEmpty()) {
                Platform.runLater(() -> controladorPrincipal.mostrarAlerta("Oh no!, el cliente no tiene planes asociados", Alert.AlertType.INFORMATION));
            }else{
                id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
                direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
                costo.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getCostoTotal()).asObject());

                tablaPlanes.setItems( FXCollections.observableArrayList(planes));
            }
        } catch (Exception e){
            controladorPrincipal.mostrarAlerta("A ocurrido un error, no fue posible realizar la busqueda de los planes", Alert.AlertType.ERROR);
        }
    }
}
