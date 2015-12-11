package net.mcft.copy.betterstorage.misc.handlers.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import net.mcft.copy.betterstorage.misc.Recipes;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;

/**
 * Created by tim_mcphee on 10-12-15.
 */
public abstract class InitClientWorldPacket extends AbstractPacket
{
    public InitClientWorldPacket()
    {
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
    }

    @Override
    public void handleClientSide(EntityPlayer player)
    {
        if (!Recipes.areAnvilRecipesRegistered())
            Recipes.registerAnvilRecipes(player.worldObj);
    }

    @Override
    public void handleServerSide(EntityPlayer player)
    {
    }
}
