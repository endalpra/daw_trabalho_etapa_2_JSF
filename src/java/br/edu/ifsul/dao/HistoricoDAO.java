package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Historico;

import java.io.Serializable;

/**
 *
 * @author Érico
 */
public class HistoricoDAO<T> extends DAOGenerico<Historico> implements Serializable{
    public HistoricoDAO(){
        super();//chama construtor da classe pai
        super.setClassePersistente(Historico.class);
    }
}
