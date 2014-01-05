package rcDusts.config;

import java.io.File;

import rcDusts.items.RcDustsItemInfo;

import net.minecraftforge.common.Configuration;
import rcDusts.items.RcDustsItemInfo;

public class ConfigurationHandler {
	
	public static int bronzePerCraft;
	public static boolean manyullynBlastFurnace;
	public static boolean cobaltBlastFurnace;
	public static boolean arditeBlastFurnace;
	public static boolean doNetherOreRecipes;
	public static boolean removeBlastFurnaceSteel;
	public static boolean doIC2CrushedOres;
	
	public static void init(File file) {
			
		Configuration config = new Configuration(file);
		config.load();
		
		RcDustsItemInfo.DUST_ID = config.getItem(RcDustsItemInfo.DUST_KEY, RcDustsItemInfo.DUST_DEFAULT).getInt() - 256;
		bronzePerCraft = config.get("OPTIONS", "Bronze Dust Crafting Output", 4).getInt();
		manyullynBlastFurnace = config.get("OPTIONS", "manyullynBlastFurnace", true, "cobalt-ardite blend must be smelted in a Blast Furnace to produce ingots.").getBoolean(true);
		cobaltBlastFurnace = config.get("OPTIONS", "cobaltBlastFurnace", true, "cobalt dust must be smelted in a Blast Furnace to produce ingots.").getBoolean(true);
		arditeBlastFurnace = config.get("OPTIONS", "arditeBlastFurnace", true, "ardite dust must be smelted in a Blast Furnace to produce ingots.").getBoolean(true);
		doNetherOreRecipes = config.get("OPTIONS", "doNetherOreRecipes", true, "produce recipes for Powercrystals' Nether Ores if the mod is installed.").getBoolean(true);
		removeBlastFurnaceSteel = config.get("OPTIONS", "removeBlastFurnaceSteel", false, "remove railcraft blast furnace recipes that produce steel\nfor balancing with other mods that add their own steel production.").getBoolean(false);
		doIC2CrushedOres = config.get("OPTIONS", "doIC2CrushedOres", false, "recipes output IC2 crushed ores instead of dusts if possible.").getBoolean(false);
		
		config.save();
		
	}
	
 }