package org.stephen.mdoc.doclet;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stephen.mdoc.FreeMarker;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.RootDoc;

import freemarker.template.TemplateException;

public class MDoclet {
	/**
	 * hello
	 * 
	 * @param root
	 * @return
	 * @throws ClassNotFoundException
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static boolean start(RootDoc root) throws ClassNotFoundException, IOException, TemplateException {
        ClassDoc[] classes = root.classes();
        System.out.println("检索到类数量：" + classes.length);
        Map<String, Object> makerRoot = new HashMap<>();
        List<POJO> pojos = new ArrayList<POJO>();
        
        for (int i = 0; i < classes.length; ++i) {
			ClassDoc classDoc = classes[i];
			System.out.println("class name : " + classDoc.toString());
			Class clazz = Class.forName(classDoc.toString());
			if (!clazz.isInterface() && !clazz.isEnum()) {
				// POJO  
				Map<String,String> fieldCommentMap = new HashMap<String, String>();
				for (FieldDoc fieldDoc : classDoc.fields(false)) {
					fieldCommentMap.put(fieldDoc.name(), fieldDoc.getRawCommentText().trim());
				}
				
				POJO pojo = new POJO();
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					String des = fieldCommentMap.get(field.getName());
					pojo.getFields().add(new Row(field.getName(), field.getType().getSimpleName(), des == null ? "" : des));
				}
				pojo.setName(clazz.getSimpleName());
				pojos.add(pojo);
			}
			
			Method[] declaredMethods = clazz.getDeclaredMethods();
			Map<String, Method> declaredMethodMap = new HashMap<>();
			for (Method method : declaredMethods) {
				declaredMethodMap.put(method.getName(), method);
			}

			MethodDoc[] docMethods = classDoc.methods();
			List<Api> apiList = new ArrayList<Api>();
			for (int j = 0; j < docMethods.length; j++) {
				MethodDoc methodDoc = docMethods[j];

				Map<String, String> paramCommentMap = new HashMap<>();
				ParamTag[] paramTags = methodDoc.paramTags();
				for (ParamTag paramTag : paramTags) {
					// if(paramTag.isTypeParameter()){
						System.out.println("comment: " + paramTag.parameterComment());
						System.out.println("paramName: " + paramTag.parameterName());
						paramCommentMap.put(paramTag.parameterName(), paramTag.parameterComment());
					// }
				}

				Method declaredMethod = declaredMethodMap.get(methodDoc.name());
				System.out.println(declaredMethod.getName() + "  comment is : " + methodDoc.commentText());
				Api api = new Api(declaredMethod.getName(), getMethodSign(declaredMethod));

				// 参数
				List<POJO> args = new ArrayList<POJO>();
				Parameter[] params = declaredMethod.getParameters();
				for (Parameter parameter : params) {
					POJO param = new POJO();
					String des = paramCommentMap.get(parameter.getName());
					param.getFields().add(new Row(parameter.getName(), parameter.getType().getSimpleName(), des == null ? "" : des));
					args.add(param);
				}
				api.setArgs(args);
				api.setMethodComment(methodDoc.commentText());

				// 返回值
				POJO returnType = new POJO();
				List<Row> rows = new ArrayList<Row>();
				Class<?> type = declaredMethod.getReturnType();
				Field[] fields = type.getDeclaredFields();
				for (Field field : fields) {
					rows.add(new Row(field.getName(), field.getType().getSimpleName(), ""));
				}
				returnType.setFields(rows);
				api.setReturnType(returnType);

				apiList.add(api);
			}
			
			makerRoot.put("apis", apiList);
        }
        
        makerRoot.put("pojos", pojos);
        FreeMarker.make(makerRoot);
        
        return true;
    }

	private static String getMethodSign(Method method) {
		return method.getReturnType().getSimpleName() + " " + method.getName() + "(" + beautifyParam(method.getParameters()) + ")";
	}

	private static String beautifyParam(Parameter[] params) {
		List<Parameter> paramList = Arrays.asList(params);
		return paramList.stream().map(param -> wrapParam(param)).collect(joining(", "));
	}

	private static String wrapParam(Parameter first) {
		StringBuffer paramSb = new StringBuffer();
		paramSb.append(first.getType().getSimpleName());
		paramSb.append(" ");
		paramSb.append(first.getName());
		return paramSb.toString();
	}
}