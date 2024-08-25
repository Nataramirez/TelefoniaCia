package co.edu.Telefonia.controlador;

import co.edu.Telefonia.modelos.Factura;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class ReporteMensualControlador implements Initializable {
    @FXML
    private Label facturasEncontradas;
    @FXML
    private Label IngresosTotales;

    @FXML
    private TableView<Factura> tablaFacturas;

    @FXML
    private TableColumn<Factura, String> id;

    @FXML
    private TableColumn<Factura, LocalDate> fecha;

    @FXML
    private TableColumn<Factura, String> direccion;

    @FXML
    private TableColumn<Factura, Float> costo;

    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<Factura> facturas = controladorPrincipal.generarReporteMensual();
            actualizarTabla(facturas);
            if (facturas == null || facturas.isEmpty()) {
                Platform.runLater(() -> controladorPrincipal.mostrarAlerta("Oh no!, no se han encontrado facturas", Alert.AlertType.INFORMATION));
            }
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Ha ocurrido un error al inicializar la pantalla, lo sentimos", Alert.AlertType.ERROR);
        }
    }

    public void actualizarTabla(ArrayList<Factura> facturas) {
        id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        fecha.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFecha()));
        direccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlan().getDireccion()));
        costo.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getCostoTotal()).asObject());

        if (facturas != null && !facturas.isEmpty()) {
            tablaFacturas.setItems(FXCollections.observableArrayList(facturas));
            facturasEncontradas.setText("Facturas encontradas: " + facturas.size());
            float totalIngresos = facturas.stream().map(Factura::getCostoTotal).reduce(0f, Float::sum);
            IngresosTotales.setText("Ingresos totales: " + totalIngresos);
        } else {
            tablaFacturas.setItems(FXCollections.observableArrayList());
            facturasEncontradas.setText("Facturas encontradas: 0");
            IngresosTotales.setText("Ingresos totales: 0");
        }
    }
}
