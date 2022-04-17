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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 009432631
 */
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

    private static final long serialVersionUID = -3766858690685689916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "CEP_F", nullable = false)
    private String cepF;

    @Column(name = "CEP_E", nullable = false)
    private String cepE;

    @Column(name = "LOGRADOURO_F", nullable = false)
    private String logradouroF;

    @Column(name = "LOGRADOURO_E", nullable = false)
    private String logradouroE;

    @Column(name = "NUMERO_F", nullable = false)
    private String numeroF;

    @Column(name = "NUMERO_E", nullable = false)
    private String numeroE;

    @Column(name = "COMPLEMENTO_F", nullable = false)
    private String complementoF;

    @Column(name = "COMPLEMENTO_E", nullable = false)
    private String complementoE;

    @Column(name = "BAIRRO_F", nullable = false)
    private String bairroF;

    @Column(name = "BAIRRO_E", nullable = false)
    private String bairroE;

    @Column(name = "UF_F", nullable = false)
    private String ufF;

    @Column(name = "UF_E", nullable = false)
    private String ufE;

    @Column(name = "CIDADE_F", nullable = false)
    private String cidadeF;

    @Column(name = "CIDADE_E", nullable = false)
    private String cidadeE;

}
