package rcDusts;

import rcDusts.config.ConfigurationHandler;
import rcDusts.items.RcDustsItems;
import rcDusts.network.PacketHandler;
import rcDusts.proxies.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
 

@Mod(modid=ModInformation.MODID, name=ModInformation.MODNAME, version=ModInformation.VERSION, dependencies = "required-after:Railcraft;after:BiomesOPlenty")
@NetworkMod(channels={ModInformation.CHANNEL}, clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class)
public class RcDusts {
 
	@Instance("rcDusts")
	public static RcDusts instance;
 
	@SidedProxy(clientSide="rcDusts.proxies.ClientProxy", serverSide="rcDusts.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());	
		RcDustsItems.init();
	}
 
	@EventHandler
	public void init(FMLInitializationEvent event) {
		RcDustsItems.isIC2Installed = Boolean.valueOf(Loader.isModLoaded("IC2")).booleanValue();
		RcDustsItems.isGregTechInstalled = Boolean.valueOf(Loader.isModLoaded("gregtech_addon")).booleanValue();
		RcDustsItems.isTinkersConstructInstalled = Boolean.valueOf(Loader.isModLoaded("TConstruct")).booleanValue();
		RcDustsItems.isNetherOresInstalled = Boolean.valueOf(Loader.isModLoaded("NetherOres")).booleanValue();
		
		//config sanity check
		if(!RcDustsItems.isIC2Installed && ConfigurationHandler.doIC2CrushedOres) {
			ConfigurationHandler.doIC2CrushedOres = false;
		}
		
		LanguageRegistry.instance().addStringLocalization("itemGroup." + ModInformation.MODNAME, "en_US", ModInformation.MODNAME);
		RcDustsItems.addNames();
		RcDustsItems.registerDustsWithOreDict();
		RcDustsItems.biomesOPlentyOreDictRoundup();
	}
 
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RcDustsItems.checkWhatIngotsWeHave();
		if(RcDustsItems.isIC2Installed) {
			RcDustsItems.removeSomeRecipes();
		}
		RcDustsItems.registerBaseRecipes();
		if(!RcDustsItems.isGregTechInstalled) {
			RcDustsItems.registerRcDustsRecipes();
			if(RcDustsItems.isNetherOresInstalled && ConfigurationHandler.doNetherOreRecipes) {
				RcDustsItems.registerNetherOresRecipes();
			}
		}
		RcDustsItems.otherOresRoundup();
		if(ConfigurationHandler.removeBlastFurnaceSteel) {
			RcDustsItems.removeBlastFurnaceSteel();
		}
		if(RcDustsItems.isGregTechInstalled && RcDustsItems.isTinkersConstructInstalled) {
			RcDustsItems.doGregTechTinkersFixes();
		}
	}
}