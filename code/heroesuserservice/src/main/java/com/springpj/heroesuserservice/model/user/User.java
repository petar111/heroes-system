package com.springpj.heroesuserservice.model.user;

import com.springpj.heroesuserservice.model.authorization.Role;
import com.springpj.heroesuserservice.model.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50, message = "Maximum username size is 50.")
	@NotBlank(message = "Username must not be blank.")
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Column(name = "EMAIL", nullable = false, unique = true)
	@Email
	private String email;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Column(name = "BACKUP_EMAIL", nullable = true, unique = true)
	@Email
	private String backupEmail;

	@Column(name = "PASSWORD", nullable = false)
	@ValidPassword
	private String password;
	
	@Column(name = "ACCOUNT_STATUS", nullable = false)
	private AccountStatus accountStatus;
	
	@Column(name = "CREDENTIALS_EXPIRED", nullable = false)
	private boolean credentialsExpired;
	
	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date dateLastUpdated;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)
	@NotNull(message = "Role is required.")
	private Role role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBackupEmail() {
		return backupEmail;
	}
	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
