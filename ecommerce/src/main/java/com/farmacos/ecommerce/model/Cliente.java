/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 009432631
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTE")
@Entity
@Getter
@Setter
public class Cliente {

    private static final long serialVersionUID = -3766858690685689916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "NOME_C", nullable = false)
    private String nomeC;

    @Column(name = "CPF_C", nullable = false, unique = true)
    private String cpfC;

    @Column(name = "EMAIL_C", nullable = false)
    private String emailC;

    @Column(name = "SENHA_C", nullable = false)
    private String senhaC;

    @Column(name = "DATA_NASC_C")
    private String dataNascC;

    @Column(name = "GENERO_C")
    private String generoC;
    
    //isso tá certo?
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cliente_endereco", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"), 
                inverseJoinColumns = @JoinColumn(name = "endereco_id", referencedColumnName = "id"))
    @Column(name = "ENDERECO", nullable = false)
    private Collection<Endereco> endereco;

}
