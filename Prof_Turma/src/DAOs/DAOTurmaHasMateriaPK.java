package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.TurmaHasMateriaPK;
import Entidades.Turma;
import Entidades.TurmaHasMateriaPK;
import java.util.ArrayList;
import java.util.List;

public class DAOTurmaHasMateriaPK extends DAOGenerico<TurmaHasMateriaPK> {

    private List<TurmaHasMateriaPK> lista = new ArrayList<>();

    public DAOTurmaHasMateriaPK() {
        super(TurmaHasMateriaPK.class);
    }

    public static void main(String[] args) {
        DAOTurmaHasMateriaPK daoTurmaHasMateriaPK = new DAOTurmaHasMateriaPK();
        List<TurmaHasMateriaPK> turmaList = daoTurmaHasMateriaPK.list();
        for (TurmaHasMateriaPK TurmaHasMateriaPK : turmaList) {
            System.out.println(TurmaHasMateriaPK.getMateriaIdmateria() + " - " + TurmaHasMateriaPK.getTurmaidTurma());
        }
    }

}
