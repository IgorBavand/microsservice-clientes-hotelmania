package com.hotelmania.controller;

import com.hotelmania.exception.BadRequestException;
import com.hotelmania.exception.NotFoundException;
import com.hotelmania.service.ClienteService;
import com.hotelmania.service.dto.ClienteDto;
import com.hotelmania.service.filter.ClienteFilter;
import com.hotelmania.service.form.ClienteForm;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @ApiOperation(httpMethod = "GET", value = "busca todos os clientes", response = ClienteDto[].class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página que você quer receber.(0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Número de registros por página."),
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDto>> buscarClientes(ClienteFilter filter, @ApiIgnore Pageable pageable) throws NotFoundException {

        Page<ClienteDto> clientes = clienteService.buscarClientes(filter, pageable);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalRegistros", String.valueOf(clientes.getTotalElements()));
        responseHeaders.set("Access-Control-Expose-Headers", "totalRegistros");

        return ResponseEntity.ok().headers(responseHeaders).body(clientes.getContent());
    }

    @ApiOperation(httpMethod = "POST", value = "Cadastra um novo cliente", response = ClienteDto.class)
    @PostMapping("/salvar-cliente")
    public ResponseEntity<ClienteDto> salvarCliente(@RequestBody ClienteForm clienteForm) throws BadRequestException {
        return ResponseEntity.ok().body(clienteService.salvarCliente(clienteForm));
    }

    @ApiOperation(httpMethod = "PUT", value = "Cadastra um novo cliente", response = ClienteDto.class)
    @PutMapping("/atualizar-cliente/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@RequestBody ClienteForm clienteForm, @PathVariable UUID id) throws BadRequestException {
        return ResponseEntity.ok().body(clienteService.alterarCliente(clienteForm, id));
    }

}


























