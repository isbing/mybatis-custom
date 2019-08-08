package com.isbing.myIml;

import com.isbing.config.XmlConfigParser;
import com.isbing.entity.Configuration;
import com.isbing.myInterface.SqlSessionFactory;
import com.isbing.utils.DocumentUtil;
import com.isbing.utils.ResourceUtil;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * 用来创建会话工厂的
 * Created by song bing
 * Created time 2019/8/8 12:11
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(InputStream inputStream) {
		Document document = DocumentUtil.createDocument(inputStream);
		XmlConfigParser xmlConfigParser = new XmlConfigParser();
		Configuration configuration = xmlConfigParser.parse(document.getRootElement());
		return new DefaultSqlSessionFactory(configuration);
	}

}
