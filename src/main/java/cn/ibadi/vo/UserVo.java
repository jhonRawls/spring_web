package cn.ibadi.vo;

import java.io.Serializable;

public class UserVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4290183358558248728L;
	private String name;
	private String pwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
