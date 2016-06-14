package br.edu.ifsul.controle;


import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Érico
 */
@ManagedBean(name = "controleSeguro")
@SessionScoped
public class ControleSeguro implements Serializable {

    private SeguroDAO<Seguro> dao;
    private Seguro objeto;
    private ClienteDAO<Cliente> daoCliente;

    public ControleSeguro() {
        dao = new SeguroDAO<>();
        daoCliente = new ClienteDAO<>();
    }

    public String listar() {
        return "/privado/seguro/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Seguro();
        return "formulario";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }

    public String cancelar() {
        return "listar";
    }

    public String editar(Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar";
        }
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public SeguroDAO<Seguro> getDao() {
        return dao;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    public void setDao(SeguroDAO dao) {
        this.dao = dao;
    }

    public Seguro getObjeto() {
        return objeto;
    }

    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

}
