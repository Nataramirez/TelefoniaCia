package co.edu.Telefonia.modelos.enums;
import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoServicioTelefonia {
    BASICO("BASICO",   "Sólo llamadas fijo nacionales", 52900),
    ESTANDAR("ESTANDAR", "Llamadas fijo nacionales y celular", 85900);

    private String categoria;
    private String descripcion;
    private float precio;
}
