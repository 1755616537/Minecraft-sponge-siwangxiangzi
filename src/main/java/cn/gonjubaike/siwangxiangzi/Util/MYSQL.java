package cn.gonjubaike.siwangxiangzi.Util;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import java.sql.Connection;
import java.sql.SQLException;

public class MYSQL {
//    172.18.0.2
//    root
//    QD564qw
    private SqlService sql;

    /**
     * 初始化数据库连接
     */
    public void RunMysql(){

    }

    public javax.sql.DataSource getDataSource(String jdbcUrl) throws SQLException {
        if (sql == null) {
            sql = Sponge.getServiceManager().provide(SqlService.class).get();
        }
        return sql.getDataSource(jdbcUrl);
    }

    // Later on
    public void myMethodThatQueries() throws SQLException {
        Connection conn = getDataSource("jdbc:h2:imalittledatabaseshortandstout.db").getConnection();
        try {
            conn.prepareStatement("SELECT * FROM test_tbl").execute();
        } finally {
            conn.close();
        }

    }
}
