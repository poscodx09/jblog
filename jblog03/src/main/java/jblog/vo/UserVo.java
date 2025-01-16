package jblog.vo;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserVo {
	@NotEmpty
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	@Size(min=4, max=8)
	private String password;
	@AssertTrue(message = "이용 약관에 동의해야 합니다.")
	private boolean agreeProv;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAgreeProv() {
		return agreeProv;
	}
	public void setAgreeProv(boolean agreeProv) {
		this.agreeProv = agreeProv;
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
