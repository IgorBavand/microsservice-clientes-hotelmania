package com.hotelmania.service;

import com.hotelmania.exception.BadRequestException;
import com.hotelmania.exception.NotFoundException;
import com.hotelmania.model.Cliente;
import com.hotelmania.repository.ClienteRepository;
import com.hotelmania.repository.specification.ClienteSpecification;
import com.hotelmania.service.dto.ClienteDto;
import com.hotelmania.service.filter.ClienteFilter;
import com.hotelmania.service.form.ClienteForm;
import com.hotelmania.service.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public Page<ClienteDto> buscarClientes(ClienteFilter filter, Pageable pageable) throws NotFoundException {
        Page<Cliente> clientes = clienteRepository.findAll(ClienteSpecification.of(filter), pageable);
        log.info("buscando clientes..");
        if(!clientes.hasContent()){
            throw new NotFoundException("Nenhum cliente encontrado");
        }
        return new PageImpl<>(clienteMapper.toDtos(clientes.getContent()), pageable, clientes.getTotalElements());
    }

    public ClienteDto salvarCliente(ClienteForm clienteForm) throws BadRequestException {
        Cliente cliente = clienteMapper.toEntity(clienteForm);

        try {
            cliente = clienteRepository.save(cliente);
        }catch (Exception e){
            throw new BadRequestException("nao foi possivel salvar o cliente");
        }

        return clienteMapper.toDto(cliente);
    }

    @Transactional
    public ClienteDto alterarCliente(ClienteForm clienteForm, UUID id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(!cliente.isPresent()){
            throw new NotFoundException("CLiente nao encontrado");
        }

        if(clienteForm.getCepCliente() == null){
            clienteForm.setCepCliente(cliente.get().getCepCliente());
        }
        if(clienteForm.getCidadeCliente() == null){
            clienteForm.setCidadeCliente(cliente.get().getCidadeCliente());
        }
        if (clienteForm.getNomeCliente() == null){
            clienteForm.setNomeCliente(cliente.get().getNomeCliente());
        }
        if (clienteForm.getRuaCliente() == null){
            clienteForm.setRuaCliente(cliente.get().getRuaCliente());
        }
        if (clienteForm.getCpfCliente() == null){
            clienteForm.setCpfCliente(cliente.get().getCpfCliente());
        }
        if (clienteForm.getNumeroResidenciaCliente() == null){
            clienteForm.setNumeroResidenciaCliente(cliente.get().getNumeroResidenciaCliente());
        }
        if (clienteForm.getTelefoneCliente() == null){
            clienteForm.setTelefoneCliente(cliente.get().getTelefoneCliente());
        }
        //atualiza o cliente.get() com os valores que estao no clienteForm
        clienteMapper.atualizarCliente(clienteForm, cliente.get());


        Cliente clienteSalvo;
        try {
            clienteSalvo = clienteRepository.save(cliente.get());
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível alterar cliente! " + e.getMessage());
        }
        log.info("alterando cliente");
        return clienteMapper.toDto(clienteSalvo);

    }
}




















