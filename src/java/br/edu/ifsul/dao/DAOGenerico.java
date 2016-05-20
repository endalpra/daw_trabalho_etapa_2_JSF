package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Érico
 */
public class DAOGenerico<T> implements Serializable {

    //atributo para armazenar a consulta paginada
    private List<T> listaObjetos;
    //atributo que armazena a consulta de todos os objetos da classe
    private List<T> listaTodos;
    //atributo armazena a classe do DAO
    private Class classePersistente;
    //atributo para armazenar as mensagens de erro
    private String mensagem = "";
    private EntityManager em;
    private String ordem = "id";
    private String filtro = "";

    public DAOGenerico() {
        //inicia a EntityManager
        em = EntityManagerUtil.getEntityManager();
    }

    public List<T> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";      

        if (filtro.length() > 0) {
            //Retirar caracteres especiais do filtro
            String[] carEspeciais = {"\"","'","+","-","=","_","§","ª","^","~","¨", "@", "#", "$", "%","*", "&", "(", ")", "\\", "|", "/", "?", ",", ".", ":", ";", "!","-","--","{","}","°","<",">","[","]"};
            for (String s : carEspeciais) {
                filtro = filtro.replace(s, "");
            }
            if (ordem.equals("id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<T> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public boolean persist(T obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean merge(T obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean remover(T obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public T localizar(Integer id) {
        return (T) em.find(classePersistente, id);
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
