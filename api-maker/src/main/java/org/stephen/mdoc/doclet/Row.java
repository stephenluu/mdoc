package org.stephen.mdoc.doclet;

/**
 * @author liuyu.lu
 * @since  Feb 24, 2016
 */
public class Row {

	private String field;
	private String type;
	private String des;

	public Row() {
		super();
	}

	public Row(String field, String type, String des) {
		super();
		this.field = field;
		this.type = type;
		this.des = des;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

}
