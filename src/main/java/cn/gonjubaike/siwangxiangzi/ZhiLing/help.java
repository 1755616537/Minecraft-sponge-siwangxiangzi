package cn.gonjubaike.siwangxiangzi.ZhiLing;

import com.google.inject.internal.cglib.proxy.$Callback;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.serializer.TextSerializer;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class help implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

//        判断执行者类型
        if (src instanceof Player){
//            玩家类型
            Player player=(Player) src;
            Text message= TextSerializers.FORMATTING_CODE.deserialize("&ahello &d&1"+player.getName());
            src.sendMessage(message);

            URL url=null;
            try {
                url=new URL("www.gongjubaike.cn");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Text urlText=Text.builder("官方网")
                    .onClick(TextActions.openUrl(url))
                    .build();
            Text hoverText=Text.builder("Name")
                    .onHover(TextActions.showText(Text.of(src.getName())))
                    .build();
            Text messageText =Text.builder("Message")
                    .onClick(TextActions.executeCallback(callback -> doStuff()))
                    .build();
            ArrayList<Text>contents=new ArrayList<Text>();
            contents.add(TextSerializers.FORMATTING_CODE.deserialize("&cFirst Line"));
            contents.add(TextSerializers.FORMATTING_CODE.deserialize("&cSecond Line"));
            contents.add(TextSerializers.FORMATTING_CODE.deserialize("&cThird Line"));
            PaginationList.builder()
                    .title(Text.of(TextColors.RED,"死亡箱子"))
                    .padding(Text.of(TextColors.AQUA,"="))
//                    .header(Text.of(TextColors.RED,"Header"))
                    .linesPerPage(5)
                    .contents(contents)
                    .contents(urlText,hoverText,messageText)
                    .sendTo(src);
//                    .build();

        }else if(src instanceof ConsoleSource) {
//            控制台类型
            src.sendMessage(Text.of(TextColors.GRAY,"/help - > 控制台"));
        }
        else if(src instanceof CommandBlockSource) {
//            命令方块类型
            src.sendMessage(Text.of(TextColors.GRAY,"/help - > 命令方块"));
        }


        return CommandResult.success();
    }

    private void doStuff() {
    }

    public static CommandSpec base() {
        return CommandSpec
                .builder()
//                需要的权限
                .permission("siwangxiangzi.command.help")
//                简短的描述
                .description(Text.of("[死亡箱子插件] 帮助"))
//                较长的扩展说明,被追加到简短描述后
                .extendedDescription(Text.of("无"))
//                需要的参数描述
                .arguments(
//                        把所有剩余的参数使用空格连接起来（对于消息命令来说十分有用）。
//                        GenericArguments.remainingJoinedStrings(Text.of("name"))
                )
//                执行器
                .executor(new help())
                .build();
    }
}
