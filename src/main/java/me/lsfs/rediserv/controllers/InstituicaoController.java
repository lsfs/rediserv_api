package me.lsfs.rediserv.controllers;

import me.lsfs.rediserv.models.Instituicao;
import me.lsfs.rediserv.models.dtos.InstituicaoSaveDTO;
import me.lsfs.rediserv.services.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    InstituicaoService instituicaoService;

    @Autowired
    public InstituicaoController(InstituicaoService instituicaoService) {
        this.instituicaoService = instituicaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Instituicao>> listar() {
        List<Instituicao> instituicoes = instituicaoService.listar();

        return ResponseEntity.ok().body(instituicoes);
    }

    @PostMapping
    public ResponseEntity<Instituicao> inserir(
            @RequestBody InstituicaoSaveDTO instituicaoSaveDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        Instituicao instituicaoSalva = instituicaoService.
                inserir(instituicaoSaveDTO);

        URI uri = uriComponentsBuilder.path("/instituicoes/{id}")
                .buildAndExpand(instituicaoSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(instituicaoSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> buscar(@PathVariable Long id){
        Instituicao instituicao = instituicaoService.buscar(id);

        return ResponseEntity.ok().body(instituicao);
    }

    @PutMapping("/{id}")
    public Instituicao alterar(
            @PathVariable Long id,
            @RequestBody InstituicaoSaveDTO instituicaoSaveDTO
    ){
        return instituicaoService.alterar(id, instituicaoSaveDTO);
    }

    @DeleteMapping("/{id}")
    public void apagar(
            @PathVariable Long id){
        instituicaoService.apagar(id);
    }


}