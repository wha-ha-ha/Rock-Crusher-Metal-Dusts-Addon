package rcDusts.network;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {
	
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		
	}
	
}