package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;

import java.io.IOException;
import java.nio.file.Path;

public class Config {
    @Inject
    private static Logger logger;

    /**
     * 拿到分配好的路径
     */
    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

//    @Inject
//    @DefaultConfig(sharedRoot = true)
//    private ConfigurationLoader<CommentedConfigurationNode> configManager;
//
//    @Inject
//    @ConfigDir(sharedRoot = false)
//    private Path privateConfigDir;

    private static ConfigurationLoader<CommentedConfigurationNode> loader;
    private static ConfigurationNode rootNode;

    /**
     * 初始化配置
     */
    public void RunConfig() {
        Sponge.getAssetManager().getAsset(this, "default.conf")
                .ifPresent(f -> {
                    try {
                        f.copyToFile(defaultConfig, false, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        loader = HoconConfigurationLoader.builder().setPath(defaultConfig).build();
        try {
//            加载节点信息
            rootNode = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("[死亡箱子]初始化配置失败！");
        }

        Sponge.getAssetManager().getAsset(this, "default.conf")
                .ifPresent(asset -> {
                    try {
                        rootNode.mergeValuesFrom(HoconConfigurationLoader.builder()
                                .setURL(
                                        asset.getUrl())
                                .build()
                                .load(ConfigurationOptions.defaults()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

//    public Path GetPrivateConfigDir() {
//        return this.privateConfigDir;
//    }

    public static ConfigurationLoader<CommentedConfigurationNode> GetLoader() {
        return loader;
    }

    public static ConfigurationNode GetRootNode() {
        return rootNode;
    }

    public static Logger getLogger() {
        return logger;
    }
}
