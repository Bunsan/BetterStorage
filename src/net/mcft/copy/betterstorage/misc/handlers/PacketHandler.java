package net.mcft.copy.betterstorage.misc.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.mcft.copy.betterstorage.utils.PlayerUtils;
import net.mcft.copy.betterstorage.utils.RandomUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {
	
	public static final int openGui = 0;
	public static final int backpackTeleport = 1;
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player pl) {
		EntityPlayer player = (EntityPlayer)pl;
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			int id = stream.readByte();
			switch (id) {
				case openGui:
					if (side == Side.SERVER)
						throw new Exception("Received BetterStorage packet for ID " + id + " on invalid side " + side + ".");
					int windowId = stream.readInt();
					String name = stream.readUTF();
					int columns = stream.readByte();
					int rows = stream.readByte();
					String title = stream.readUTF();
					PlayerUtils.openGui(player, name, columns, rows, title);
					player.openContainer.windowId = windowId;
					break;
				case backpackTeleport:
					if (side == Side.SERVER)
						throw new Exception("Received BetterStorage packet for ID " + id + " on invalid side " + side + ".");
					int sourceX = stream.readInt();
					int sourceY = stream.readInt();
					int sourceZ = stream.readInt();
					int x = stream.readInt();
					int y = stream.readInt();
					int z = stream.readInt();
					World world = Minecraft.getMinecraft().theWorld;
					int amount = 128;
					for (int i = 0; i < amount; i++) {
						double a = i / (double)(amount - 1);
						double vX = RandomUtils.getDouble(-0.3, 0.3);
						double vY = RandomUtils.getDouble(-0.3, 0.3);
						double vZ = RandomUtils.getDouble(-0.3, 0.3);
						double pX = sourceX + (x - sourceX) * a + RandomUtils.getDouble(0.3, 0.7);
						double pY = sourceY + (y - sourceY) * a + RandomUtils.getDouble(-0.5, 0.0) + a / 2;
						double pZ = sourceZ + (z - sourceZ) * a + RandomUtils.getDouble(0.3, 0.7);
						world.spawnParticle("portal", pX, pY, pZ, vX, vY, vZ);
					}
					break;
				default:
					throw new Exception("Received BetterStorage packet for unhandled ID " + id + " on side " + side + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
