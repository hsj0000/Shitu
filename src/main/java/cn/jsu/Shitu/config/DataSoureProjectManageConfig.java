package cn.jsu.projectmanage.config;


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

import javax.sql.DataSource;

@Configuration  //表示这是一个配置类
//进行Mapper扫描
@MapperScan(basePackages = {"cn.jsu.projectmanage.dao.projectManage"},
        sqlSessionFactoryRef = "projectManageDbSqlSessionFactory")
public class DataSoureProjectManageConfig {
    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name = "projectManageDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.projectmanage")
    public DataSource projectManageDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置SQLSessionFactory
     * 当我们注入的是相同类型的接口的bean时
     * 可以通过Qualifier注解来区分具体是哪一个bean进行注入
     * <bean id=  calsss></bean>
     * @param projectManageDbDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "projectManageDbSqlSessionFactory")
    public SqlSessionFactory projectManageDbSqlSessionFactory(@Qualifier("projectManageDbDataSource") DataSource projectManageDbDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(projectManageDbDataSource);
        //设置mybatis配置，这里设置了驼峰命名配置
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(config);
        //设置Mapper.xml所在的位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/projectManage/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 配置SqlSessionTemplate
     *
     * @param projectManagedbSqlSessionFactory
     * @return
     */
    @Bean(name = "projectManagesDbSqlSessionTemplate")
    public SqlSessionTemplate projectManageDbSqlSessionTemplate(@Qualifier("projectManageDbSqlSessionFactory") SqlSessionFactory projectManagedbSqlSessionFactory) {
        return new SqlSessionTemplate(projectManagedbSqlSessionFactory);
    }

}
