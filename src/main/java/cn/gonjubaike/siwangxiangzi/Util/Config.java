package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;

import java.io.IOException;
import java.nio.file.Path;

public class Config {
    /**
     * 拿到分配好的路径
     */
    @Inject
    @DefaultConfig(sharedRoot = false)
    public static Path privateConfigDir;

    /**
     * 初始化配置
     */
    public void RunConfig(){
        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setPath(Config.privateConfigDir).build();
//        ConfigurationNode rootNode = loader.createEmptyNode(ConfigurationOptions.defaults());
        ConfigurationNode rootNode;
        try {
//            加载节点信息
            rootNode = loader.load();
//            读取存储节点信息
            ConfigurationNode mysql = rootNode.getNode("storage", "MYSQL");
            ConfigurationNode database = mysql.getNode("storage", "MYSQL", "database");
            ConfigurationNode host = mysql.getNode("storage", "MYSQL", "host");
            ConfigurationNode user = mysql.getNode("storage", "MYSQL", "user");
            ConfigurationNode password = mysql.getNode("storage", "MYSQL", "password");
            ConfigurationNode ssl = mysql.getNode("storage", "MYSQL", "SSL");

            logger.info("[死亡箱子]",database.getString(),host.toString(),user.toString(),password.toString(),ssl.toString());
        } catch(IOException e) {
            // error
            logger.info("[死亡箱子]配置文件读取失败！");
        }
    }
}
