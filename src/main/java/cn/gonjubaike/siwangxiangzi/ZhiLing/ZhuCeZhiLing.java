package cn.gonjubaike.siwangxiangzi.ZhiLing;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class ZhuCeZhiLing {
//    注册指令
    public void RunZhuCeZhiLing(Object plugin){
        Sponge.getCommandManager().register(plugin,help.base(),"abc");
    }
}
