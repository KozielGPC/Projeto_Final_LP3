package DAOs;

import Entidades.Professor;
import java.util.ArrayList;
import java.util.List;

public class DAOProfessor extends DAOGenerico<Professor> {

    public DAOProfessor() {
        super(Professor.class);
    }

    public int autoIdProfessor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idProfessor) FROM Professor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Professor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Professor e WHERE e.nomeProfessor LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Professor> listById(int id) {
        return em.createQuery("SELECT e FROM Professor e WHERE e.idProfessor = :id").setParameter("id", id).getResultList();
    }

    public List<Professor> listInOrderNome() {
        return em.createQuery("SELECT e FROM Professor e ORDER BY e.nomeProfessor").getResultList();
    }

    public List<Professor> listInOrderId() {
        return em.createQuery("SELECT e FROM Professor e ORDER BY e.idProfessor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Professor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdProfessor()+ "-" + lf.get(i).getNomeProfessor());
        }
        return ls;
    }
}
