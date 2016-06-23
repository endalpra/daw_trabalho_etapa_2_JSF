package br.edu.ifsul.controle;


import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Telefone;
import br.edu.ifsul.modelo.TelefoneId;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Érico
 */
@ManagedBean(name = "controleCliente")
@SessionScoped
public class ControleCliente implements Serializable{
    private ClienteDAO<Cliente> dao;
    private Cliente objeto;
    private Telefone telefone;
    private Boolean novoTelefone;
    
    public ControleCliente(){
        dao = new ClienteDAO<>();
    } 
  
    public void novoTelefone(){
        telefone = new Telefone();
        telefone.setTelefoneId(new TelefoneId());
        novoTelefone = true;
    }
    
    public void alterarTelefone(int index){
        telefone = objeto.getTelefones().get(index);
        novoTelefone = false;
    }
    public void salvarTelefone(){
        if(novoTelefone){
            objeto.adicionarTelefone(telefone);
        }else{
           
        }
        Util.mensagemInformacao("Telefone salvo com sucesso");
    }
    
    public void removerTelefone(int index){
        objeto.removerTelefone(index);
        Util.mensagemInformacao("Item removido comsucesso");
    }

    public String listar(){
        return "/privado/cliente/listar?faces-redirect=true";        
   }
    public void novo(){
        objeto = new Cliente();
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
        }else{
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public void editar(Integer id){
        try{
            objeto = dao.localizar(id);
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
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

    public ClienteDAO<Cliente> getDao() {
        return dao;
    }

    public void setDao(ClienteDAO<Cliente> dao) {
        this.dao = dao;
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Boolean getNovoTelefone() {
        return novoTelefone;
    }

    public void setNovoTelefone(Boolean novoTelefone) {
        this.novoTelefone = novoTelefone;
    }

    
    
}
