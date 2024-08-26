package co.edu.Telefonia.modelos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPantalla {
    INICIO("/inicio.fxml", "TelefoniaCia Inicio"),
    VISUALIZACION_CLIENTE("/vistaCliente.fxml", "TelefonoaCia | Visualizaion Cliente"),
    CREAR_SERVICIO("/crearServicio.fxml", "TelefonoaCia | Creacion de servicios"),
    CONSULTAR_SERVICIO("/consultarServicio.fxml", "TelefonoaCia | Consulta de servicio"),
    REPORTE_MENSUAL("/reporteMensual.fxml","TelefonoaCia | Reporte Mensual");

    private String ruta;
    private String nombre;
}
