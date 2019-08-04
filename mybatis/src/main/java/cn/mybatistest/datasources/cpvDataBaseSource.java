package cn.mybatistest.datasources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

@Configuration
@MapperScan(
	basePackages = {"cn.mybatistest.dao"},//指向你本项目的dao
	sqlSessionFactoryRef="cpvSqlSessionFactory",//cpvSqlSessionFactory 是随便起的名字
	sqlSessionTemplateRef="cpvSqlSessionTemplate"//cpvSqlSessionTemplate 是随便起的名字
)
public class cpvDataBaseSource implements BaseDataSource {

	@Bean("cpvDataSource") //cpvDataSource随便起的名字
	@ConfigurationProperties(prefix="spring.datasource") //要和application.yml
	@Override
	public DataSource baseDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean("cpvSqlSessionFactory")
	@Override
	public SqlSessionFactory baseSqlSessionFactory(@Qualifier("cpvDataSource") DataSource baseDataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(baseDataSource);

		try {
			bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:cn/mybatistest/mapper/*Mapper.xml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = bean.getObject();
			//驼峰命名法
			bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

	@Bean("cpvDataSourceTransactionManager")
	@Override
	public DataSourceTransactionManager baseDataSourceTransactionManager(@Qualifier("cpvDataSource") DataSource baseDataSource) {
		return new DataSourceTransactionManager(baseDataSource);
	}

	@Bean("cpvSqlSessionTemplate")
	@Override
	public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("cpvSqlSessionFactory") SqlSessionFactory baseSqlSessionFactory) {
		return new SqlSessionTemplate(baseSqlSessionFactory);
	}

}
