package co.edu.Telefonia.modelos.enums;
import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoServicioTv {
    SUPERIOR("superior", "185 canales", 85900),
    INTERMEDIO("intermedio", "105 canales", 79300),
    BASICO("básico", "54 canales", 63900);

    private String categoria;
    private String descripcion;
    private float precio;
}
