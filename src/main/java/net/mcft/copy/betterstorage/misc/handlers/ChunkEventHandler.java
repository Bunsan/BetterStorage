package net.mcft.copy.betterstorage.misc.handlers;

import net.minecraftforge.event.world.WorldEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.mcft.copy.betterstorage.misc.Recipes;
/**
 * Created by Bunsan on 10-12-15.
 */
public class ChunkEventHandler {
    @SubscribeEvent
    public void onLoadWorld(WorldEvent.Load event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0 && !Recipes.areAnvilRecipesRegistered())
        {
            Recipes.registerAnvilRecipes(event.world);
        }
    }
}
