package org.stephen.mdoc.doclet;

import java.util.List;


public class Api {

	private String apiName;
	private String methodSign;
	private String methodComment;
	private List<POJO> args;
	private POJO returnType;

	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getMethodSign() {
		return methodSign;
	}
	public void setMethodSign(String methodSign) {
		this.methodSign = methodSign;
	}
	public Api() {
		super();
	}
	public Api(String apiName, String methodSign) {
		super();
		this.apiName = apiName;
		this.methodSign = methodSign;
	}
	public String getMethodComment() {
		return methodComment;
	}
	public void setMethodComment(String methodComment) {
		this.methodComment = methodComment;
	}
	public List<POJO> getArgs() {
		return args;
	}
	public void setArgs(List<POJO> args) {
		this.args = args;
	}
	public POJO getReturnType() {
		return returnType;
	}
	public void setReturnType(POJO returnType) {
		this.returnType = returnType;
	}

}
