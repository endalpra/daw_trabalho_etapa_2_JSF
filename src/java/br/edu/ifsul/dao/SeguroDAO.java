package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Seguro;

import java.io.Serializable;

/**
 *
 * @author Érico
 */
public class SeguroDAO<T> extends DAOGenerico<Seguro> implements Serializable{
    public SeguroDAO(){
        super();//chama construtor da classe pai
        super.setClassePersistente(Seguro.class);
    }
}
