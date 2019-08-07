package com.isbing.config;

import com.isbing.entity.Configuration;
import com.isbing.utils.DocumentUtil;
import com.isbing.utils.ResourceUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by song bing
 * Created time 2019/8/7 16:20
 */
public class XmlConfigParser {

	private Configuration configuration = new Configuration();

	public Configuration parse(Element rootElement) {
		// 解析全局配置文件
		parseEnvironments(rootElement.element("environments"));
		// 解析映射文件
		parseMappers(rootElement.element("mappers"));
		return configuration;
	}

	private void parseMappers(Element mappers) {
		List<Element> elements = mappers.elements("mapper");
		for (Element element : elements) {
			parseMapper(element);
		}
	}

	private void parseMapper(Element element) {
		String resource = element.attributeValue("resource");
		InputStream inputStream = ResourceUtil.loadXmlToInputStream(resource);
		Document document = DocumentUtil.createDocument(inputStream);
		XmlMapperParser xmlMapperParser = new XmlMapperParser(configuration);
		xmlMapperParser.parse(document.getRootElement());
	}

	private void parseEnvironments(Element environments) {
		String defaultEnv = environments.attributeValue("default");
		// 得到一个默认值
		if (defaultEnv != null && !defaultEnv.equals("")) {
			System.out.println(defaultEnv);
			// 获取environments标签下的所有孩子节点
			List<Element> environmentList = environments.elements("environment");
			for (Element element : environmentList) {
				String id = element.attributeValue("id");
				if (defaultEnv.equals(id)) {
					parseEnvironment(element);
				}
			}
		}
	}

	private void parseEnvironment(Element element) {
		// 获取数据源信息
		Element dataSource = element.element("dataSource");
		String type = dataSource.attributeValue("type");
		Properties properties = new Properties();
		List<Element> propertyList = dataSource.elements("property");
		// 封装数据库的连接信息
		for (Element property : propertyList) {
			properties.put(property.attributeValue("name"), property.attributeValue("value"));
		}
		if (type.equals("DBCP")) {
			BasicDataSource basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(properties.getProperty("driver"));
			basicDataSource.setUrl(properties.getProperty("url"));
			basicDataSource.setUsername(properties.getProperty("username"));
			basicDataSource.setPassword(properties.getProperty("password"));
			configuration.setDataSource(basicDataSource);
		}
	}
}
