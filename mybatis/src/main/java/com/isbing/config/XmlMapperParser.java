package com.isbing.config;

import com.isbing.entity.Configuration;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/7 17:26
 */
public class XmlMapperParser {

	private Configuration configuration;

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
		String namespace = rootElement.attributeValue("namespace");
		List<Element> selectList = rootElement.elements("select");
		for (Element element : selectList) {
			// 一个select语句 就是一个statement
			parseStatement(element);
		}
	}

	private void parseStatement(Element element) {

	}
}
