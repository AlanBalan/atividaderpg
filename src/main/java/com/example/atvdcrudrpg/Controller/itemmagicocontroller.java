package com.example.atvdcrudrpg.Controller;

import com.example.atvdcrudrpg.Model.itemmagico;
import com.example.atvdcrudrpg.Service.itemmagicoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class itemmagicocontroller {

    @Autowired
    private itemmagicoservice itemMagicoService;

    @PostMapping
    public ResponseEntity<itemmagico> criar(@RequestBody itemmagico itemMagico) {
        return ResponseEntity.ok(itemMagicoService.criarItem(itemMagico));
    }

    @GetMapping
    public ResponseEntity<List<itemmagico>> listar() {
        return ResponseEntity.ok(itemMagicoService.listarItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<itemmagico> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        itemMagicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}