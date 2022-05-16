package com.hotelmania.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClienteDto {

    private UUID idCliente;
    private String nomeCliente;
    private String telefoneCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String ruaCliente;
    private String numeroResidenciaCliente;
    private String cpfCliente;
}
