package org.nimphus.sql.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserContact {

	// Properties
	// =============================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Address_1;
	private String Address_2;
	private int TelNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference
    private User user;

	// Constructor
	// =============================
	public UserContact() {
		super();
	}

	// Getters and Setter
	// =============================
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress_1() {
		return Address_1;
	}

	public void setAddress_1(String address_1) {
		Address_1 = address_1;
	}

	public String getAddress_2() {
		return Address_2;
	}

	public void setAddress_2(String address_2) {
		Address_2 = address_2;
	}

	public int getTelNo() {
		return TelNo;
	}

	public void setTelNo(int telNo) {
		TelNo = telNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// ToString
	// =============================
	@Override
	public String toString() {
		return "UserContact [id=" + id + ", Address_1=" + Address_1 + ", Address_2=" + Address_2 + ", TelNo=" + TelNo
				+ ", user=" + user + "]";
	}

}
