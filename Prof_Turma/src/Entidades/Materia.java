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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gpcga
 */
@Entity
@Table(name = "materia")
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmateria")
    private Integer idmateria;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "carga_horaria")
    private int cargaHoraria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
    private List<TurmaHasMateria> turmaHasMateriaList;

    public Materia() {
    }

    public Materia(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public Materia(Integer idmateria, String nome, int cargaHoraria) {
        this.idmateria = idmateria;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public List<TurmaHasMateria> getTurmaHasMateriaList() {
        return turmaHasMateriaList;
    }

    public void setTurmaHasMateriaList(List<TurmaHasMateria> turmaHasMateriaList) {
        this.turmaHasMateriaList = turmaHasMateriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmateria != null ? idmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idmateria == null && other.idmateria != null) || (this.idmateria != null && !this.idmateria.equals(other.idmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Materia[ idmateria=" + idmateria + " ]";
    }
    
}
