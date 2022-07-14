package ec.edu.espe.vargaslistaobservadosp2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String cedula;

    private String NombreCompleto;

    private String estado;
}
