package com.isbing;

import com.isbing.config.XmlConfigParser;
import com.isbing.entity.Configuration;
import com.isbing.utils.DocumentUtil;
import com.isbing.utils.ResourceUtil;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by song bing
 * Created time 2019/8/7 14:54
 */
public class XmlParserTest {

	/**
	 * 首先解析配置文件
	 * 全局配置文件 可以获取数据源信息，还可以获取映射文件信息
	 */
	@Test
	public void testInitConfiguration() throws Exception {
		String resource = "SqlMapConfig.xml";
		// 加载文件到输入流中
		InputStream inputStream = ResourceUtil.loadXmlToInputStream(resource);
		// 将这个xml输入流弄成文档
		Document document = DocumentUtil.createDocument(inputStream);
		// 使用面向对象的思想，而不是使用面向方法
		XmlConfigParser xmlConfigParser = new XmlConfigParser();
		// 解析根节点。要返回一个Configuration对象
		Configuration configuration = xmlConfigParser.parse(document.getRootElement());
		System.out.println(configuration.getDataSource().getConnection());
		System.out.println(configuration.getMappedStatementMap());
	}
}
