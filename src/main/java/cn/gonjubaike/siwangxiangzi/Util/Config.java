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
    private Path privateConfigDir;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private ConfigurationNode rootNode;

    /**
     * 初始化配置
     */
    public void RunConfig() {
        loader = HoconConfigurationLoader.builder().setPath(privateConfigDir).build();
        try {
//            加载节点信息
            rootNode = loader.load();
        } catch (IOException e) {
            Logger.GetLogger().info("[死亡箱子]初始化配置失败！");
        }
    }

    public Path GetPrivateConfigDir() {
        return this.privateConfigDir;
    }

    public ConfigurationLoader<CommentedConfigurationNode> GetLoader() {
        return this.loader;
    }

    public ConfigurationNode GetRootNode() {
        return this.rootNode;
    }

}
