package org.stephen.mdoc.model;

/**
 * @author liuyu.lu
 * @since Feb 25, 2016
 */
public class User {
	/**
	 * 主键
	 */
	int id;
	/**
	 * 名字
	 */
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
