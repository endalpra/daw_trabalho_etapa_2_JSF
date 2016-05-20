package br.edu.ifsul.controle;


import br.edu.ifsul.dao.AgenciaDAO;
import br.edu.ifsul.modelo.Agencia;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Érico
 */
@ManagedBean(name = "controleAgencia")
@SessionScoped
public class ControleAgencia implements Serializable{
    private AgenciaDAO<Agencia> dao;
    private Agencia objeto;
    
    public ControleAgencia(){
        dao = new AgenciaDAO<>();
    } 

    public String listar(){
        return "/privado/agencia/listar?faces-redirect=true";        
   }
    public String novo(){
        objeto = new Agencia();
        return "formulario";
    }
    
    public String salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
        }else{
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try{
            objeto = dao.localizar(id);
            return "formulario";
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
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
    
    public AgenciaDAO getDao() {
        return dao;
    }

    public void setDao(AgenciaDAO dao) {
        this.dao = dao;
    }

    public Agencia getObjeto() {
        return objeto;
    }

    public void setObjeto(Agencia objeto) {
        this.objeto = objeto;
    }
    
}
