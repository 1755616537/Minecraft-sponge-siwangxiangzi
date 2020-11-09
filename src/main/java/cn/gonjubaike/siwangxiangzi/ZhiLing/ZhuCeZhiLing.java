package cn.gonjubaike.siwangxiangzi.ZhiLing;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class ZhuCeZhiLing {
//    注册指令
    public void RunZhuCeZhiLing(){
        CommandSpec setHome = CommandSpec.builder()
                .description(Text.of("设置一个家（重复设置可替换）"))
                .arguments(
                        GenericArguments.remainingJoinedStrings(Text.of("name"))
                )
                .executor(new SetHomeCommand())
                .build();
        Sponge.getCommandManager().register(this, setHome, "sethome");
    }


}
