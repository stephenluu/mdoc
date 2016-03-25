package org.stephen.mdoc.doclet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyu.lu
 * @since  Feb 24, 2016
 */
public class POJO {

	private String name;
	
	private List<Row> fields = new ArrayList<Row>();

	public List<Row> getFields() {
		return fields;
	}

	public void setFields(List<Row> fields) {
		this.fields = fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
