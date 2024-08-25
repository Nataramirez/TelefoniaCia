package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.*;
import co.edu.Telefonia.modelos.enums.TipoPantalla;
import co.edu.Telefonia.modelos.enums.TipoServicioInternet;
import co.edu.Telefonia.modelos.enums.TipoServicioTelefonia;
import co.edu.Telefonia.modelos.enums.TipoServicioTv;
import co.edu.Telefonia.servicios.ServiciosEmpresa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ControladorPrincipal implements ServiciosEmpresa {
    private final TelefoniaCia telefoniaCia;
    public static ControladorPrincipal INSTANCIA;

    @Getter
    private final Sesion sesion;

    private ControladorPrincipal() {
        telefoniaCia = new TelefoniaCia();
        sesion = new Sesion();
    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }




    public FXMLLoader navegarVentana(String nombreArchivoFxml, String tituloVentana){
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);
            /**
            if(nombreArchivoFxml.equals(nombreArchivoFxml.equals(TipoPantalla.INICIO.getRuta()))){
                stage.setMaximized(true);
            }*/

            // Mostrar la nueva ventana
            stage.show();
            return loader;

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setHeaderText(mensaje);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @Override
    public Cliente crearCliente(String nombre, String cedula, String telefono, String correo) throws Exception {
        return telefoniaCia.crearCliente(nombre, cedula, telefono, correo);
    }

    @Override
    public Cliente buscarCliente(String cedula) throws Exception {
        return telefoniaCia.buscarCliente(cedula);
    }

    @Override
    public ServicioTelefonia crearServicio(TipoServicioTelefonia servicioTelefonia) throws Exception {
        return telefoniaCia.crearServicio(servicioTelefonia);
    }

    @Override
    public ServicioTv crearServicio(TipoServicioTv servicioTv) throws Exception {
        return telefoniaCia.crearServicio(servicioTv);
    }

    @Override
    public ServicioInternet crearServicio(TipoServicioInternet servicioInternet) throws Exception {
        return telefoniaCia.crearServicio(servicioInternet);
    }

    @Override
    public Cliente actuallizarCliente(String nombre, String telefono, String correo, String cedulaActual, String cedulaNueva) throws Exception {
        return telefoniaCia.actuallizarCliente(nombre, telefono, correo, cedulaActual, cedulaNueva);
    }

    @Override
    public Boolean validarString(String cadena) throws Exception {
        return telefoniaCia.validarString(cadena);
    }

    @Override
    public String escogerPrimeroNoNulo(String cadena1, String cadena2) throws Exception {
        return telefoniaCia.escogerPrimeroNoNulo(cadena1, cadena2);
    }

    @Override
    public Plan validarDireccion(String direccion) throws Exception {
        return telefoniaCia.validarDireccion(direccion);
    }

    @Override
    public float calcularCostoTotalMensual(Plan plan) throws Exception {
        return telefoniaCia.calcularCostoTotalMensual(plan);
    }

    @Override
    public Plan crearPlan(String cedulaCliente, String direccion, TipoServicioTelefonia servicioTelefono, TipoServicioTv servicioTv, TipoServicioInternet servicioInternet) throws Exception {
        return telefoniaCia.crearPlan(cedulaCliente, direccion, servicioTelefono, servicioTv, servicioInternet);
    }

    @Override
    public ArrayList<Plan> listarPlanesPorCliente(String cedula) throws Exception {
        return telefoniaCia.listarPlanesPorCliente(cedula);
    }

    @Override
    public int contarMesesPlan(Plan plan) throws Exception {
        return telefoniaCia.contarMesesPlan(plan);
    }

    @Override
    public Factura crearFactura(Cliente cliente, Plan plan) throws Exception {
        return telefoniaCia.crearFactura(cliente, plan);
    }

    @Override
    public void enviarFacturas() throws Exception {
        telefoniaCia.enviarFacturas();
    }

    @Override
    public ArrayList<Factura> generarReporteMensual() throws Exception {
        return telefoniaCia.generarReporteMensual();
    }
}
