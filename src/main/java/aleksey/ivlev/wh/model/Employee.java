package aleksey.ivlev.wh.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
    @Column(name="ID")
	@SequenceGenerator(name="empIdSeq", sequenceName="SEQ_EMPLOYEE", allocationSize=10, initialValue=1)
    @GeneratedValue(generator="empIdSeq")
    private Integer id;
 
    @Column(name="FIRSTNAME")
    private String firstname;
 
    @Column(name="LASTNAME")
    private String lastname;
 
    @Column(name="EMAIL")
    private String email;
 
    @Column(name="TELEPHONE")
    private String telephone;
    
    @Column(name="CREATED")
    private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
    
    

}
