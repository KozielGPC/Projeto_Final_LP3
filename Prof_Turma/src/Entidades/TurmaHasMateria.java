/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gpcga
 */
@Entity
@Table(name = "turma_has_materia")
@NamedQueries({
    @NamedQuery(name = "TurmaHasMateria.findAll", query = "SELECT t FROM TurmaHasMateria t")})
public class TurmaHasMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurmaHasMateriaPK turmaHasMateriaPK;
    @Basic(optional = false)
    @Column(name = "dia_semana")
    private String diaSemana;
    @Basic(optional = false)
    @Column(name = "turno")
    private String turno;
    @Basic(optional = false)
    @Column(name = "horas_aula")
    private String horasAula;
    @JoinColumn(name = "materia_idmateria", referencedColumnName = "idmateria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materia materia;
    @JoinColumn(name = "turma_idTurma", referencedColumnName = "idTurma", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Turma turma;

    public TurmaHasMateria() {
    }

    public TurmaHasMateria(TurmaHasMateriaPK turmaHasMateriaPK) {
        this.turmaHasMateriaPK = turmaHasMateriaPK;
    }

    public TurmaHasMateria(TurmaHasMateriaPK turmaHasMateriaPK, String diaSemana, String turno, String horasAula) {
        this.turmaHasMateriaPK = turmaHasMateriaPK;
        this.diaSemana = diaSemana;
        this.turno = turno;
        this.horasAula = horasAula;
    }

    public TurmaHasMateria(int turmaidTurma, int materiaIdmateria) {
        this.turmaHasMateriaPK = new TurmaHasMateriaPK(turmaidTurma, materiaIdmateria);
    }

    public TurmaHasMateriaPK getTurmaHasMateriaPK() {
        return turmaHasMateriaPK;
    }

    public void setTurmaHasMateriaPK(TurmaHasMateriaPK turmaHasMateriaPK) {
        this.turmaHasMateriaPK = turmaHasMateriaPK;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHorasAula() {
        return horasAula;
    }

    public void setHorasAula(String horasAula) {
        this.horasAula = horasAula;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turmaHasMateriaPK != null ? turmaHasMateriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurmaHasMateria)) {
            return false;
        }
        TurmaHasMateria other = (TurmaHasMateria) object;
        if ((this.turmaHasMateriaPK == null && other.turmaHasMateriaPK != null) || (this.turmaHasMateriaPK != null && !this.turmaHasMateriaPK.equals(other.turmaHasMateriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TurmaHasMateria[ turmaHasMateriaPK=" + turmaHasMateriaPK + " ]";
    }
    
}
