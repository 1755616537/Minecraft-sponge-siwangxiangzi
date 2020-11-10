package cn.gonjubaike.siwangxiangzi.Util;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;


public class Config {

    private static File ConfigFile;
    private static ConfigurationLoader<CommentedConfigurationNode> ConfigManager;
    private static CommentedConfigurationNode confNode;

    private static File ConfigCatalog;
    private static ConfigurationLoader<CommentedConfigurationNode> loader;
    private static CommentedConfigurationNode configConfNode;

    /**
     * 初始化配置
     */
    public void RunConfig() throws Exception {
        if (ConfigFile==null || ConfigManager==null || ConfigCatalog==null){
            throw new Exception("请先执行setup初始化值");
        }

//        ------------------------------------------------------------------------
        try {
            if (!ConfigFile.exists()) {
//                创建文件
                ConfigFile.createNewFile();
//                读入文件
                confNode = ConfigManager.load();
//                保存文件
                ConfigManager.save(confNode);
            }else {
//                读入文件
                confNode = ConfigManager.load();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        ------------------------------------------------------------------------
        //            创建配置目录
        if (!ConfigCatalog.exists()){
            ConfigCatalog.mkdir();
        }
        File config=new File(ConfigCatalog,"config.conf");
        loader = HoconConfigurationLoader.builder().setFile(config).build();
        try {
            if (!config.exists()) {
//                创建文件
                config.createNewFile();
//                读入文件
                configConfNode = loader.load();
//                添加数据
                configConfNode.getNode("storage","MYSQL","database").setValue("表名");
                configConfNode.getNode("storage","MYSQL","host").setValue("0.0.0.0:3306");
                configConfNode.getNode("storage","MYSQL","user").setValue("用户名");
                configConfNode.getNode("storage","MYSQL","password").setValue("密码");
                configConfNode.getNode("storage","MYSQL","SSL").setValue("false");
//                保存数据
                loader.save(configConfNode);
            }else {
//                读入文件
                configConfNode = loader.load();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化值
     */
    public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> configManager, File configCatalog) {
        ConfigFile = configFile;
        ConfigManager = configManager;
        ConfigCatalog = configCatalog;
    }

    public static CommentedConfigurationNode getConfigConfNode() {
        return configConfNode;
    }

    public static CommentedConfigurationNode getConfNode() {
        return confNode;
    }

    public static ConfigurationLoader<CommentedConfigurationNode> getConfigManager() {
        return ConfigManager;
    }

    public static ConfigurationLoader<CommentedConfigurationNode> getLoader() {
        return loader;
    }

    public static File getConfigCatalog() {
        return ConfigCatalog;
    }

    public static File getConfigFile() {
        return ConfigFile;
    }
}
