package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MYSQL {
    @Inject
    private Logger logger;

    private SqlService sql;
    private String jdbcUrl;

    /**
     * 初始化数据库连接
     */
    public void RunMysql() {
        //            读取存储节点信息
        ConfigurationNode mysql = Config.getConfNode().getNode("storage", "MYSQL");
        ConfigurationNode database = mysql.getNode("database");
        ConfigurationNode host = mysql.getNode("host");
        ConfigurationNode user = mysql.getNode("user");
        ConfigurationNode password = mysql.getNode("password");
        ConfigurationNode ssl = mysql.getNode("SSL");

        logger.info("{}{}{}{}{}",
                database.getString(),
                host.getString(),
                user.getString(),
                password.getString(),
                ssl.getBoolean()
        );

        try {
            myMethodThatQueries("jdbc:mysql://"
                    + host.getString() + "/" + database.getString()
                    + "?user=" + user.getString()
                    + "&password=" + password.getString()
                    + "&useUnicode=true&characterEncoding=UTF8&ssl=" + ssl.getBoolean(), "SELECT * FROM ");
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

    public void myMethodThatQueries(String uri, String sql) {
        jdbcUrl = uri;

        try (Connection conn = getDataSource(uri).getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet results = stmt.executeQuery()) {
            while (results.next()) {

            }
        } catch (Exception ignored) {

        }
    }

    public void myMethodThatQueries(String sql) {
        myMethodThatQueries(jdbcUrl, sql);
    }
}
