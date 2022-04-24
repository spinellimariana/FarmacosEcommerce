/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 009432631
 */
@Entity
@Table(name = "ENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

    private static final long serialVersionUID = -3766858690685689916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "CEP", nullable = false)
    private String cepF;

    @Column(name = "LOGRADOURO", nullable = false)
    private String logradouroF;

    @Column(name = "NUMERO", nullable = false)
    private String numeroF;

    @Column(name = "COMPLEMENTO", nullable = false)
    private String complementoF;

    @Column(name = "BAIRRO", nullable = false)
    private String bairroF;

    @Column(name = "UF", nullable = false)
    private String ufF;

    @Column(name = "CIDADE", nullable = false)
    private String cidadeF;
    
	@ManyToOne
	@JsonIgnore
	private Cliente cliente;
    
    

}
