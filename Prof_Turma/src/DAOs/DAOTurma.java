package DAOs;

import Entidades.Turma;
import java.util.ArrayList;
import java.util.List;

public class DAOTurma extends DAOGenerico<Turma> {

    public DAOTurma() {
        super(Turma.class);
    }

    public int autoIdTurma() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idjogo) FROM Turma e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Turma> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Turma e WHERE e.nomeTurma LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Turma> listById(int id) {
        return em.createQuery("SELECT e FROM Turma e WHERE e.idTurma = :id").setParameter("id", id).getResultList();
    }

    public List<Turma> listInOrderNome() {
        return em.createQuery("SELECT e FROM Turma e ORDER BY e.nomeTurma").getResultList();
    }

    public List<Turma> listInOrderId() {
        return em.createQuery("SELECT e FROM Turma e ORDER BY e.idTurma").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Turma> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();

        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTurma() + "-" + lf.get(i).getNomeTurma());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOTurma daoTurma = new DAOTurma();
        List<Turma> listaTurma = daoTurma.list();
        for (Turma turma : listaTurma) {
            System.out.println(turma.getIdTurma() + "-" + turma.getNomeTurma());
        }
    }
}
