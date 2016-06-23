package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AgenciaDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.CorrenteDAO;
import br.edu.ifsul.modelo.Agencia;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Corrente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Érico
 */
@ManagedBean(name = "controleContaCorrente")
@ViewScoped
public class ControleContaCorrente implements Serializable{
    private CorrenteDAO<Corrente> dao;
    private ClienteDAO<Cliente> daoCliente;
    private AgenciaDAO<Agencia> daoAgencia;
    private Corrente objeto;
    
    public ControleContaCorrente(){
        dao = new CorrenteDAO<>();  
        daoCliente = new ClienteDAO<>();
        daoAgencia = new AgenciaDAO<>();
    }
     
    public String listar(){
        return "/privado/contaCorrente/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Corrente();        
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId()==null){
            persistiu = dao.persist(objeto);
        }else{
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar(Integer id){
        try{
            objeto = dao.localizar(id);
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public CorrenteDAO<Corrente> getDao() {
        return dao;
    }

    public void setDao(CorrenteDAO<Corrente> dao) {
        this.dao = dao;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    public AgenciaDAO<Agencia> getDaoAgencia() {
        return daoAgencia;
    }

    public void setDaoAgencia(AgenciaDAO<Agencia> daoAgencia) {
        this.daoAgencia = daoAgencia;
    }

    public Corrente getObjeto() {
        return objeto;
    }

    public void setObjeto(Corrente objeto) {
        this.objeto = objeto;
    }

   
    
    
}
