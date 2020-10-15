package DAOs;

import Entidades.Materia;
import Entidades.Turma;
import java.util.ArrayList;
import java.util.List;

public class DAOMateria extends DAOGenerico<Materia> {

    public DAOMateria() {
        super(Materia.class);
    }

    public int autoIdMateria() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idmateria) FROM Materia e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Materia> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Materia e WHERE e.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Materia> listById(int id) {
        return em.createQuery("SELECT e FROM Materia e WHERE e.idmateria = :id").setParameter("id", id).getResultList();
    }

    public List<Materia> listInOrderNome() {
        return em.createQuery("SELECT e FROM Materia e ORDER BY e.nome").getResultList();
    }

    public List<Materia> listInOrderId() {
        return em.createQuery("SELECT e FROM Materia e ORDER BY e.idmateria").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Materia> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdmateria()+ "-" + lf.get(i).getNome());
        }
        return ls;
    }
    public static void main(String[] args) {
        DAOMateria daoMateria = new DAOMateria();
        List<Materia> turmaList = daoMateria.list();
        for (Materia materia : turmaList) {
            System.out.println(materia.getIdmateria() + "-" + materia.getNome());
        }
    }
    
    
}
