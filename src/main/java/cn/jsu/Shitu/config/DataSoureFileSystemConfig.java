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
@MapperScan(basePackages = {"cn.jsu.projectmanage.dao.fileSystem"},
        sqlSessionFactoryRef = "fileSystemDbSqlSessionFactory")
public class DataSoureFileSystemConfig {
    /**
     * 配置一个数据源的bean
     *
     * @return
     */
    @Bean(name = "fileSystemDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.filesystem")
    public DataSource fileSystemDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置SQLSessionFactory
     * 当我们注入的是相同类型的接口的bean时
     * 可以通过Qualifier注解来区分具体是哪一个bean进行注入
     * <bean id=  calsss></bean>
     * @param fileSystemDbDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "fileSystemDbSqlSessionFactory")
    public SqlSessionFactory fileSystemDbSqlSessionFactory(@Qualifier("fileSystemDbDataSource") DataSource fileSystemDbDataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(fileSystemDbDataSource);
        //设置mybatis配置，这里设置了驼峰命名配置
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(config);
        //设置Mapper.xml所在的位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/fileSystem/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 配置SqlSessionTemplate
     *
     * @param fileSystemDbSqlSessionFactory
     * @return
     */
    @Bean(name = "fileSystemSqlSessionTemplate")
    public SqlSessionTemplate fileSystemDbSqlSessionTemplate(@Qualifier("fileSystemDbSqlSessionFactory") SqlSessionFactory fileSystemDbSqlSessionFactory) {
        return new SqlSessionTemplate(fileSystemDbSqlSessionFactory);
    }

}
