package com.example.atvdcrudrpg.Controller;

import com.example.atvdcrudrpg.Model.itemmagico;
import com.example.atvdcrudrpg.Model.usermodel;
import com.example.atvdcrudrpg.Service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personagens")
public class usercontroller {

    @Autowired
    private userservice userService;

    @PostMapping
    public ResponseEntity<usermodel> criar(@RequestBody usermodel userModel) {
        return ResponseEntity.ok(userService.criarPersonagem(userModel));
    }

    @GetMapping
    public ResponseEntity<List<usermodel>> listar() {
        return ResponseEntity.ok(userService.listarPersonagem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<usermodel> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    @PutMapping("/{id}/nome-aventureiro")
    public ResponseEntity<usermodel> atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String nomeAventureiro) {
        return ResponseEntity.ok(userService.atualizarNomeAventureiro(id, nomeAventureiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        userService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<Void> adicionarItem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        userService.adicionarItem(personagemId, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        userService.removerItem(personagemId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{personagemId}/itens")
    public ResponseEntity<List<itemmagico>> listarItens(@PathVariable Long personagemId) {
        return ResponseEntity.ok(userService.listarItensMagicos(personagemId));
    }

    @GetMapping("/{personagemId}/amuleto")
    public ResponseEntity<itemmagico> buscarAmuleto(@PathVariable Long personagemId) {
        Optional<itemmagico> amuleto = userService.buscarAmuleto(personagemId);
        return amuleto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}