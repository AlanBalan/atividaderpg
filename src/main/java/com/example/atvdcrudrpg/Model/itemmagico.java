package com.example.atvdcrudrpg.Model;

import jakarta.persistence.*;

@Entity
public class itemmagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private usermodel personagem;

    public enum Tipo {
        ARMA, ARMADURA, AMULETO
    }

    @PrePersist
    @PreUpdate
    private void validarItem() {
        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("Item não pode ter força e defesa iguais a 0.");
        }
        if (forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("Força e defesa não podem ser maiores que 10.");
        }
        if (tipo == Tipo.ARMA && defesa != 0) {
            throw new IllegalArgumentException("Armas não podem ter defesa diferente de 0.");
        }
        if (tipo == Tipo.ARMADURA && forca != 0) {
            throw new IllegalArgumentException("Armaduras não podem ter força diferente de 0.");
        }
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public usermodel getPersonagem() {
        return personagem;
    }

    public void setPersonagem(usermodel personagem) {
        this.personagem = personagem;
    }
}
