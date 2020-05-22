package JDBC.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionManger {
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        //得到Mybatis配置文件
        String recource = "mybatis/SqlMapConfig.xml";
        //得到配置文件的文件流
        InputStream inputStream = Resources.getResourceAsStream(recource);
        //创建会话工厂 传入mybatis的配置文件信息
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
