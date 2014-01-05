package rcDusts.items;

import ic2.core.AdvShapelessRecipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Random;

import mods.railcraft.api.crafting.IBlastFurnaceRecipe;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.items.ItemDust.EnumDust;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rcDusts.config.ConfigurationHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class RcDustsItems {
	
	public static boolean isIC2Installed;
	public static boolean isTinkersConstructInstalled;
	public static boolean isGregTechInstalled;
	public static boolean isNetherOresInstalled;
	
	public static ItemDust dust;
	public static ItemStack dustCopper;
	public static ItemStack dustGold;
	public static ItemStack dustIron;
	public static ItemStack dustLead;
	public static ItemStack dustSilver;
	public static ItemStack dustTin;
	public static ItemStack dustBronze;
	public static ItemStack dustNaturalAluminum;
	public static ItemStack dustAluminumBrass;
	public static ItemStack dustCobalt;
	public static ItemStack dustArdite;
	public static ItemStack dustManyullyn;
	
	public static boolean hasCopperIngot;
	public static boolean hasLeadIngot;
	public static boolean hasSilverIngot;
	public static boolean hasTinIngot;

	
	public static void init() {
		
		dust = new ItemDust(RcDustsItemInfo.DUST_ID);
		
		dustCopper = new ItemStack(dust, 1, 0);
		dustGold = new ItemStack(dust, 1, 1);
		dustIron = new ItemStack(dust, 1, 2);
		dustLead = new ItemStack(dust, 1, 3);
		dustSilver = new ItemStack(dust, 1, 4);
		dustTin = new ItemStack(dust, 1, 5);
		dustBronze = new ItemStack(dust, 1, 6);
		dustNaturalAluminum = new ItemStack(dust, 1, 7);
		dustAluminumBrass = new ItemStack(dust, 1, 8);
		dustCobalt = new ItemStack(dust, 1, 9);
		dustArdite = new ItemStack(dust, 1, 10);
		dustManyullyn = new ItemStack(dust, 1, 11);
		
		
		
	}
	
	public static void addNames() {
		
		LanguageRegistry.addName(dustCopper, RcDustsItemInfo.DUST_NAMES[0]);
		LanguageRegistry.addName(dustGold, RcDustsItemInfo.DUST_NAMES[1]);
		LanguageRegistry.addName(dustIron, RcDustsItemInfo.DUST_NAMES[2]);
		LanguageRegistry.addName(dustLead, RcDustsItemInfo.DUST_NAMES[3]);
		LanguageRegistry.addName(dustSilver, RcDustsItemInfo.DUST_NAMES[4]);
		LanguageRegistry.addName(dustTin, RcDustsItemInfo.DUST_NAMES[5]);
		LanguageRegistry.addName(dustBronze, RcDustsItemInfo.DUST_NAMES[6]);
		LanguageRegistry.addName(dustNaturalAluminum, RcDustsItemInfo.DUST_NAMES[7]);
		LanguageRegistry.addName(dustAluminumBrass, RcDustsItemInfo.DUST_NAMES[8]);
		LanguageRegistry.addName(dustCobalt, RcDustsItemInfo.DUST_NAMES[9]);
		LanguageRegistry.addName(dustArdite, RcDustsItemInfo.DUST_NAMES[10]);
		LanguageRegistry.addName(dustManyullyn, RcDustsItemInfo.DUST_NAMES[11]);
		
		
	}
	
	public static void registerDustsWithOreDict() {
		
		OreDictionary.registerOre("dustCopper", dustCopper);
		OreDictionary.registerOre("dustGold", dustGold);
		OreDictionary.registerOre("dustIron", dustIron);
		OreDictionary.registerOre("dustLead", dustLead);
		OreDictionary.registerOre("dustSilver", dustSilver);
		OreDictionary.registerOre("dustTin", dustTin);
		OreDictionary.registerOre("dustBronze", dustBronze);
		OreDictionary.registerOre("dustAluminum", dustNaturalAluminum);
		OreDictionary.registerOre("dustAluminumBrass", dustAluminumBrass);
		OreDictionary.registerOre("dustCobalt", dustCobalt);
		OreDictionary.registerOre("dustArdite", dustArdite);
		OreDictionary.registerOre("dustManyullyn", dustManyullyn);
		
		OreDictionary.registerOre("ingotIron", new ItemStack(Item.ingotIron));
		OreDictionary.registerOre("ingotGold", new ItemStack(Item.ingotGold));
		
	}
	
	public static void checkWhatIngotsWeHave() {
		
		hasCopperIngot = !OreDictionary.getOres("ingotCopper").isEmpty();
		hasLeadIngot = !OreDictionary.getOres("ingotLead").isEmpty();
		hasSilverIngot = !OreDictionary.getOres("ingotSilver").isEmpty();
		hasTinIngot = !OreDictionary.getOres("ingotTin").isEmpty();
		
	}
	
	public static void removeSomeRecipes() {
		//remove the bronze dust recipe
		ListIterator<IRecipe> iterator = CraftingManager.getInstance().getRecipeList().listIterator();
		ItemStack recipeResult = null;
		while(iterator.hasNext()) {
			IRecipe r = iterator.next();
			if(r instanceof AdvShapelessRecipe) {
				recipeResult = r.getRecipeOutput();
				if(OreDictionary.getOreName(OreDictionary.getOreID(recipeResult)).equals("dustBronze")) {
					iterator.remove();
					break;
				}
			}
		}
		//remove the IC2 rock crusher recipes
		ListIterator<IRockCrusherRecipe> crusherIterator = RailcraftCraftingManager.rockCrusher.getRecipes().listIterator();

		ArrayList<Integer> oresToGrab = new ArrayList<Integer>();
		oresToGrab.add(OreDictionary.getOreID("oreIron"));
		oresToGrab.add(OreDictionary.getOreID("oreGold"));
		oresToGrab.add(OreDictionary.getOreID("oreCopper"));
		oresToGrab.add(OreDictionary.getOreID("oreTin"));
		oresToGrab.add(OreDictionary.getOreID("oreSilver"));
		oresToGrab.add(OreDictionary.getOreID("oreLead"));
		oresToGrab.add(OreDictionary.getOreID("oreNickel"));
		oresToGrab.add(OreDictionary.getOreID("oreUranium"));
		while(crusherIterator.hasNext()) {
			IRockCrusherRecipe r = crusherIterator.next();
			ItemStack input = r.getInput();
			List<ItemStack> outputs = r.getPossibleOuputs();
			int oreIndex = OreDictionary.getOreID(input);
			if(outputs.size() == 1 && oreIndex > -1) {
				if(oresToGrab.contains(oreIndex)) {
					crusherIterator.remove();
				}
			}
		}
	}
	
	public static void registerRcDustsRecipes() {
		
		ItemStack copperCrushSingle = null;
		ItemStack copperCrushDouble = null;
		ItemStack tinCrushSingle = null;
		ItemStack tinCrushDouble = null;
		ItemStack goldCrushSingle = null;
		ItemStack goldCrushDouble = null;
		ItemStack ironCrushSingle = null;
		ItemStack ironCrushDouble = null;
		ItemStack silverCrushSingle = null;
		ItemStack silverCrushDouble = null;
		ItemStack leadCrushSingle = null;
		ItemStack leadCrushDouble = null;
		boolean hasNickel = false;
		ItemStack nickel = null;
		
		if(isIC2Installed && ConfigurationHandler.doIC2CrushedOres) {
			copperCrushSingle = OreDictionary.getOres("crushedCopper").get(0);
			copperCrushDouble = new ItemStack(copperCrushSingle.itemID, 2, copperCrushSingle.getItemDamage());
			tinCrushSingle = OreDictionary.getOres("crushedTin").get(0);
			tinCrushDouble = new ItemStack(tinCrushSingle.itemID, 2, tinCrushSingle.getItemDamage());
			goldCrushSingle = OreDictionary.getOres("crushedGold").get(0);
			goldCrushDouble = new ItemStack(goldCrushSingle.itemID, 2, goldCrushSingle.getItemDamage());
			ironCrushSingle = OreDictionary.getOres("crushedIron").get(0);
			ironCrushDouble = new ItemStack(ironCrushSingle.itemID, 2, ironCrushSingle.getItemDamage());
			silverCrushSingle = OreDictionary.getOres("crushedSilver").get(0);
			silverCrushDouble = new ItemStack(silverCrushSingle.itemID, 2, silverCrushSingle.getItemDamage());
			leadCrushSingle = OreDictionary.getOres("crushedLead").get(0);
			leadCrushDouble = new ItemStack(leadCrushSingle.itemID, 2, leadCrushSingle.getItemDamage());
			hasNickel = OreDictionary.getOres("crushedNickel").size() > 0;
			if(hasNickel) {
				nickel = OreDictionary.getOres("crushedNickel").get(0);
			}
		}else {
			copperCrushSingle = dustCopper;
			copperCrushDouble = new ItemStack(dust, 2, 0);
			goldCrushSingle = dustGold;
			goldCrushDouble = new ItemStack(dust, 2, 1);
			ironCrushSingle = dustIron;
			ironCrushDouble = new ItemStack(dust, 2, 2);
			leadCrushSingle = dustLead;
			leadCrushDouble = new ItemStack(dust, 2, 3);
			silverCrushSingle = dustSilver;
			silverCrushDouble = new ItemStack(dust, 2, 4);
			tinCrushSingle = dustTin;
			tinCrushDouble = new ItemStack(dust, 2, 5);
			hasNickel = OreDictionary.getOres("dustNickel").size() > 0;
			if(hasNickel) {
				nickel = OreDictionary.getOres("dustNickel").get(0);
			}
		}
		
		ItemStack doubleString = new ItemStack(Item.silk, 2);
		ItemStack singleString = new ItemStack(Item.silk, 1);
		for (int i = 0; i < 16; i++) {
			ItemStack newFoundOre = new ItemStack(Block.cloth, 1, i);
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(doubleString, 1.0F);
			r.addOutput(singleString, .5F);
		}
		
		IRockCrusherRecipe c = RailcraftCraftingManager.rockCrusher.createNewRecipe(new ItemStack(Block.oreCoal, 1), true, true);
		c.addOutput(new ItemStack(Item.coal, 2, 0), 1.0F);
		c.addOutput(new ItemStack(Item.coal, 1, 0), .10F);
		
		mods.railcraft.common.items.ItemDust railcraftDust = new mods.railcraft.common.items.ItemDust(RailcraftConfig.getItemId("railcraft.dust"));
		ItemStack sulfur = new ItemStack(railcraftDust, 1, EnumDust.SULFUR.ordinal());
		for (ItemStack newFoundOre : OreDictionary.getOres("oreSulfur")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(new ItemStack(sulfur.itemID, 8, sulfur.getItemDamage()), 1.0F);
			r.addOutput(new ItemStack(sulfur.itemID, 1, sulfur.getItemDamage()), .1F);
		}
		
		ItemStack saltpeter = new ItemStack(railcraftDust, 1, EnumDust.SALTPETER.ordinal());
		for (ItemStack newFoundOre : OreDictionary.getOres("oreSaltpeter")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(new ItemStack(saltpeter.itemID, 5, saltpeter.getItemDamage()), 1.0F);
			r.addOutput(new ItemStack(saltpeter.itemID, 1, saltpeter.getItemDamage()), .1F);
		}
		
		ItemStack emeraldDouble = new ItemStack(Item.emerald, 2);
		ItemStack emeraldSingle = new ItemStack(Item.emerald, 1);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreEmerald")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(emeraldDouble, 1.0F);
			r.addOutput(emeraldSingle, .1F);
		}
		
		ItemStack redstoneTen = new ItemStack(Item.redstone, 10);
		ItemStack glowstoneSingle = new ItemStack(Item.glowstone, 1);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreRedstone")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(redstoneTen, 1.0F);
			r.addOutput(glowstoneSingle, .1F);
		}
		
		ItemStack diamondDouble = new ItemStack(Item.diamond, 2);
		ItemStack coalSingle = new ItemStack(Item.coal, 1, 0);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreDiamond")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(diamondDouble, 1.0F);
			r.addOutput(coalSingle, .1F);
		}
		
		ItemStack lapisTwelve = new ItemStack(Item.dyePowder, 12, 4);
		ItemStack lapisSingle = new ItemStack(Item.dyePowder, 1, 4);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreLapis")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(lapisTwelve, 1.0F);
			r.addOutput(lapisSingle, .1F);
		}
		
		ItemStack quartzSingle = new ItemStack(Item.netherQuartz, 1);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreQuartz")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(quartzSingle, 1.0F);
			r.addOutput(quartzSingle, .5F);
		}
		
		if(!OreDictionary.getOres("dustNetherQuartz").isEmpty()) {
			IRockCrusherRecipe netherQuartzDust = RailcraftCraftingManager.rockCrusher.createNewRecipe(quartzSingle, true, true);
			netherQuartzDust.addOutput(OreDictionary.getOres("dustNetherQuartz").get(0), 1.0F);
		}
		
		if(!OreDictionary.getOres("gemSapphire").isEmpty() && !OreDictionary.getOres("oreSapphire").isEmpty()) {
			ItemStack sapphire = OreDictionary.getOres("gemSapphire").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreSapphire")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(sapphire.itemID, 2, sapphire.getItemDamage()), 1.0F);
				r.addOutput(sapphire, .1F);
				if(!OreDictionary.getOres("gemGreenSapphire").isEmpty()) {
					r.addOutput(OreDictionary.getOres("gemGreenSapphire").get(0), .025F);
				}
			}
		}
		
		if(!OreDictionary.getOres("gemGreenSapphire").isEmpty() && !OreDictionary.getOres("oreGreenSapphire").isEmpty()) {
			ItemStack greenSapphire = OreDictionary.getOres("gemGreenSapphire").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreGreenSapphire")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(greenSapphire.itemID, 2, greenSapphire.getItemDamage()), 1.0F);
				r.addOutput(greenSapphire, .1F);
				if(!OreDictionary.getOres("gemSapphire").isEmpty()) {
					r.addOutput(OreDictionary.getOres("gemSapphire").get(0), .025F);
				}
			}
		}
		
		if(!OreDictionary.getOres("oreRuby").isEmpty() &&!OreDictionary.getOres("oreRuby").isEmpty()) {
			ItemStack ruby = OreDictionary.getOres("gemRuby").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreRuby")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(ruby.itemID, 2, ruby.getItemDamage()), 1.0F);
				r.addOutput(ruby, .1F);
			}
		}
		
		if(isIC2Installed) {
			ItemStack iridium = ic2.core.Ic2Items.iridiumOre;
			for (ItemStack newFoundOre : OreDictionary.getOres("oreIridium")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(iridium.itemID, 2, iridium.getItemDamage()), 1.0F);
				r.addOutput(iridium, .1F);
				if(!OreDictionary.getOres("dustPlatium").isEmpty()) {
					r.addOutput(OreDictionary.getOres("dustPlatium").get(0), .025F);
				}
			}
		}
		
		if(!OreDictionary.getOres("orePlatinum").isEmpty() && !OreDictionary.getOres("dustPlatinum").isEmpty()) {
			ItemStack platinum = OreDictionary.getOres("dustPlatinum").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("orePlatinum")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(platinum.itemID, 2, platinum.getItemDamage()), 1.0F);
				r.addOutput(platinum, .1F);
				if(isIC2Installed) {
					r.addOutput(ic2.core.Ic2Items.iridiumOre, .025F);
				}
			}
		}
		
		if(!OreDictionary.getOres("oreNickel").isEmpty() && !OreDictionary.getOres("dustNickel").isEmpty()) {
			ItemStack dustNickel = OreDictionary.getOres("dustNickel").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreNickel")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(dustNickel.itemID, 2, dustNickel.getItemDamage()), 1.0F);
				r.addOutput(dustNickel, .1F);
				if(!OreDictionary.getOres("dustPlatinum").isEmpty()) {
					r.addOutput(OreDictionary.getOres("dustPlatinum").get(0), .025F);
				}
			}
		}
		
		if(!OreDictionary.getOres("oreCertusQuartz").isEmpty() && !OreDictionary.getOres("crystalCertusQuartz").isEmpty() && !OreDictionary.getOres("dustCertusQuartz").isEmpty()) {
			ItemStack certus = OreDictionary.getOres("crystalCertusQuartz").get(0);
			ItemStack certusDust = OreDictionary.getOres("dustCertusQuartz").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreCertusQuartz")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(certus.itemID, 2, certus.getItemDamage()), 1.0F);
				r.addOutput(certusDust, .1F);
			}
			for (ItemStack newFoundOre : OreDictionary.getOres("crystalCertusQuartz")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(certusDust, 1.0F);
			}
		}
		
		if(!OreDictionary.getOres("dustWheat").isEmpty()) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(new ItemStack(Item.wheat), true, true);
			r.addOutput(OreDictionary.getOres("dustWheat").get(0), 1.0F);
		}
		
		if(!OreDictionary.getOres("oreNikolite").isEmpty()) {
			if(!OreDictionary.getOres("dustNikolite").isEmpty()) {
				ItemStack nikolite = OreDictionary.getOres("dustNikolite").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNikolite")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(nikolite.itemID, 15, nikolite.getItemDamage()), 1.0F);
					r.addOutput(nikolite, .1F);
					r.addOutput(new ItemStack(Item.diamond, 1), .025F);
				}
			}	
		}
		
		if(isIC2Installed) {
			ItemStack uranium = ic2.core.Ic2Items.crushedUraniumOre;
			for (ItemStack newFoundOre : OreDictionary.getOres("oreUranium")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(uranium.itemID, 2, uranium.getItemDamage()), 1.0F);
				r.addOutput(uranium, .1F);
			}
		}
		
		if(!OreDictionary.getOres("gemApatite").isEmpty() && !OreDictionary.getOres("oreApatite").isEmpty()) {
			ItemStack apatite = OreDictionary.getOres("gemApatite").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreApatite")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(apatite.itemID, 8, apatite.getItemDamage()), 1.0F);
				r.addOutput(apatite, .1F);
			}
		}
		
		if(hasCopperIngot) {
			for (ItemStack newFoundOre : OreDictionary.getOres("oreCopper")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(copperCrushDouble, 1.0F);
				r.addOutput(copperCrushSingle, .10F);
				r.addOutput(goldCrushSingle, .025F);
				//register copper ore recipes - 2x dust + 15% extra + 2.5% gold?
			}
		}
		
		for (ItemStack newFoundOre : OreDictionary.getOres("oreGold")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(goldCrushDouble, 1.0F);
			r.addOutput(goldCrushSingle, .10F);
			if(hasSilverIngot) {
				r.addOutput(silverCrushSingle, .025F);
			}
			//register gold ore recipes - 2x dust + 15% extra + 2.5% silver?
		}
		
		for (ItemStack newFoundOre : OreDictionary.getOres("oreIron")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(ironCrushDouble, 1.0F);
			r.addOutput(ironCrushSingle, .10F);
			if(hasNickel) {
				r.addOutput(nickel, .025F);
			}else if(hasTinIngot) {
				r.addOutput(tinCrushSingle, .025F);
			}
			//register iron ore recipes - 2x dust + 15% extra + 2.5% tin?
		}
	
		
		if(hasTinIngot) {	
			for (ItemStack newFoundOre : OreDictionary.getOres("oreTin")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(tinCrushDouble, 1.0F);
				r.addOutput(tinCrushSingle, .10F);
				r.addOutput(ironCrushSingle, .025F);
				//register tin ore recipes - 2x dust + 15% extra + 2.5% iron?
			}
		}
		
		if(hasLeadIngot) {
			for (ItemStack newFoundOre : OreDictionary.getOres("oreLead")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(leadCrushDouble, 1.0F);
				r.addOutput(leadCrushSingle, .10F);
				if(hasSilverIngot) {
					r.addOutput(silverCrushSingle, .025F);
				}
				//register lead ore recipes - 2x dust + 15% extra + 2.5% silver?
			}
		
		}
		
		if(hasSilverIngot) {
			if(OreDictionary.getOres("oreLead").isEmpty()) {
				for (ItemStack newFoundOre : OreDictionary.getOres("oreSilver")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(silverCrushDouble, 1.0F);
					r.addOutput(silverCrushSingle, .10F);
					if(hasLeadIngot) {
						r.addOutput(leadCrushDouble, 1.0F);
						r.addOutput(leadCrushSingle, .10F);
					}
					/*register silver ore recipes for lead ingot but no ore (LIKE FACTORIZATION)
					 * 2x silver, 2x lead + 15% extra each?
					 */
				}
			}else {
				for (ItemStack newFoundOre : OreDictionary.getOres("oreSilver")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(silverCrushDouble, 1.0F);
					r.addOutput(silverCrushSingle, .10F);
					if(hasLeadIngot) {
						r.addOutput(leadCrushSingle, .025F);
					}
					/*register silver ore recipes for lead ingot WITH lead ore present
					 * 2x silver + 15% extra, 2.5% lead?
					 */
				}
			}
		}
		
		if(!OreDictionary.getOres("oreOsmium").isEmpty() && !OreDictionary.getOres("oreOsmium").isEmpty()) {
			ItemStack osmium = OreDictionary.getOres("dustOsmium").get(0);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreOsmium")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(new ItemStack(osmium.itemID, 2, osmium.getItemDamage()), 1.0F);
				r.addOutput(osmium, .1F);
			}
		}
		
	}
	
	public static void registerNetherOresRecipes() {
		
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherCoal")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(new ItemStack(Item.coal, 4, 0), 1.0F);
			r.addOutput(new ItemStack(Item.coal, 1, 0), 1.0F);
			r.addOutput(new ItemStack(Item.coal, 1, 0), 1.0F);
		}
		
		ItemStack emeraldQuad = new ItemStack(Item.emerald, 4);
		ItemStack emeraldSingle = new ItemStack(Item.emerald, 1);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherEmerald")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(emeraldQuad, 1.0F);
			r.addOutput(emeraldSingle, .1F);
			r.addOutput(emeraldSingle, .1F);
		}
		
		ItemStack redstoneTwenty = new ItemStack(Item.redstone, 20);
		ItemStack glowstoneSingle = new ItemStack(Item.glowstone, 1);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherRedstone")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(redstoneTwenty, 1.0F);
			r.addOutput(glowstoneSingle, .1F);
			r.addOutput(glowstoneSingle, .1F);
		}
		
		ItemStack diamondQuad = new ItemStack(Item.diamond, 4);
		ItemStack coalSingle = new ItemStack(Item.coal, 1, 0);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherDiamond")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(diamondQuad, 1.0F);
			r.addOutput(coalSingle, .1F);
			r.addOutput(coalSingle, .1F);
		}
		
		ItemStack lapisTwentyFour = new ItemStack(Item.dyePowder, 24, 4);
		ItemStack lapisSingle = new ItemStack(Item.dyePowder, 1, 4);
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherLapis")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(lapisTwentyFour, 1.0F);
			r.addOutput(lapisSingle, .1F);
			r.addOutput(lapisSingle, .1F);
		}
		
		ItemStack copperCrushSingle = null;
		ItemStack copperCrushQuad = null;
		ItemStack tinCrushSingle = null;
		ItemStack tinCrushQuad = null;
		ItemStack goldCrushSingle = null;
		ItemStack goldCrushQuad = null;
		ItemStack ironCrushSingle = null;
		ItemStack ironCrushQuad = null;
		ItemStack silverCrushSingle = null;
		ItemStack silverCrushQuad = null;
		ItemStack leadCrushSingle = null;
		ItemStack leadCrushQuad = null;
		boolean hasNickel;
		ItemStack nickel = null;
		
		if(isIC2Installed && ConfigurationHandler.doIC2CrushedOres) {
			copperCrushSingle = OreDictionary.getOres("crushedCopper").get(0);
			copperCrushQuad = new ItemStack(copperCrushSingle.itemID, 4, copperCrushSingle.getItemDamage());
			tinCrushSingle = OreDictionary.getOres("crushedTin").get(0);
			tinCrushQuad = new ItemStack(tinCrushSingle.itemID, 4, tinCrushSingle.getItemDamage());
			goldCrushSingle = OreDictionary.getOres("crushedGold").get(0);
			goldCrushQuad = new ItemStack(goldCrushSingle.itemID, 4, goldCrushSingle.getItemDamage());
			ironCrushSingle = OreDictionary.getOres("crushedIron").get(0);
			ironCrushQuad = new ItemStack(ironCrushSingle.itemID, 4, ironCrushSingle.getItemDamage());
			silverCrushSingle = OreDictionary.getOres("crushedSilver").get(0);
			silverCrushQuad = new ItemStack(silverCrushSingle.itemID, 4, silverCrushSingle.getItemDamage());
			leadCrushSingle = OreDictionary.getOres("crushedLead").get(0);
			leadCrushQuad = new ItemStack(leadCrushSingle.itemID, 4, leadCrushSingle.getItemDamage());
			hasNickel = OreDictionary.getOres("crushedNickel").size() > 0;
			if(hasNickel) {
				nickel = OreDictionary.getOres("crushedNickel").get(0);
			}
		}else {
			copperCrushSingle = dustCopper;
			copperCrushQuad = new ItemStack(dust, 4, 0);
			goldCrushSingle = dustGold;
			goldCrushQuad = new ItemStack(dust, 4, 1);
			ironCrushSingle = dustIron;
			ironCrushQuad = new ItemStack(dust, 4, 2);
			leadCrushSingle = dustLead;
			leadCrushQuad = new ItemStack(dust, 4, 3);
			silverCrushSingle = dustSilver;
			silverCrushQuad = new ItemStack(dust, 4, 4);
			tinCrushSingle = dustTin;
			tinCrushQuad = new ItemStack(dust, 4, 5);
			hasNickel = OreDictionary.getOres("dustNickel").size() > 0;
			if(hasNickel) {
				nickel = OreDictionary.getOres("dustNickel").get(0);
			}
		}
		
		if(hasCopperIngot) {
			for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherCopper")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(copperCrushQuad, 1.0F);
				r.addOutput(copperCrushSingle, .10F);
				r.addOutput(copperCrushSingle, .10F);
				r.addOutput(goldCrushSingle, .025F);
				r.addOutput(goldCrushSingle, .025F);
				//register copper ore recipes - 2x dust + 15% extra + 2.5% gold?
			}
		}
		
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherGold")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(goldCrushQuad, 1.0F);
			r.addOutput(goldCrushSingle, .10F);
			r.addOutput(goldCrushSingle, .10F);
			if(hasSilverIngot) {
				r.addOutput(silverCrushSingle, .025F);
				r.addOutput(silverCrushSingle, .025F);
			}
			//register gold ore recipes - 2x dust + 15% extra + 2.5% silver?
		}
		
		for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherIron")) {
			IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
			r.addOutput(ironCrushQuad, 1.0F);
			r.addOutput(ironCrushSingle, .10F);
			r.addOutput(ironCrushSingle, .10F);
			if(hasNickel) {
				r.addOutput(nickel, .025F);
				r.addOutput(nickel, .025F);
			}else if(hasTinIngot) {
				r.addOutput(tinCrushSingle, .025F);
				r.addOutput(tinCrushSingle, .025F);
			}
			//register iron ore recipes - 2x dust + 15% extra + 2.5% tin?
		}
	
		
		if(hasTinIngot) {
			for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherTin")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(tinCrushQuad, 1.0F);
				r.addOutput(tinCrushSingle, .10F);
				r.addOutput(tinCrushSingle, .10F);
				r.addOutput(ironCrushSingle, .025F);
				r.addOutput(ironCrushSingle, .025F);
				//register tin ore recipes - 2x dust + 15% extra + 2.5% iron?
			}
		}
		
		if(hasLeadIngot) {
			for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherLead")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(leadCrushQuad, 1.0F);
				r.addOutput(leadCrushSingle, .10F);
				r.addOutput(leadCrushSingle, .10F);
				if(hasSilverIngot) {
					r.addOutput(silverCrushSingle, .025F);
					r.addOutput(silverCrushSingle, .025F);
				}
				//register lead ore recipes - 2x dust + 15% extra + 2.5% silver?
			}
		}
		
		if(hasSilverIngot) {
			if(OreDictionary.getOres("oreLead").isEmpty()) {
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherSilver")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(silverCrushQuad, 1.0F);
					r.addOutput(silverCrushSingle, .10F);
					r.addOutput(silverCrushSingle, .10F);
					if(hasLeadIngot) {
						r.addOutput(leadCrushQuad, 1.0F);
						r.addOutput(leadCrushSingle, .10F);
						r.addOutput(leadCrushSingle, .10F);
					}
					
					/*register silver ore recipes for lead ingot but no ore (LIKE FACTORIZATION)
					 * 2x silver, 2x lead + 15% extra each?
					 */
				}
			}else {
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherSilver")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(silverCrushQuad, 1.0F);
					r.addOutput(silverCrushSingle, .10F);
					r.addOutput(silverCrushSingle, .10F);
					if(hasLeadIngot) {
						r.addOutput(leadCrushSingle, .025F);
						r.addOutput(leadCrushSingle, .025F);
					}
					/*register silver ore recipes for lead ingot WITH lead ore present
					 * 2x silver + 15% extra, 2.5% lead?
					 */
				}
			}
			
			if(!OreDictionary.getOres("gemSapphire").isEmpty()) {
				ItemStack sapphire = OreDictionary.getOres("gemSapphire").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherSapphire")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(sapphire.itemID, 4, sapphire.getItemDamage()), 1.0F);
					r.addOutput(sapphire, .1F);
					r.addOutput(sapphire, .1F);
					if(!OreDictionary.getOres("gemGreenSapphire").isEmpty()) {
						r.addOutput(OreDictionary.getOres("gemGreenSapphire").get(0), .025F);
						r.addOutput(OreDictionary.getOres("gemGreenSapphire").get(0), .025F);
					}
				}
			}
			
			if(!OreDictionary.getOres("gemGreenSapphire").isEmpty()) {
				ItemStack greenSapphire = OreDictionary.getOres("gemGreenSapphire").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherGreenSapphire")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(greenSapphire.itemID, 4, greenSapphire.getItemDamage()), 1.0F);
					r.addOutput(greenSapphire, .1F);
					r.addOutput(greenSapphire, .1F);
					if(!OreDictionary.getOres("gemSapphire").isEmpty()) {
						r.addOutput(OreDictionary.getOres("gemSapphire").get(0), .025F);
						r.addOutput(OreDictionary.getOres("gemSapphire").get(0), .025F);
					}
				}
			}
			
			if(!OreDictionary.getOres("gemRuby").isEmpty()) {
				ItemStack ruby = OreDictionary.getOres("gemRuby").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherRuby")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(ruby.itemID, 4, ruby.getItemDamage()), 1.0F);
					r.addOutput(ruby, .1F);
					r.addOutput(ruby, .1F);
				}
			}
			
			if(isIC2Installed) {
				ItemStack uranium = ic2.core.Ic2Items.crushedUraniumOre;
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherUranium")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(uranium.itemID, 4, uranium.getItemDamage()), 1.0F);
					r.addOutput(uranium, .1F);
					r.addOutput(uranium, .1F);
				}
			}
			
			if(isIC2Installed) {
				ItemStack iridium = ic2.core.Ic2Items.iridiumOre;
				for (ItemStack newFoundOre : OreDictionary.getOres("oreIridium")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(iridium.itemID, 4, iridium.getItemDamage()), 1.0F);
					r.addOutput(iridium, .1F);
					r.addOutput(iridium, .1F);
					if(!OreDictionary.getOres("dustPlatium").isEmpty()) {
						r.addOutput(OreDictionary.getOres("dustPlatium").get(0), .025F);
						r.addOutput(OreDictionary.getOres("dustPlatium").get(0), .025F);
					}
				}
			}
			
			if(!OreDictionary.getOres("dustPlatium").isEmpty()) {
				ItemStack platinum = OreDictionary.getOres("dustPlatinum").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherPlatinum")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(platinum.itemID, 4, platinum.getItemDamage()), 1.0F);
					r.addOutput(platinum, .1F);
					r.addOutput(platinum, .1F);
					if(isIC2Installed) {
						r.addOutput(ic2.core.Ic2Items.iridiumOre, .025F);
						r.addOutput(ic2.core.Ic2Items.iridiumOre, .025F);
					}
				}
			}
			
			if(!OreDictionary.getOres("dustCoal").isEmpty()) {
				ItemStack coalDust = OreDictionary.getOres("dustCoal").get(0);
				ItemStack coal = new ItemStack(Item.coal);
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(coal, true, true);
				r.addOutput(coalDust, 1.0F);
			}
			
			if(hasNickel) {
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherNickel")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(nickel.itemID, 4, nickel.getItemDamage()), 1.0F);
					r.addOutput(nickel, .1F);
					r.addOutput(nickel, .1F);
					if(!OreDictionary.getOres("dustPlatinum").isEmpty()) {
						r.addOutput(OreDictionary.getOres("dustPlatinum").get(0), .025F);
						r.addOutput(OreDictionary.getOres("dustPlatinum").get(0), .025F);
					}
				}
			}
			
			if(!OreDictionary.getOres("dustNikolite").isEmpty()) {
				ItemStack nikolite = OreDictionary.getOres("dustNikolite").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherNikolite")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(nikolite.itemID, 30, nikolite.getItemDamage()), 1.0F);
					r.addOutput(nikolite, .1F);
					r.addOutput(nikolite, .1F);
					r.addOutput(new ItemStack(Item.diamond, 1), .025F);
					r.addOutput(new ItemStack(Item.diamond, 1), .025F);
				}
			}
			
			if(!OreDictionary.getOres("dustOsmium").isEmpty()) {
				ItemStack osmium = OreDictionary.getOres("dustOsmium").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherOsmium")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(osmium.itemID, 4, osmium.getItemDamage()), 1.0F);
					r.addOutput(osmium, .1F);
					r.addOutput(osmium, .1F);
				}
			}
			
			if(!OreDictionary.getOres("dustSteel").isEmpty()) {
				ItemStack steel = OreDictionary.getOres("dustSteel").get(0);
				for (ItemStack newFoundOre : OreDictionary.getOres("oreNetherSteel")) {
					IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
					r.addOutput(new ItemStack(steel.itemID, 4, steel.getItemDamage()), 1.0F);
					r.addOutput(steel, .1F);
					r.addOutput(steel, .1F);
				}
			}
		}
	}
	
	public static void registerBaseRecipes() {
		
		if(isTinkersConstructInstalled) {
			
			FurnaceRecipes.smelting().addSmelting(dustNaturalAluminum.itemID, 7, OreDictionary.getOres("ingotAluminum").get(0), 0.1f);
			FurnaceRecipes.smelting().addSmelting(dustAluminumBrass.itemID, 8, OreDictionary.getOres("ingotAluminumBrass").get(0), 0.1f);
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 2, 8), "dustCopper", "dustAluminum", "dustAluminum", "dustAluminum"));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 1, 11), "dustCobalt", "dustCobalt", "dustArdite", "dustArdite"));
			if(ConfigurationHandler.manyullynBlastFurnace) {
				RailcraftCraftingManager.blastFurnace.addRecipe(dustManyullyn, true, true, 1280, OreDictionary.getOres("ingotManyullyn").get(0));
			}else {
				FurnaceRecipes.smelting().addSmelting(dustManyullyn.itemID, 11, OreDictionary.getOres("ingotManyullyn").get(0), 0.1f);
			}
			
			if(ConfigurationHandler.cobaltBlastFurnace) {
				RailcraftCraftingManager.blastFurnace.addRecipe(dustCobalt, true, true, 1280, OreDictionary.getOres("ingotCobalt").get(0));
			}else {
				FurnaceRecipes.smelting().addSmelting(dustCobalt.itemID, 9, OreDictionary.getOres("ingotCobalt").get(0), 0.1f);
			}
			
			if(ConfigurationHandler.arditeBlastFurnace) {
				RailcraftCraftingManager.blastFurnace.addRecipe(dustArdite, true, true, 1280, OreDictionary.getOres("ingotArdite").get(0));
			}else {
				FurnaceRecipes.smelting().addSmelting(dustArdite.itemID, 10, OreDictionary.getOres("ingotArdite").get(0), 0.1f);
			}
			
				
			ItemStack doubleCobalt = new ItemStack(dust, 2, 9);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreCobalt")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(doubleCobalt, 1.0F);
				r.addOutput(dustCobalt, 0.1F);
			}
			
			ItemStack doubleArdite = new ItemStack(dust, 2, 10);
			for (ItemStack newFoundOre : OreDictionary.getOres("oreArdite")) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(newFoundOre, true, true);
				r.addOutput(doubleArdite, 1.0F);
				r.addOutput(dustArdite, 0.1F);
			}
			
		}
		
		if(hasCopperIngot && hasTinIngot) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, ConfigurationHandler.bronzePerCraft, 6), "dustTin", "dustCopper", "dustCopper", "dustCopper"));
			FurnaceRecipes.smelting().addSmelting(dustBronze.itemID, 6, OreDictionary.getOres("ingotBronze").get(0), 0.1f);
		}
		
		FurnaceRecipes.smelting().addSmelting(dustGold.itemID, 1, new ItemStack(Item.ingotGold, 1), 0.1f);
		FurnaceRecipes.smelting().addSmelting(dustIron.itemID, 2, new ItemStack(Item.ingotIron, 1), 0.1f);
		
		if(hasCopperIngot) {
			FurnaceRecipes.smelting().addSmelting(dustCopper.itemID, 0, OreDictionary.getOres("ingotCopper").get(0), 0.1f);
		}
		
		if(hasTinIngot) {
			FurnaceRecipes.smelting().addSmelting(dustTin.itemID, 5, OreDictionary.getOres("ingotTin").get(0), 0.1f);
		}
		
		if(hasLeadIngot) {
			FurnaceRecipes.smelting().addSmelting(dustLead.itemID, 3, OreDictionary.getOres("ingotLead").get(0), 0.1f);
		}
		
		if(hasSilverIngot) {
			FurnaceRecipes.smelting().addSmelting(dustSilver.itemID, 4, OreDictionary.getOres("ingotSilver").get(0), 0.1f);
		}
	}

	public static void biomesOPlentyOreDictRoundup() {
		if(Boolean.valueOf(Loader.isModLoaded("BiomesOPlenty")).booleanValue()) {
			Random random = new Random();
			Block bopOre = biomesoplenty.api.Blocks.amethystOre.get();
			ItemStack gemAmethyst = new ItemStack(bopOre.idDropped(0, random, 0), 1, 2);
			ItemStack oreAmethyst = new ItemStack(bopOre, 1, 0);
			ItemStack oreRuby = new ItemStack(bopOre, 1, 2);
			ItemStack orePeridot = new ItemStack(bopOre, 1, 4);
			ItemStack oreTopaz = new ItemStack(bopOre, 1, 6);
			ItemStack oreTanzanite = new ItemStack(bopOre, 1, 8);
			ItemStack oreApatite = new ItemStack(bopOre, 1, 10);
			ItemStack oreSapphire = new ItemStack(bopOre, 1, 12);
			
			if(OreDictionary.getOreID(gemAmethyst) == -1) {
				OreDictionary.registerOre("gemAmethyst", gemAmethyst);
			}
			if(OreDictionary.getOreID(oreAmethyst) == -1) {
				OreDictionary.registerOre("oreAmethyst", oreAmethyst);
			}
			if(OreDictionary.getOreID(oreRuby) == -1) {
				OreDictionary.registerOre("oreRuby", oreRuby);
			}
			if(OreDictionary.getOreID(orePeridot) == -1) {
				OreDictionary.registerOre("orePeridot", orePeridot);
			}
			if(OreDictionary.getOreID(oreTopaz) == -1) {
				OreDictionary.registerOre("oreTopaz", oreTopaz);
			}
			if(OreDictionary.getOreID(oreTanzanite) == -1) {
				OreDictionary.registerOre("oreTanzanite", oreTanzanite);
			}
			if(OreDictionary.getOreID(oreApatite) == -1) {
				OreDictionary.registerOre("oreApatite", oreApatite);
			}
			if(OreDictionary.getOreID(oreSapphire) == -1) {
				OreDictionary.registerOre("oreSapphire", oreSapphire);
			}	
		}
	}

	/**
	 * TODO: replace registerRcDustsRecipes with this entirely?
	 */
	public static void otherOresRoundup() {
		if(!isGregTechInstalled) {
			String[] ores = OreDictionary.getOreNames();
			boolean hasOre;
			boolean hasIngot;
			for(String s : ores) {
				hasOre = false;
				hasIngot = false;
				if(s.contains("ore")) {
					ListIterator<IRockCrusherRecipe> crusherIterator = RailcraftCraftingManager.rockCrusher.getRecipes().listIterator();
					while(crusherIterator.hasNext() && !hasOre) {	
						IRockCrusherRecipe r = crusherIterator.next();
						if(OreDictionary.getOreID(r.getInput()) == OreDictionary.getOreID(s)) {
							hasOre = true;
						}
					}
				}else if(s.contains("ingot")) {
					ListIterator<IRockCrusherRecipe> crusherIterator = RailcraftCraftingManager.rockCrusher.getRecipes().listIterator();
					while(crusherIterator.hasNext() && !hasIngot) {	
						IRockCrusherRecipe r = crusherIterator.next();
						if(OreDictionary.getOreID(r.getInput()) == OreDictionary.getOreID(s)) {
							hasIngot = true;
						}
					}
				}
				if(!hasOre) {
					String[] oreNamePrecise = s.split("ore");
					for(String q : oreNamePrecise) {
						if(s.contains(q) && !s.equals(q) && !q.equals("")) {
							ArrayList<ItemStack> dustStacks = OreDictionary.getOres("dust" + q);
							ArrayList<ItemStack> gemStacks = OreDictionary.getOres("gem" + q);
							ArrayList<ItemStack> oreStacks = OreDictionary.getOres(s);
							for(ItemStack stack : oreStacks) {
								if(!gemStacks.isEmpty()) {
									ItemStack gemItem = gemStacks.get(0);
									Item item = gemItem.getItem();
									int meta = gemItem.getItemDamage();
									IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(stack, true, true);
									r.addOutput(new ItemStack(item, 2, meta), 1.0F);
									r.addOutput(new ItemStack(item, 1, meta), .1F);
								}else if(!dustStacks.isEmpty()) {
									ItemStack dustItem = dustStacks.get(0);
									Item item = dustItem.getItem();
									int meta = dustItem.getItemDamage();
									IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(stack, true, true);
									r.addOutput(new ItemStack(item, 2, meta), 1.0F);
									r.addOutput(new ItemStack(item, 1, meta), .1F);
								}
							}
						}
					}
				}
				if(!hasIngot) {
					String[] oreNamePrecise = s.split("ingot");
					for(String q : oreNamePrecise) {
						if(s.contains(q) && !s.equals(q) && !q.equals("")) {
							ArrayList<ItemStack> dustStacks = OreDictionary.getOres("dust" + q);
							ArrayList<ItemStack> ingotStacks = OreDictionary.getOres(s);
							for(ItemStack stack : ingotStacks) {
								if(!dustStacks.isEmpty()) {
									ItemStack dustItem = dustStacks.get(0);
									Item item = dustItem.getItem();
									int meta = dustItem.getItemDamage();
									IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(stack, true, true);
									r.addOutput(new ItemStack(item, 1, meta), 1.0F);
								}
							}
						}
					}
				}
			}
		}
	}

	public static void removeBlastFurnaceSteel() {
		ListIterator<IBlastFurnaceRecipe> blastIterator = RailcraftCraftingManager.blastFurnace.getRecipes().listIterator();
		while(blastIterator.hasNext()) {
			IBlastFurnaceRecipe r = blastIterator.next();
			ItemStack output = r.getOutput();
			int a = OreDictionary.getOreID(output);
			if(a == OreDictionary.getOreID("ingotSteel") || a == OreDictionary.getOreID("nuggetSteel") || a == OreDictionary.getOreID("blockSteel")) {
				blastIterator.remove();
			}
		}
	}

	public static void doGregTechTinkersFixes() {
		
		HashSet<Integer> tinkersOreIds = new HashSet<Integer>();
		for(ItemStack s : OreDictionary.getOres("oreCobalt")) {
			tinkersOreIds.add(s.itemID);
		}
		
		for(ItemStack s : OreDictionary.getOres("oreIron")) {
			if(tinkersOreIds.contains(s.itemID)) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(s, true, true);
				ItemStack crushed = OreDictionary.getOres("crushedIron").get(0).copy();
				crushed.stackSize = 2;
				r.addOutput(crushed, 1.0F);
				r.addOutput(OreDictionary.getOres("dustNickel").get(0), 0.1F);
			}
		}
		
		for(ItemStack s : OreDictionary.getOres("oreGold")) {
			if(tinkersOreIds.contains(s.itemID)) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(s, true, true);
				ItemStack crushed = OreDictionary.getOres("crushedGold").get(0).copy();
				crushed.stackSize = 2;
				r.addOutput(crushed, 1.0F);
				r.addOutput(OreDictionary.getOres("dustCopper").get(0), 0.1F);
			}
		}
		
		for(ItemStack s : OreDictionary.getOres("oreCopper")) {
			if(tinkersOreIds.contains(s.itemID)) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(s, true, true);
				ItemStack crushed = OreDictionary.getOres("crushedCopper").get(0).copy();
				crushed.stackSize = 2;
				r.addOutput(crushed, 1.0F);
				r.addOutput(OreDictionary.getOres("dustGold").get(0), 0.1F);
			}
		}
		
		for(ItemStack s : OreDictionary.getOres("oreTin")) {
			if(tinkersOreIds.contains(s.itemID)) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(s, true, true);
				ItemStack crushed = OreDictionary.getOres("crushedTin").get(0).copy();
				crushed.stackSize = 2;
				r.addOutput(crushed, 1.0F);
				r.addOutput(OreDictionary.getOres("dustIron").get(0), 0.1F);
			}
		}
		
		for(ItemStack s : OreDictionary.getOres("oreAluminum")) {
			if(tinkersOreIds.contains(s.itemID)) {
				IRockCrusherRecipe r = RailcraftCraftingManager.rockCrusher.createNewRecipe(s, true, true);
				ItemStack crushed = OreDictionary.getOres("dustImpureAluminium").get(0).copy();
				crushed.stackSize = 2;
				r.addOutput(crushed, 1.0F);
				r.addOutput(OreDictionary.getOres("dustBauxite").get(0), 0.1F);
			}
		}
		
	}
}