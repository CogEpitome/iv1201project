/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthknugarna.iv1201project.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Anton
 */
@Entity
@Table(name = "APPLICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")
    , @NamedQuery(name = "Application.findByApplicationId", query = "SELECT a FROM Application a WHERE a.applicationId = :applicationId")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    @Column(name = "APPLICATION_ID")
    private Long applicationId;
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person personId;
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusId;

    public Application() {
    }

    public Application(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kthknugarna.iv1201project.model.Application[ applicationId=" + applicationId + " ]";
    }
    
}
