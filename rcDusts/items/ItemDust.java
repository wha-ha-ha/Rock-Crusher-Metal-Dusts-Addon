package rcDusts.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDust extends Item {

	static int id = RcDustsItemInfo.DUST_ID;
	static int meta;
	
	private Icon[] dustIcons = new Icon[12];
	
	
	public ItemDust(int id) {
		super(id);
		setHasSubtypes(true);
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMaterials);
		setUnlocalizedName(RcDustsItemInfo.DUST_NAMES[meta]);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		for(int i = 0; i < dustIcons.length; i++) {
			dustIcons[i] = register.registerIcon(RcDustsItemInfo.TEXTURE_LOCATION + ":" + RcDustsItemInfo.DUST_ICONS[i]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInformation) {
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dmg) {
		return dustIcons[dmg];
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
        return RcDustsItemInfo.DUST_UNLOCALIZED_NAMES[itemstack.getItemDamage()];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list){
		for(int i = 0; i < RcDustsItemInfo.DUST_NAMES.length; i++){
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}