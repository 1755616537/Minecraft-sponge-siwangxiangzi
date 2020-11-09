package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;
import javafx.animation.RotateTransition;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
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

    private ConfigurationLoader<CommentedConfigurationNode> loader;
    public static ConfigurationNode rootNode;

    /**
     * 初始化配置
     */
    public void RunConfig() {
        loader = HoconConfigurationLoader.builder().setPath(Config.privateConfigDir).build();
        try {
//            加载节点信息
            rootNode = loader.load();
        } catch (IOException e) {
            Logger.getLogger().info("[死亡箱子]初始化配置失败！");
        }
    }

    /**
     * 获取配置
     */
    public void GetConfig(){

    }

    public void SetConfig() {
        try {
//                获取一个空的 ConfigurationNode
            ConfigurationNode rootNode = loader.createEmptyNode(ConfigurationOptions.defaults());
            loader.save(rootNode);
        } catch (IOException e2) {
            Logger.getLogger().info("[死亡箱子]保存配置文件失败！");
        }
    }
}
