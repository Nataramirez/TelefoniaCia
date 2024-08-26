package co.edu.Telefonia.modelos.enums;
import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoServicioTv {
    SUPERIOR("SUPERIOR", "185 canales", 85900),
    INTERMEDIO("INTERMEDIO", "105 canales", 79300),
    BASICO("BASICO", "54 canales", 63900);

    private String categoria;
    private String descripcion;
    private float precio;
}
