package org.stephen.mdoc;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarker {
	
	public static void make(Map<String,Object> root) throws IOException, TemplateException {

		Configuration cfg = new Configuration();

		// cfg.setDirectoryForTemplateLoading(FileUtil.getFileFromClasspath("template"));
		cfg.setDirectoryForTemplateLoading(new File("/Users/stephen/git/github/mdoc/api-maker/src/main/resources/template"));

		cfg.setDefaultEncoding("UTF-8");

		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Template temp = cfg.getTemplate("template.ftl");

		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
	}

}
