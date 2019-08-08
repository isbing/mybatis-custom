package com.isbing;

import com.isbing.config.XmlConfigParser;
import com.isbing.dao.UserDao;
import com.isbing.dao.UserDaoImpl;
import com.isbing.entity.Configuration;
import com.isbing.entity.User;
import com.isbing.myIml.SqlSessionFactoryBuilder;
import com.isbing.myInterface.SqlSession;
import com.isbing.myInterface.SqlSessionFactory;
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

	/**
	 * 尝试写执行流程
	 * 首先得得到一个会话吧(SqlSession)。得用SqlSessionFactory工厂创建(
	 * 工厂创建的时候，如果传参是这样，怎么样，如果传参是那样怎么样。。构建者模式去创建默认的工厂)
	 * SqlSession（执行方法用的） -- DefaultSqlSession
	 * SqlSessionFactory -- DefaultSqlSessionFactory
	 * SqlSessionFactoryBuilder.build 得到 SqlSessionFactory
	 *
	 */
	@Test
	public void tt() {
		String resource = "SqlMapConfig.xml";
		// 加载文件到输入流中
		InputStream inputStream = ResourceUtil.loadXmlToInputStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 执行查询 是用dao查询的。所以dao就需要拿到sqlSession
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		User param = new User();
		param.setId(1L);
		User user = userDao.queryUserById(param);
		System.out.println(user);

	}
}
