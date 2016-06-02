package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;

/**
 *
 * @author Érico
 */
public class ClienteDAO<T> extends DAOGenerico<Cliente> implements Serializable {

    public ClienteDAO() {
        super();//chama construtor da classe pai
        super.setClassePersistente(Cliente.class);
    }
}
