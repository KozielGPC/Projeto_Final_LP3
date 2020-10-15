package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.TurmaHasMateria;
import Entidades.Turma;
import Entidades.TurmaHasMateria;
import Entidades.TurmaHasMateriaPK;
import java.util.ArrayList;
import java.util.List;

public class DAOTurmaHasMateria extends DAOGenerico<TurmaHasMateria> {

    public DAOTurmaHasMateria() {
        super(TurmaHasMateria.class);
    }

    public int autoIdTurmaHasMateria() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTurmaHasMateria) FROM TurmaHasMateria e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TurmaHasMateria> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TurmaHasMateria e WHERE e.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TurmaHasMateria> listById(int id) {
        return em.createQuery("SELECT e FROM TurmaHasMateria e WHERE e.idTurmaHasMateria = :id").setParameter("id", id).getResultList();
    }

    public List<TurmaHasMateria> listInOrderNome() {
        return em.createQuery("SELECT e FROM TurmaHasMateria e ORDER BY e.nome").getResultList();
    }

    public List<TurmaHasMateria> listInOrderId() {
        return em.createQuery("SELECT e FROM TurmaHasMateria e ORDER BY e.idTurmaHasMateria").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TurmaHasMateria> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getTurmaHasMateriaPK()+ " - Turma: " + lf.get(i).getTurma().getIdTurma()+ " - " + lf.get(i).getTurma().getNomeTurma()
             + " - Matéria: " + lf.get(i).getMateria().getIdmateria()+ " - " + lf.get(i).getMateria().getNome());
        }
        return ls;
    }
    public TurmaHasMateria obter(TurmaHasMateriaPK turmaHasMateriaPK) {

        return em.find(TurmaHasMateria.class, turmaHasMateriaPK);

    }
    public static void main(String[] args) {
        DAOTurmaHasMateria daoTurmaHasMateria = new DAOTurmaHasMateria();
        List<TurmaHasMateria> turmaList = daoTurmaHasMateria.list();
        for (TurmaHasMateria TurmaHasMateria : turmaList) {
            System.out.println(TurmaHasMateria.getTurmaHasMateriaPK().getMateriaIdmateria() + " - " + TurmaHasMateria.getTurmaHasMateriaPK().getTurmaidTurma());
        }
    }
    
    
}
