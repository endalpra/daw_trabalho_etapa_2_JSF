/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Corrente;
import java.io.Serializable;

/**
 *
 * @author Érico
 */
public class CorrenteDAO<T> extends DAOGenerico<Corrente> implements Serializable {

    public CorrenteDAO() {
        super();//chama construtor da classe pai
        super.setClassePersistente(Corrente.class);
    }
}
