package cn.gonjubaike.siwangxiangzi;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "siwangxiangzi",
        name = "Siwangxiangzi",
        description = "死亡后会原地生成漂浮的箱子"
)
public class Siwangxiangzi {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("已成功运行死亡箱子插件！");
    }
}
