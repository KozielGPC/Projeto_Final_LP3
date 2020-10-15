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
@Table(name = "professor")
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProfessor")
    private Integer idProfessor;
    @Basic(optional = false)
    @Column(name = "nomeProfessor")
    private String nomeProfessor;
    @Basic(optional = false)
    @Column(name = "emailProfessor")
    private String emailProfessor;
    @Basic(optional = false)
    @Column(name = "nascimentoProfessor")
    private String nascimentoProfessor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Turma> turmaList;

    public Professor() {
    }

    public Professor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Professor(Integer idProfessor, String nomeProfessor, String emailProfessor, String nascimentoProfessor) {
        this.idProfessor = idProfessor;
        this.nomeProfessor = nomeProfessor;
        this.emailProfessor = emailProfessor;
        this.nascimentoProfessor = nascimentoProfessor;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    public String getNascimentoProfessor() {
        return nascimentoProfessor;
    }

    public void setNascimentoProfessor(String nascimentoProfessor) {
        this.nascimentoProfessor = nascimentoProfessor;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Professor[ idProfessor=" + idProfessor + " ]";
    }
    
}
