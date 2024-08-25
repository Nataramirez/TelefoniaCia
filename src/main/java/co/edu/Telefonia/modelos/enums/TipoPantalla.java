package co.edu.Telefonia.modelos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPantalla {
    INICIO("/inicio.fxml", "TelefoniaCia Inicio"),
    VISUALIZACION_CLIENTE("/vistaCliente.fxml", "TelefonoaCia | Visualizaion Cliente"),
    REPORTE_MENSUAL("/reporteMensual.fxml","TelefonoaCia | Reporte Mensual");

    private String ruta;
    private String nombre;
}
