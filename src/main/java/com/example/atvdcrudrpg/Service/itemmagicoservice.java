package com.example.atvdcrudrpg.Service;

import com.example.atvdcrudrpg.Model.itemmagico;
import com.example.atvdcrudrpg.Repository.itemmagicorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class itemmagicoservice {

    @Autowired
    private itemmagicorepository itemMagicoRepository;

    public itemmagico criarItem(itemmagico itemMagico) {
        return itemMagicoRepository.save(itemMagico);
    }

    public List<itemmagico> listarItems() {
        return itemMagicoRepository.findAll();
    }

    public itemmagico buscarPorId(Long id) {
        return itemMagicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item mágico não encontrado."));
    }

    public void excluir(Long id) {
        if (!itemMagicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Item mágico não encontrado.");
        }
        itemMagicoRepository.deleteById(id);
    }
}