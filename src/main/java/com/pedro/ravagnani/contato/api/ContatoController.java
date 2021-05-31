package com.pedro.ravagnani.contato.api;

import com.pedro.ravagnani.contato.domain.Contato;
import com.pedro.ravagnani.contato.domain.ContatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(path = "/contatos")
@Api(value = "API de Contatos")
@CrossOrigin(origins = "*")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna um contato especifico.")
    public ResponseEntity<Contato> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping()
    @ApiOperation(value = "Recupera uma lista com todos os contatos.")
    public ResponseEntity<Page<Contato>> findAll(String filter, int page, int pageSize) {
        return ResponseEntity.ok().body(service.findAll(filter, page, pageSize));
    }

    @PostMapping
    @ApiOperation(value = "Insere um contato.")
    public ResponseEntity<Contato> insert(@Valid @RequestBody Contato contato) {
        Contato contatoCriado = service.insert(contato);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(contatoCriado.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(contatoCriado);
    }

    @PutMapping
    @ApiOperation(value = "Altera um contato.")
    public ResponseEntity<Contato> update(@Valid @RequestBody Contato contato) {
        Contato contatoAlterado = service.update(contato);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(contatoAlterado.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(contatoAlterado);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Realiza a exclusao de um contato.")
    public ResponseEntity<Contato> deleteById(@PathVariable String id) {
        Contato contatoDeletado = service.findById(id);
        service.delete(contatoDeletado);
        return ResponseEntity.ok().body(contatoDeletado);
    }


}
