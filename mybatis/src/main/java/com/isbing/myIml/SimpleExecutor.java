package com.isbing.myIml;

import com.isbing.entity.*;
import com.isbing.myInterface.Executor;
import com.isbing.myInterface.SqlSession;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/8 14:06
 */
public class SimpleExecutor implements Executor {

	@Override
	public List<Object> query(Configuration configuration, MappedStatement mappedStatement, Object param) {
		DataSource dataSource = configuration.getDataSource();
		Connection connection = null;
		List<Object> results = new ArrayList();

		try {
			connection = dataSource.getConnection();
			SqlSource sqlSource = mappedStatement.getSqlSource();

			// 拿到原始的SQL语句 将其拆分为 BoundSql对象
			BoundSql boundSql = sqlSource.getBoundSql();

			// 拿到处理好的SQL  SqlBound:sql+list<parameters>
			PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
			// 填充占位符 交由handleParameter去处理
			handleParameter(preparedStatement, boundSql.getParameterMappingList(), param);

			// 开始执行
			ResultSet resultSet = preparedStatement.executeQuery();
			Class<?> resultTypeClass = mappedStatement.getResultType();
			// 处理结果映射
			handleReturnValue(results, resultSet, resultTypeClass);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return results;
	}

	private void handleReturnValue(List<Object> results, ResultSet resultSet, Class<?> resultTypeClass)
			throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		while (resultSet.next()) {
			Object result = resultTypeClass.newInstance();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; ++i) {
				String columnName = metaData.getColumnName(i);
				Field field = resultTypeClass.getDeclaredField(columnName);
				field.setAccessible(true);
				field.set(result, resultSet.getObject(columnName));
			}

			results.add(result);
		}
	}

	private void handleParameter(PreparedStatement preparedStatement, List<ParameterMapping> parameterMappingList,
			Object param)
			throws SQLException, NoSuchFieldException, IllegalAccessException {
		if (param instanceof Integer) {
			preparedStatement.setObject(1, param);
		} else if (param instanceof String) {
			preparedStatement.setObject(1, param);
		} else {
			Class<?> clazz = param.getClass();

			for (int i = 0; i < parameterMappingList.size(); ++i) {
				ParameterMapping parameterMapping = parameterMappingList.get(i);
				// 参数名
				String name = parameterMapping.getName();
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				Object value = field.get(param);
				preparedStatement.setObject(i + 1, value);
			}
		}
	}

}
