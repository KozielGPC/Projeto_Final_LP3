/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gpcga
 */
@Entity
@Table(name = "turma")
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t")})
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTurma")
    private Integer idTurma;
    @Basic(optional = false)
    @Column(name = "nomeTurma")
    private String nomeTurma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turma")
    private List<TurmaHasMateria> turmaHasMateriaList;
    @JoinColumn(name = "professor_idProfessor", referencedColumnName = "idProfessor")
    @ManyToOne(optional = false)
    private Professor professor;

    public Turma() {
    }

    public Turma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Turma(Integer idTurma, String nomeTurma) {
        this.idTurma = idTurma;
        this.nomeTurma = nomeTurma;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public List<TurmaHasMateria> getTurmaHasMateriaList() {
        return turmaHasMateriaList;
    }

    public void setTurmaHasMateriaList(List<TurmaHasMateria> turmaHasMateriaList) {
        this.turmaHasMateriaList = turmaHasMateriaList;
    }

    public Professor getProfessoridProfessor() {
        return professor;
    }

    public void setProfessoridProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Turma[ idTurma=" + idTurma + " ]";
    }
    
}
