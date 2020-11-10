package cn.gonjubaike.siwangxiangzi.ZhiLing;

import cn.gonjubaike.siwangxiangzi.Util.Config;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;


public class SetHomeCommand implements CommandExecutor {
    ConfigurationNode rootNode = Config.getConfNode();
    ConfigurationNode homeRoot = rootNode.getNode("homes");
    ConfigurationNode homeList = rootNode.getNode("homeList");
    ConfigurationLoader<CommentedConfigurationNode> loader = Config.getLoader();

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            Player p = (Player) src;
            Location<World> loc = p.getLocation();
            String key = args.<String>getOne("name")
                    .orElse(String.valueOf(++HomeManager.homeNum));
            if (HomeManager.homes_L.contains(key)) {
                HomeManager.homes.replace(key, loc);
            } else {
                HomeManager.homes_L.add(key);
                HomeManager.homes.put(key, loc);
            }
            homeList.setValue(HomeManager.homes_L);
            homeRoot.getNode(key).getNode("world").setValue(p.getWorld().getName());
            homeRoot.getNode(key).getNode("x").setValue(loc.getX());
            homeRoot.getNode(key).getNode("y").setValue(loc.getY());
            homeRoot.getNode(key).getNode("z").setValue(loc.getZ());
            try {
                loader.save(rootNode);
                System.out.println("成功添加/修改家节点：" + key);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return CommandResult.success();
        }
        return null;
    }
}
