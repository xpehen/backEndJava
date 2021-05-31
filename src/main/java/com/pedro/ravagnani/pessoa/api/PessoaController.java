package com.pedro.ravagnani.pessoa.api;

import com.pedro.ravagnani.pessoa.domain.PessoaService;
import com.pedro.ravagnani.pessoa.domain.Pessoa;
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
@RequestMapping(path = "/pessoas")
@Api(value = "API de Pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping()
    @ApiOperation(value = "Retorna uma lista de pessoas.")
    public ResponseEntity<Page<Pessoa>> findAll(String filter, int page, int pageSize) {
        return ResponseEntity.ok().body(service.findAll(filter, page, pageSize));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Retorna uma pessoa.")
    public ResponseEntity<Pessoa> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Insere uma pessoa.")
    public ResponseEntity<Pessoa> insert(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaInserida = service.insert(pessoa);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pessoaInserida.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(pessoaInserida);
    }

    @PutMapping
    @ApiOperation(value = "Altera uma pessoa.")
    public ResponseEntity<Pessoa> update(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaAlterada = service.update(pessoa);
        URI createdResponse = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pessoaAlterada.getId()).build().toUri();
        return ResponseEntity.created(createdResponse).body(pessoaAlterada);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Exclui uma pessoa.")
    public ResponseEntity<Pessoa> deleteById(@PathVariable String id) {
        Pessoa pessoaDeletada = service.findById(id);
        service.delete(pessoaDeletada);
        return ResponseEntity.ok().body(pessoaDeletada);
    }

}
