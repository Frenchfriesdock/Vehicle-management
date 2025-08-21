package com.hosiky.sellers.utils;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.hosiky.sellers.demo.VaccineRecordMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class JdbcUtil {
    private static final String URL  = "jdbc:mysql://localhost:3306/vehicle_system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PWD  = "1234";

    private static final HikariDataSource DS;
    static {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(URL);
        cfg.setUsername(USER);
        cfg.setPassword(PWD);
        cfg.setMaximumPoolSize(8);
        DS = new HikariDataSource(cfg);
    }

    public static SqlSessionFactory sessionFactory() {
        MybatisConfiguration cfg = new MybatisConfiguration();
        cfg.addMapper(VaccineRecordMapper.class);
        Environment env = new Environment.Builder("dev")
                .dataSource(DS)
                .transactionFactory(new JdbcTransactionFactory())
                .build();
        cfg.setEnvironment(env);
        return new SqlSessionFactoryBuilder().build(cfg);
    }
}