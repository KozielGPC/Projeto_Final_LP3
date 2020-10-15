package DAOs;

import Entidades.Turma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAOGenerico<T> {

    public static EntityManager em = Persistence.createEntityManagerFactory("UP").createEntityManager();
    private Class clazz;
    private List<Turma> turmas;
    private int id_materia;

    public DAOGenerico(Class clazz) {
        this.clazz = clazz;
    }

    public DAOGenerico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void inserir(T e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public void atualizar(T e) {
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    public void remover(T e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public T obter(Long id) {
        return (T) em.find(clazz, id);
    }

    public T obter(Integer id) {
        return (T) em.find(clazz, id);
    }

    public List<T> list() {
        return em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    public void SetTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public void SetMateria(int id_materia) {
        this.id_materia = id_materia;
    }
    
}
