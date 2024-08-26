package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Cliente;
import co.edu.Telefonia.modelos.Servicio;
import co.edu.Telefonia.modelos.ServicioTv;
import co.edu.Telefonia.modelos.Sesion;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InicioControlador implements Initializable {
    private final ControladorPrincipal controladorPrincipal;
    @FXML
    public TextField nombreCliente;
    @FXML
    public TextField cedulaCliente;
    @FXML
    public TextField telefonoCliente;
    @FXML
    public TextField correoCliente;
    @FXML
    public TextField buscarCedula;
    @FXML
    public TextField cedulaNuevoPlan;
    @FXML
    public TextField direccionNuevoPlan;
    @FXML
    public TextField nombreClientePlan;
    @FXML
    public TextField telefonoClientePlan;
    @FXML
    public TextField correoClientePlan;
    @FXML
    public ComboBox btnServicioTV;
    @FXML
    public ComboBox btnServicioInternet;
    @FXML
    public ComboBox btnServicioTelefonia;

    Cliente clienteNuevoPlan;

    public InicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        System.out.println("CLIENTES: " + controladorPrincipal.getTelefoniaCia().getClientes());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnServicioTV.setItems(FXCollections.observableArrayList(nombresServicios(controladorPrincipal.getTelefoniaCia().getServiciosTv())));
        btnServicioInternet.setItems(FXCollections.observableArrayList(nombresServicios(controladorPrincipal.getTelefoniaCia().getServiciosInternet())));
        btnServicioTelefonia.setItems(FXCollections.observableArrayList(nombresServicios(controladorPrincipal.getTelefoniaCia().getServiciosTelefonia())));
        System.out.println("TELEFONIA CIA: " + controladorPrincipal.getTelefoniaCia());
    }

    public void registrarCliente() {
        if(validarCampos()){
            controladorPrincipal.mostrarAlerta("Todos los campos son obligatorios. Los campos de cédula y teléfono deben ser numéricos",
                    Alert.AlertType.WARNING);
        } else {
            try {
                Cliente cliente = controladorPrincipal.buscarCliente(cedulaCliente.getText());
                if(cliente == null){
                    controladorPrincipal.crearCliente(
                        nombreCliente.getText(),
                        cedulaCliente.getText(),
                        telefonoCliente.getText(),
                        correoCliente.getText()
                    );
                    controladorPrincipal.mostrarAlerta(
                            "El cliente fue creado con exito.",
                            Alert.AlertType.INFORMATION
                    );
                } else {
                    controladorPrincipal.mostrarAlerta("El cliente que desea crear ya existe",
                            Alert.AlertType.INFORMATION);
                }
            } catch (Exception e){
                controladorPrincipal.mostrarAlerta("No ha sido posible crear el cliente. Inténtalo nuevamente",
                        Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos(){
        return  nombreCliente.getText().isEmpty() ||
                cedulaCliente.getText().isEmpty() ||
                telefonoCliente.getText().isEmpty() ||
                correoCliente.getText().isEmpty() ||
                nombreCliente.getText().isBlank() ||
                cedulaCliente.getText().isBlank() ||
                telefonoCliente.getText().isBlank() ||
                correoCliente.getText().isBlank() || !validarCamposNumericos();
    }

    private boolean validarCamposNumericos(){
        try{
            Integer.parseInt(cedulaCliente.getText());
            Integer.parseInt(telefonoCliente.getText());
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public void buscarCliente() {
        if(buscarCedula.getText().isEmpty() || buscarCedula.getText().isBlank()){
            controladorPrincipal.mostrarAlerta("Requiere un número de cédula para realizar la búsqueda.",
                    Alert.AlertType.WARNING);
        } else {
            try {
                Integer.parseInt(buscarCedula.getText());
                Cliente cliente = buscarCliente(buscarCedula.getText());
                if(cliente == null){
                    controladorPrincipal.mostrarAlerta("No se encontró cliente", Alert.AlertType.WARNING);
                }else {
                    Sesion sesion = controladorPrincipal.getSesion();
                    sesion.setCliente(cliente);
                    controladorPrincipal.navegarVentana(TipoPantalla.VISUALIZACION_CLIENTE.getRuta(), TipoPantalla.VISUALIZACION_CLIENTE.getNombre());
                }
            } catch (NumberFormatException e){
                controladorPrincipal.mostrarAlerta("Ingresa sólo números para buscar cliente.",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private Cliente buscarCliente(String cedula){
        try{
            return controladorPrincipal.buscarCliente(buscarCedula.getText());
        }catch (Exception e){
            return null;
        }
    }

    @FXML
    private void reporteMensual(){
        try {
            controladorPrincipal.navegarVentana(TipoPantalla.REPORTE_MENSUAL.getRuta(), TipoPantalla.REPORTE_MENSUAL.getNombre());
        }catch(Exception e){
            controladorPrincipal.mostrarAlerta("Lo sentimos, no se ha podido generar el reporte mensual.", Alert.AlertType.ERROR);
        }
    }

    public void buscarClientePlan() {
        if(cedulaNuevoPlan.getText().isEmpty() || cedulaNuevoPlan.getText().isBlank()){
            controladorPrincipal.mostrarAlerta("Requiere un número de cédula para realizar la búsqueda.",
                    Alert.AlertType.WARNING);
        } else {
            try {
                Integer.parseInt(cedulaNuevoPlan.getText());
                Cliente cliente = buscarCliente(cedulaNuevoPlan.getText());
                if(cliente == null){
                    controladorPrincipal.mostrarAlerta("No se encontró cliente", Alert.AlertType.WARNING);
                }else {
                    clienteNuevoPlan = cliente;
                    nombreClientePlan.setText(clienteNuevoPlan.getNombre());
                    telefonoClientePlan.setText(clienteNuevoPlan.getTelefono());
                    correoClientePlan.setText(clienteNuevoPlan.getCorreo());
                }
            } catch (NumberFormatException e){
                controladorPrincipal.mostrarAlerta("Ingresa sólo números para buscar cliente.",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private ArrayList<String> nombresServicios(ArrayList<Servicio> servicios){
        ArrayList<String> nombresServicio = new ArrayList<>();
        for (Servicio servicio : servicios) {
            nombresServicio.add(servicio.getNombre());
        }
        return nombresServicio;
    }

    public void obtenerServicioTV(ActionEvent actionEvent) {
    }

    public void obtenerServicioInternet(ActionEvent actionEvent) {
    }

    public void obtenerServicioTelefonia(ActionEvent actionEvent) {
    }

    public void CrearPlan(ActionEvent actionEvent) {
    }
    @FXML
    private void crearServicio(){
        try {
            controladorPrincipal.navegarVentana(TipoPantalla.CREAR_SERVICIO.getRuta(), TipoPantalla.CREAR_SERVICIO.getNombre());
        }catch(Exception e){
            controladorPrincipal.mostrarAlerta("Lo sentimos, no se ha podido generar el reporte mensual.", Alert.AlertType.ERROR);
        }
    }

}
