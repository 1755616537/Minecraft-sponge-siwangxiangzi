package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import java.sql.*;
import java.util.Optional;

public class MYSQL {
    @Inject
    private Logger logger;

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

        System.out.println(database);
        System.out.println(host);
        System.out.println(user);
        System.out.println(password);
        System.out.println(ssl);

        try {
            myMethodThatQueries("jdbc:mysql://"
                    + host + "/" + database
//                    + "?user=" + user
//                    + "&password=" + password
                    + "?useUnicode=true&characterEncoding=UTF8&useSSL=" + ssl,user,password,
                    "SELECT * FROM ");
        } catch (Exception ignored) {
            logger.info("");
        }
    }

    public javax.sql.DataSource getDataSource(String jdbcUrl) throws SQLException {
        if (sql == null) {
            Optional<SqlService> sqlService = Sponge.getServiceManager().provide(SqlService.class);
            sqlService.ifPresent(service -> sql = service);
        }
        return sql.getDataSource(jdbcUrl);
    }

    public void myMethodThatQueries(String uri,String user,String password, String sql) {
        jdbcUrl = uri;
        User=user;
        Password=password;

        try (Connection conn = DriverManager.getConnection(uri,user,password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet results = stmt.executeQuery()) {
            while (results.next()) {

            }
        } catch (Exception ignored) {

        }
    }

    public void myMethodThatQueries(String sql) {
        myMethodThatQueries(jdbcUrl,User,Password, sql);
    }
}
