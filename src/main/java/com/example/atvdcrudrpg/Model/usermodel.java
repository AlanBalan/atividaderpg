package com.example.atvdcrudrpg.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class usermodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;
    private int forcaBase;
    private int defesaBase;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<itemmagico> itensMagicos;

    public usermodel() {
        this.itensMagicos = new ArrayList<>();
    }

    public int getForcaTotal() {
        return forcaBase + itensMagicos.stream().mapToInt(itemmagico::getForca).sum();
    }

    public int getDefesaTotal() {
        return defesaBase + itensMagicos.stream().mapToInt(itemmagico::getDefesa).sum();
    }

    public enum Classe {
        GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO
    }

    public usermodel(Long id, String nome, String nomeAventureiro, Classe classe, int level, int forcaBase, int defesaBase, List<itemmagico> itensMagicos) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
        this.itensMagicos = itensMagicos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public void setForcaBase(int forcaBase) {
        this.forcaBase = forcaBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(int defesaBase) {
        this.defesaBase = defesaBase;
    }

    public List<itemmagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<itemmagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }
}
