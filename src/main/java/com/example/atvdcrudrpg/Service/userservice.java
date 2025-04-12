package com.example.atvdcrudrpg.Service;

import com.example.atvdcrudrpg.Model.itemmagico;
import com.example.atvdcrudrpg.Model.usermodel;
import com.example.atvdcrudrpg.Repository.itemmagicorepository;
import com.example.atvdcrudrpg.Repository.userrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userservice {

    @Autowired
    private userrepository userRepository;

    @Autowired
    private itemmagicorepository itemMagicoRepository;

    @Transactional
    public usermodel criarPersonagem(usermodel personagem) {
        int totalPontosBase = personagem.getForcaBase() + personagem.getDefesaBase();
        if (totalPontosBase > 10) {
            throw new IllegalArgumentException("Força base + Defesa base não pode ultrapassar 10 pontos.");
        }

        long amuletos = personagem.getItensMagicos().stream()
                .filter(i -> i.getTipo() == itemmagico.Tipo.AMULETO)
                .count();
        if (amuletos > 1) {
            throw new IllegalArgumentException("O personagem só pode ter um Amuleto.");
        }

        personagem.getItensMagicos().forEach(i -> i.setPersonagem(personagem));
        return userRepository.save(personagem);
    }

    public List<usermodel> listarPersonagem() {
        return userRepository.findAll();
    }

    public usermodel buscarPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
    }

    @Transactional
    public usermodel atualizarNomeAventureiro(Long id, String nomeAventureiro) {
        usermodel personagem = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
        personagem.setNomeAventureiro(nomeAventureiro);
        return userRepository.save(personagem);
    }

    @Transactional
    public void excluir(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Personagem não encontrado.");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void adicionarItem(Long personagemId, Long itemId) {
        usermodel personagem = userRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
        itemmagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado."));
        System.out.println(item);
        if (item.getTipo() == itemmagico.Tipo.AMULETO) {
            long amuletos = personagem.getItensMagicos().stream()
                    .filter(i -> i.getTipo() == itemmagico.Tipo.AMULETO)
                    .count();
            if (amuletos > 0) {
                throw new IllegalArgumentException("O personagem já possui um Amuleto.");
            }
        }

        item.setPersonagem(personagem);
        personagem.getItensMagicos().add(item);
        userRepository.save(personagem);
    }

    @Transactional
    public void removerItem(Long personagemId, Long itemId) {
        usermodel personagem = userRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
        itemmagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado."));

        if (!personagem.getItensMagicos().contains(item)) {
            throw new IllegalArgumentException("Item não pertence a este personagem.");
        }

        personagem.getItensMagicos().remove(item);
        item.setPersonagem(null);
        userRepository.save(personagem);
        itemMagicoRepository.save(item);
    }

    public List<itemmagico> listarItensMagicos(Long personagemId) {
        usermodel personagem = userRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
        return personagem.getItensMagicos();
    }

    public Optional<itemmagico> buscarAmuleto(Long personagemId) {
        usermodel personagem = userRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado."));
        return personagem.getItensMagicos().stream()
                .filter(i -> i.getTipo() == itemmagico.Tipo.AMULETO)
                .findFirst();
    }
}