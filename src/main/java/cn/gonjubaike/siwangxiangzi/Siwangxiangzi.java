package cn.gonjubaike.siwangxiangzi;

import cn.gonjubaike.siwangxiangzi.Util.Config;
import cn.gonjubaike.siwangxiangzi.Util.Logger;
import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(
        id = "siwangxiangzi",
        name = "Siwangxiangzi",
        description = "死亡后会原地生成漂浮的箱子"
)
public class Siwangxiangzi {
    //    初始化状态-------------------------------------------------------------
    @Listener
    public void onConstruction(GameConstructionEvent event) {
//        游戏会构造带有 @Plugin 注解的类实例
    }

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {
//        插件准备进行初始化，这时默认的 Logger 已经准备好被调用，同时你也可以开始引用配置文件中的内容

//        读取配置

//        连接数据库

    }

    @Listener
    public void onInitialization(GameInitializationEvent event) {
//        插件应该完成他所需功能的所有应该完成的准备工作，你应该在这个事件发生时注册监听事件
    }

    @Listener
    public void onPostInitialization(GamePostInitializationEvent event) {
//        你应该准备好不同插件之间的交互信息，有的插件也提供了一些基础 API 来接受交互请求
    }

    @Listener
    public void onLoadComplete(GameLoadCompleteEvent event) {
//        所有插件都已准备就绪
    }


    //    运行中状态-------------------------------------------------------------
    @Listener
    public void onAboutToStartServer(GameAboutToStartServerEvent event) {
//        服务器实例已经可用，但世界仍未被载入
    }

    @Listener
    public void onStartingServer(GameStartingServerEvent event) {
//        服务器初始化和世界载入都已经完成，你应该在这时注册插件命令
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) throws Exception {
//        服务器实例已经可用，世界也已被载入

        Logger.getLogger().info("[死亡箱子]插件成功运行！");
    }

    @Listener
    public void onStoppingServer(GameStoppingServerEvent event) {
//        服务器会进入最后一个 Tick，紧接着就会开始保存世界
    }

    @Listener
    public void onStoppedServer(GameStoppedServerEvent event) {
//        任何玩家都无法连接至服务器，对世界所做的任何更改也不会被保存
    }


    //    停止时状态-------------------------------------------------------------
    @Listener
    public void onStopping(GameStoppingEvent event) {
//        此阶段会在 GAME_STOPPED 之前出现，此时会触发 GameStoppingEvent。提供 API 的插件仍然可以接受基本调用
    }

    @Listener
    public void onStopped(GameStoppedEvent event) {
//        Minecraft 将会立即关闭，此时插件不应跟游戏进行任何交互
    }
}
