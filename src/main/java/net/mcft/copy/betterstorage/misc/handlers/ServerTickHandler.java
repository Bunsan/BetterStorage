package net.mcft.copy.betterstorage.misc.handlers;

import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.world.World;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

import net.mcft.copy.betterstorage.misc.Recipes;

/**
 * Created by Bunsan on 10-12-15.
 */
public class ServerTickHandler {
    @SubscribeEvent
    public void onServerWorldTick(TickEvent.WorldTickEvent event)
    {
        World world = event.world;

        if (event.phase == Phase.START)
        {
            if (world.provider.dimensionId == 0 && !Recipes.areAnvilRecipesRegistered())
            {
                Recipes.registerAnvilRecipes(world);
            }
        }
    }
}
