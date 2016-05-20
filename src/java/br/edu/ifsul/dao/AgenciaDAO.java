package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Agencia;
import java.io.Serializable;

/**
 *
 * @author Érico
 */
public class AgenciaDAO<T> extends DAOGenerico<Agencia> implements Serializable {

    public AgenciaDAO() {
        super();//chama construtor da classe pai
        super.setClassePersistente(Agencia.class);
    }
}
