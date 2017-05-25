package edu.mum.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id = null;

	@Version
	private int version = 0;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "RANK", nullable = true)
	private int ranking = 0;
	
	@Column(name = "PHONE", nullable = true)
	private String phone;

	@Column(name = "IS_ADMIN", nullable = true)
	private boolean admin = false;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	@JsonBackReference
	private UserCredentials userCredentials;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private Set<Address> addresses = new HashSet<Address>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE}, mappedBy = "rideOwner")
	@JsonIgnore
	private Set<Ride> createdRides = new HashSet<Ride>();
	
	public Set<Ride> getCreatedRides() {
		return createdRides;
	}

	public void setCreatedRides(Set<Ride> createdRides) {
		this.createdRides = createdRides;
	}
	
	public void addCreatedRide(Ride ride) {
		createdRides.add(ride);
	}

	public List<Ride> getReservedRides() {
		return reservedRides;
	}

	public void setReservedRides(List<Ride> reservedRides) {
		this.reservedRides = reservedRides;
	}

	@ManyToMany(mappedBy = "usersThatReservedRide", fetch=FetchType.EAGER) //, cascade= {CascadeType.PERSIST,CascadeType.MERGE })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ride> reservedRides = new ArrayList<Ride>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
}