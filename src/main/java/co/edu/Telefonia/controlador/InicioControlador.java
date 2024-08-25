package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Cliente;
import co.edu.Telefonia.modelos.Sesion;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class InicioControlador {
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

    public InicioControlador() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        System.out.println("CLIENTES: " + controladorPrincipal.getTelefoniaCia().getClientes());
    }


    public void registrarCliente() {
        if(validarCampos() && !validarCamposNumericos()){
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
                correoCliente.getText().isBlank();
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

}
