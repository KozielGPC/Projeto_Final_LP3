/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gpcga
 */
@Embeddable
public class TurmaHasMateriaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "turma_idTurma")
    private int turmaidTurma;
    @Basic(optional = false)
    @Column(name = "materia_idmateria")
    private int materiaIdmateria;

    public TurmaHasMateriaPK() {
    }

    public TurmaHasMateriaPK(int turmaidTurma, int materiaIdmateria) {
        this.turmaidTurma = turmaidTurma;
        this.materiaIdmateria = materiaIdmateria;
    }

    public int getTurmaidTurma() {
        return turmaidTurma;
    }

    public void setTurmaidTurma(int turmaidTurma) {
        this.turmaidTurma = turmaidTurma;
    }

    public int getMateriaIdmateria() {
        return materiaIdmateria;
    }

    public void setMateriaIdmateria(int materiaIdmateria) {
        this.materiaIdmateria = materiaIdmateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) turmaidTurma;
        hash += (int) materiaIdmateria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaHasMateriaPK)) {
            return false;
        }
        TurmaHasMateriaPK other = (TurmaHasMateriaPK) object;
        if (this.turmaidTurma != other.turmaidTurma) {
            return false;
        }
        if (this.materiaIdmateria != other.materiaIdmateria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TurmaHasMateriaPK[ turmaidTurma=" + turmaidTurma + ", materiaIdmateria=" + materiaIdmateria + " ]";
    }
    
}
