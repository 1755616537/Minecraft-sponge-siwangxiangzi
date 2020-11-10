package cn.gonjubaike.siwangxiangzi.Util;

import cn.gonjubaike.siwangxiangzi.Siwangxiangzi;
import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import java.sql.*;
import java.util.Optional;

public class MYSQL {
    private SqlService sql;
    private String jdbcUrl;
    private String User;
    private String Password;

    /**
     * 初始化数据库连接
     */
    public void RunMysql() {
        //            读取存储节点信息
        ConfigurationNode mysql = Config.getConfigConfNode().getNode("storage", "MYSQL");
        String database = mysql.getNode("database").getString();
        String host = mysql.getNode("host").getString();
        String user = mysql.getNode("user").getString();
        String password = mysql.getNode("password").getString();
        String ssl = mysql.getNode("SSL").getString();

        try {
            myMethodThatQueries("jdbc:mysql://[root[:QD564qw]@]"+host + "/" + database
                    + "?&useUnicode=true&characterEncoding=UTF8&useSSL=" + ssl,
                    "SELECT * FROM "+database);
        } catch (Exception ignored) {
            new Siwangxiangzi().getLogger().info("[死亡箱子]插件,数据库连接失败");
        }
    }

    public javax.sql.DataSource getDataSource(String jdbcUrl) throws SQLException {
        if (sql == null) {
            Optional<SqlService> sqlService = Sponge.getServiceManager().provide(SqlService.class);
            sqlService.ifPresent(service -> sql = service);
        }
        return sql.getDataSource(jdbcUrl);
    }

    public void myMethodThatQueries(String uri, String sql) throws SQLException {
        jdbcUrl = uri;

        System.out.println(uri);

        Connection conn = getDataSource(uri).getConnection();
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        ResultSet results = stmt.executeQuery();
//        while (results.next()) {
//
//        }
    }

    public void myMethodThatQueries(String sql) throws SQLException {
        myMethodThatQueries(jdbcUrl, sql);
    }
}
