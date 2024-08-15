package co.edu.Telefonia.modelos.enums;
import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoServicioInternet {
    MEGAS_500("500MEGAS",   "Hasta 4 puntos cableados", 165900),
    MEGAS_450("450MEGAS", "Hasta 3 puntos cableados", 125900),
    MEGAS_350("350MEGAS", "Hasta 2 puntos cableados", 89900),
    MEGAS_250("250MEGAS", "Hasta 1 puntos cableados", 75900),;

    private String categoria;
    private String descripcion;
    private float precio;
}