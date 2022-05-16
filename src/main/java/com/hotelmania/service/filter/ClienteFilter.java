package com.hotelmania.service.filter;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ClienteFilter {

    private String nomeCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String cpfCliente;
}
