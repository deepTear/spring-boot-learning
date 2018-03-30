package com.deep.base;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * ������Դ����
 * @author Administrator
 *
 */

@Configuration
@MapperScan(basePackages = {"com.deep.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig implements EnvironmentAware{
	
	private static Log logger = LogFactory.getLog(DataSourceConfig.class);
	

    private Environment env;
	
	@Override
	public void setEnvironment(Environment environment) {
		 this.env = environment;
	}

	
	@Primary
    @Bean(name = "datasource1")
	@Qualifier("datasource1")
    @ConfigurationProperties(prefix="spring.datasource1")
    public DataSource datasource1() {
		
		
		/*HikariConfig config = new HikariConfig();
        //config.setDataSourceClassName("datasource1");
        config.addDataSourceProperty("url", env.getProperty("spring.datasource1.url"));
        config.setUsername(env.getProperty("spring.datasource1.username"));
        config.setPassword(env.getProperty("spring.datasource1.password"));
        config.setConnectionTestQuery(env.getProperty("spring.datasource1.connectionTestQuery"));
        config.setDriverClassName(env.getProperty("spring.datasource1.driverClassName"));
        logger.info("Configruing primaryDataSource end...");
        return new HikariDataSource(config);*/
        return DataSourceBuilder.create().build();
    }
    
    
    @Bean(name = "datasource2")
    @Qualifier("datasource2")
    @ConfigurationProperties(prefix="spring.datasource2")
    public DataSource datasource2() {
    	
    	/*HikariConfig config = new HikariConfig();
        //config.setDataSourceClassName("datasource2");
        config.addDataSourceProperty("url", env.getProperty("spring.datasource2.url"));
        config.setUsername(env.getProperty("spring.datasource2.username"));
        config.setPassword(env.getProperty("spring.datasource2.password"));
        config.setConnectionTestQuery(env.getProperty("spring.datasource2.connectionTestQuery"));
        config.setDriverClassName(env.getProperty("spring.datasource2.driverClassName"));
        logger.info("Configruing primaryDataSource end...");
        return new HikariDataSource(config);*/
        return DataSourceBuilder.create().build();
    }
    
    /**
     * ��̬����Դ: ͨ��AOP�ڲ�ͬ����Դ֮�䶯̬�л�
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource(@Qualifier("datasource1")DataSource datasource1,@Qualifier("datasource2")DataSource datasource2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // Ĭ������Դ
        dynamicDataSource.setDefaultTargetDataSource(datasource1);
        // ���ö�����Դ
        Map<Object, Object> dsMap = new HashMap<Object,Object>(5);
        dsMap.put("datasource1", datasource1);
        dsMap.put("datasource2", datasource2);

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
    
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
        return factoryBean.getObject();

    }
    
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // ʹ���������õ�Factory
        return template;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    
}
