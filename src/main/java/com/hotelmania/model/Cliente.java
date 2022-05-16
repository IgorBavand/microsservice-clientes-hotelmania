package com.hotelmania.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "BINARY(16)", name = "id_cliente", unique = true, nullable = false, updatable = false)
    private UUID idCliente;

    @Column(name = "cliente_nome")
    private String nomeCliente;

    @Column(name = "cliente_telefone")
    private String telefoneCliente;

    @Column(name = "cliente_cep")
    private String cepCliente;

    @Column(name = "cliente_cidade")
    private String cidadeCliente;

    @Column(name = "cliente_rua")
    private String ruaCliente;

    @Column(name = "cliente_numero_residencia")
    private String numeroResidenciaCliente;

    @Column(name = "cliente_cpf")
    private String cpfCliente;

}
