package com.isbing.config;

import com.isbing.entity.Configuration;
import com.isbing.entity.MappedStatement;
import com.isbing.entity.SqlSource;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by song bing
 * Created time 2019/8/7 17:26
 */
public class XmlMapperParser {

	private Configuration configuration;
	private String namespace;

	public XmlMapperParser(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public XmlMapperParser() {
	}

	public void parse(Element rootElement) {
		namespace = rootElement.attributeValue("namespace");
		List<Element> selectList = rootElement.elements("select");
		for (Element element : selectList) {
			// 一个select语句 就是一个statement
			parseStatement(element);
		}
	}

	private void parseStatement(Element element) {
		String id = element.attributeValue("id");
		String parameterType = element.attributeValue("parameterType");
		String resultType = element.attributeValue("resultType");
		String statementType = element.attributeValue("statementType");
		String textTrim = element.getTextTrim();
		// 将参数类型 以及结果类型 搞为 class对象
		Class<?> parameterTypeClazz = null;
		Class<?> resultTypeClazz = null;
		try {
			parameterTypeClazz = Class.forName(parameterType);
			resultTypeClazz = Class.forName(resultType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String statementId = namespace + "." + id;
		SqlSource sqlSource = new SqlSource(textTrim);
		MappedStatement mappedStatement = new MappedStatement(statementId, parameterTypeClazz, resultTypeClazz,
				statementType, sqlSource);
		configuration.addMappedStatement(statementId, mappedStatement);
	}
}
