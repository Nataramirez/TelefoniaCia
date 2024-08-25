package co.edu.Telefonia.modelos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPantalla {
    INICIO("/inicio.fxml", "TelefoniaCia Inicio"),
    VISUALIZACION_CLIENTE("/vistaCliente.fxml", "TelefonoaCia | Visualizaion Cliente");
    private String ruta;
    private String nombre;
}
