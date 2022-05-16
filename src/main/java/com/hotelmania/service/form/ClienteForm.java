package com.hotelmania.service.form;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ClienteForm {

    private String nomeCliente;
    private String telefoneCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String ruaCliente;
    private String numeroResidenciaCliente;
    private String cpfCliente;
}
