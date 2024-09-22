package com.springpj.heroesuserservice.model.dto;


import com.springpj.heroesuserservice.model.user.AccountStatus;
import com.springpj.heroesuserservice.model.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserDto {
	
	private Long id;
	
	@Size(max = 50, message = "Maximum username size is 50.")
	@NotNull
	private String username;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Email
	@NotNull
	private String email;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Email
	private String backupEmail;

	@ValidPassword
	private String password;
	private AccountStatus accountStatus;
	private boolean credentialsExpired;
	
	private Date dateCreated;
	private Date dateLastUpdated;
	
	private Long roleId;
	
	
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
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", backupEmail=" + backupEmail
				+ ", password=" + password + ", accountStatus=" + accountStatus + ", credentialsExpired="
				+ credentialsExpired + ", dateCreated=" + dateCreated + ", dateLastUpdated=" + dateLastUpdated
				+ ", roleId=" + roleId + "]";
	}
	
	
	

}
