package co.edu.Telefonia.modelos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPantalla {
    INICIO("/inicio.fxml", "TelefoniaCia Inicio");
    private String ruta;
    private String nombre;
}
