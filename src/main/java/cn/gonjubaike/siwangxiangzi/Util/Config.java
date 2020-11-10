package cn.gonjubaike.siwangxiangzi.Util;

import cn.gonjubaike.siwangxiangzi.Siwangxiangzi;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class Config {

    private static File ConfigFile;
    private static ConfigurationLoader<CommentedConfigurationNode> ConfigManager;
    private static File ConfigCatalog;

    private static ConfigurationLoader<CommentedConfigurationNode> loader;
    private static CommentedConfigurationNode confNode;

    private static ConfigurationNode rootNode = null;

    /**
     * 初始化配置
     */
    public void RunConfig() {
        loader = HoconConfigurationLoader.builder()
                .setFile(ConfigFile)
                .build();

        System.out.println("------------------------------成功");

        CommentedConfigurationNode root;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.err.println("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            System.exit(1);
            return;
        }

        ConfigurationNode countNode = root.getNode("messages", "count"),
                moodNode = root.getNode("messages", "mood");

        String name = root.getNode("name").getString();
        int count = countNode.getInt(Integer.MIN_VALUE);

        if (name == null || count == Integer.MIN_VALUE) {
            System.err.println("Invalid configuration");
            System.exit(2);
            return;
        }

        System.out.println("Hello, " + name + "!");
        System.out.println("Thanks for viewing your messages");

        // Update values
        countNode.setValue(0);

        root.getNode("accesses").act(n -> { // perform actions with the node at key "accesses" available at `n`
            n.appendListNode().setValue(System.currentTimeMillis());
        });


//        try{
//            //        获取配置加载器
//            loader = HoconConfigurationLoader.builder().setPath(siwangxiangzi.getGetConfigFile()).build();
//        }catch (Exception ignored){
////            初始化默认配置文件
//            try {
//                new Siwangxiangzi().getGetConfigFile().toFile().createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////        获取一个空的 ConfigurationNode
//        ConfigurationNode rootNode2 = loader.createEmptyNode(ConfigurationOptions.defaults());
//        try {
////            加载节点信息
//            rootNode = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//            new Siwangxiangzi().getLogger().info("[死亡箱子]初始化配置失败！");
//            try {
//                loader.save(rootNode);
//            } catch(IOException e2) {
//                // handle error
//            }
//        }
    }

    /**
     * 初始化值
     */
    public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> configManager, File configCatalog) {
        ConfigFile = configFile;
        ConfigManager = configManager;
        ConfigCatalog = configCatalog;
    }

    /**
     * 初始化文件
     */
    public void load() {
        try {
//            创建配置文件
            if (!ConfigFile.exists()) {
                ConfigFile.createNewFile();
                confNode = ConfigManager.load();
                ConfigManager.save(confNode);
            }
            confNode = ConfigManager.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        添加数据
        addValue();

        //            创建配置目录
        if (!ConfigCatalog.exists()){
            ConfigCatalog.mkdir();
        }
        File config=new File(ConfigCatalog,"config.conf");
        loader = HoconConfigurationLoader.builder().setFile(config).build();
        CommentedConfigurationNode configConfNode;
        try {
//            创建配置文件
            if (!config.exists()) {
                config.createNewFile();
                configConfNode = loader.load();
                loader.save(configConfNode);
            }
            configConfNode = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加值
     */
    public void addValue(){
        confNode.getNode("storage","MYSQL","database").setValue("3306");
        try {
            ConfigManager.save(confNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationLoader<CommentedConfigurationNode> GetLoader() {
        return loader;
    }

    public static ConfigurationNode GetRootNode() {
        return rootNode;
    }
}
