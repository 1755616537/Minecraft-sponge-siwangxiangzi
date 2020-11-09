package cn.gonjubaike.siwangxiangzi.ZhiLing;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeManager {
    public static HashMap<String, Location<World>> homes = new HashMap<>();
    public static List<String> homes_L = new ArrayList<String>();
    public static int homeNum = 0;
}
