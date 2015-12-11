package net.mcft.copy.betterstorage.misc;

import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;
import net.mcft.copy.betterstorage.BetterStorage;
import net.mcft.copy.betterstorage.addon.Addon;
import net.mcft.copy.betterstorage.api.crafting.BetterStorageCrafting;
import net.mcft.copy.betterstorage.content.BetterStorageItems;
import net.mcft.copy.betterstorage.content.BetterStorageTiles;
import net.mcft.copy.betterstorage.item.cardboard.CardboardEnchantmentRecipe;
import net.mcft.copy.betterstorage.item.cardboard.CardboardRepairRecipe;
import net.mcft.copy.betterstorage.item.recipe.DrinkingHelmetRecipe;
import net.mcft.copy.betterstorage.item.recipe.DyeRecipe;
import net.mcft.copy.betterstorage.item.recipe.KeyRecipe;
import net.mcft.copy.betterstorage.item.recipe.LockColorRecipe;
import net.mcft.copy.betterstorage.item.recipe.LockRecipe;
import net.mcft.copy.betterstorage.item.recipe.PresentRecipe;
import net.mcft.copy.betterstorage.item.recipe.PresentRemoveNametagRecipe;
import net.mcft.copy.betterstorage.tile.ContainerMaterial;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import com.bioxx.tfc.api.TFCItems;

import java.util.Map;
import java.util.Random;


public final class Recipes {

	private static final String RingPlan = "ring";
	//private static final String KeyPlan = "key";
	//private static final String LockPlan = "lock";
	
	private Recipes() {  }
	
	public static void add() {
		
		registerRecipeSorter();
		
		addTileRecipes();
		addItemRecipes();
		addCardboardRecipes();
	//	areAnvilRecipesRegistered();

		GameRegistry.addRecipe(new DyeRecipe());
		Addon.addRecipesAll();
		
	}
	
	private static void registerRecipeSorter() {
		
		RecipeSorter.register("betterstorage:drinkinghelmetrecipe", DrinkingHelmetRecipe.class, Category.SHAPED,    "");
		RecipeSorter.register("betterstorage:keyrecipe",            KeyRecipe.class,            Category.SHAPED,    "");
		RecipeSorter.register("betterstorage:lockrecipe",           LockRecipe.class,           Category.SHAPED,    "");
		
		RecipeSorter.register("betterstorage:dyerecipe",       DyeRecipe.class,       Category.SHAPELESS, "");
		RecipeSorter.register("betterstorage:lockcolorrecipe", LockColorRecipe.class, Category.SHAPELESS, "");
		
	}
	
	private static void addTileRecipes() {
		
		// Crate recipe
		if (BetterStorageTiles.crate != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.crate),
					"o/o",
					"/ /",
					"o/o", 'o', "plankWood",
					       '/', "stickWood"));
		
		// Reinforced chest recipes
		if (BetterStorageTiles.reinforcedChest != null)
			for (ContainerMaterial material : ContainerMaterial.getMaterials()) {
				IRecipe recipe = material.getReinforcedChestRecipe("chestWood", BetterStorageTiles.reinforcedChest);
				if (recipe != null) GameRegistry.addRecipe(recipe);
			}
		
		// Locker recipe
		if (BetterStorageTiles.locker != null) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.locker),
					"ooo",
					"o |",
					"ooo", 'o', "plankWood",
					       '|', Blocks.trapdoor));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.locker),
					"ooo",
					"| o",
					"ooo", 'o', "plankWood",
					'|', Blocks.trapdoor));
			
			// Reinforced locker recipes
			if (BetterStorageTiles.reinforcedLocker != null)
				for (ContainerMaterial material : ContainerMaterial.getMaterials()) {
					IRecipe recipe = material.getReinforcedLockerRecipe(BetterStorageTiles.locker, BetterStorageTiles.reinforcedLocker);
					if (recipe != null) GameRegistry.addRecipe(recipe);
				}
		}
		
		// Armor stand recipe
		if (BetterStorageTiles.armorStand != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.armorStand),
					" i ",
					"/i/",
					" s ", 's', "stoneSmooth",
					'i', "ingotIron",
					'/', "stickWood"));
		
		// Backpack recipe
		if (BetterStorageTiles.backpack != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.itemBackpack),
					"#i#",
					"#O#",
					"###", '#', "materialLeather",
					'O', "materialCloth",
					'i', "ingotSteel"));
		
		// Cardboard box recipe
		if ((BetterStorageTiles.cardboardBox != null) &&
		    (BetterStorageItems.cardboardSheet != null))
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.cardboardBox),
					"ooo",
					"o o",
					"ooo", 'o', "sheetCardboard"));
		
		// Crafting Station recipe
		if (BetterStorageTiles.craftingStation != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageTiles.craftingStation),
					"B-B",
					"PTP",
					"WCW", 'B', "plateSteel",
					       '-', Blocks.light_weighted_pressure_plate,
					       'P', Blocks.piston,
					       'T', "craftingTableWood",
					       'W', "plankWood",
					       'C', "chestWood"));
		
		// Present recipe
		if ((BetterStorageTiles.present != null) &&
		    (BetterStorageTiles.cardboardBox != null)) {
			GameRegistry.addRecipe(new PresentRecipe());
			BetterStorageCrafting.addStationRecipe(new PresentRemoveNametagRecipe());
		}
		
		// Flint Block recipe
		if (BetterStorageTiles.flintBlock != null) {
			GameRegistry.addShapedRecipe(new ItemStack(BetterStorageTiles.flintBlock),
					"ooo",
					"ooo",
					"ooo", 'o', Items.flint);
			GameRegistry.addShapelessRecipe(new ItemStack(Items.flint, 9), BetterStorageTiles.flintBlock);
		}
		
	}

	private static void addItemRecipes() {
		
		if (BetterStorageItems.key != null) {
			// Key recipe
			// TODO: Add support for ore dictionary gold ingots / nuggets.
			GameRegistry.addRecipe(KeyRecipe.createKeyRecipe(
					".o",
					" o",
					" r", 'o', TFCItems.goldIngot,
					      '.', TFCItems.goldSheet,
					      'r', BetterStorageItems.keyring));
			// Key modify recipe
			GameRegistry.addRecipe(KeyRecipe.createKeyRecipe(
					"k", 'k', new ItemStack(BetterStorageItems.key)));
		}
		
		if (BetterStorageItems.lock != null) {
			// Lock recipe
			if (BetterStorageItems.key != null)
				GameRegistry.addRecipe(LockRecipe.createLockRecipe());
			// Lock color recipe
			GameRegistry.addRecipe(LockColorRecipe.createLockColorRecipe());
		}

		//Keyring recipe
		//if (BetterStorageItems.keyring != null)
		//	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.keyring),
		//			"...",
		//			". .",
		//			"...", '.', "nuggetGold"));

		// Drinking helmet recipe
		if (BetterStorageItems.drinkingHelmet != null)
			GameRegistry.addRecipe(new DrinkingHelmetRecipe(BetterStorageItems.drinkingHelmet));
		
	}
	
	private static void addCardboardRecipes() {
		
		// Cardboard sheet recipe
		if (BetterStorageItems.cardboardSheet != null) {
			GameRegistry.addShapelessRecipe(new ItemStack(BetterStorageItems.cardboardSheet, 4),
					Items.paper, Items.paper, Items.paper,
					Items.paper, Items.paper, Items.paper,
					Items.paper, Items.paper, Items.slime_ball);
		}
		
		// Cardboard helmet recipe
		if (BetterStorageItems.cardboardHelmet != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardHelmet),
					"ooo",
					"o o", 'o', "sheetCardboard"));
		// Cardboard chestplate recipe
		if (BetterStorageItems.cardboardChestplate != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardChestplate),
					"o o",
					"ooo",
					"ooo", 'o', "sheetCardboard"));
		// Cardboard leggings recipe
		if (BetterStorageItems.cardboardLeggings != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardLeggings),
					"ooo",
					"o o",
					"o o", 'o', "sheetCardboard"));
		// Cardboard boots recipe
		if (BetterStorageItems.cardboardBoots != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardBoots),
					"o o",
					"o o", 'o', "sheetCardboard"));

		// Cardboard sword recipe
		if (BetterStorageItems.cardboardSword != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardSword),
					"o",
					"o",
					"/", 'o', "sheetCardboard",
					     '/', "stickWood"));
		// Cardboard pickaxe recipe
		if (BetterStorageItems.cardboardPickaxe != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardPickaxe),
					"ooo",
					" / ",
					" / ", 'o', "sheetCardboard",
					       '/', "stickWood"));
		// Cardboard shovel recipe
		if (BetterStorageItems.cardboardShovel != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardShovel),
					"o",
					"/",
					"/", 'o', "sheetCardboard",
					     '/', "stickWood"));
		
		// Cardboard axe recipe
		if (BetterStorageItems.cardboardAxe != null) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardAxe),
					"oo",
					"o/",
					" /", 'o', "sheetCardboard",
					      '/', "stickWood"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardAxe),
					"oo",
					"/o",
					"/ ", 'o', "sheetCardboard",
					      '/', "stickWood"));
		}
		
		// Cardboard hoe recipe
		if (BetterStorageItems.cardboardHoe != null) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardHoe),
					"oo",
					" /",
					" /", 'o', "sheetCardboard",
					      '/', "stickWood"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BetterStorageItems.cardboardHoe),
					"oo",
					"/ ",
					"/ ", 'o', "sheetCardboard",
					      '/', "stickWood"));
		}
		
		if (BetterStorageItems.anyCardboardItemsEnabled) {
			// Crafting Station: Add cardboard enchantment recipe
			BetterStorageCrafting.addStationRecipe(new CardboardEnchantmentRecipe());
			
			// Crafting Station: Add cardboard repair recipe
			if (BetterStorageItems.cardboardSheet != null)
				BetterStorageCrafting.addStationRecipe(new CardboardRepairRecipe());
		}
		
	}
	public static boolean areAnvilRecipesRegistered()
	{
		Map map = AnvilManager.getInstance().getPlans();

		return map.containsKey(RingPlan);
	}

	public static void registerAnvilRecipes(World world) {
		AnvilManager manager = AnvilManager.getInstance();
		//We need to set the world ref so that all anvil recipes can generate correctly
		AnvilManager.world = world;

		manager.addPlan(RingPlan, new PlanRecipe(new RuleEnum[]{RuleEnum.PUNCHLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.PUNCHTHIRDFROMLAST}));
	//	manager.addPlan(KeyPlan, new PlanRecipe(new RuleEnum[]{RuleEnum.HITLAST, RuleEnum.PUNCHNOTLAST, RuleEnum.BENDNOTLAST}));
	//	manager.addPlan(LockPlan, new PlanRecipe(new RuleEnum[]{RuleEnum.BENDLAST, RuleEnum.SHRINKNOTLAST, RuleEnum.PUNCHNOTLAST}));

		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet), null, "ring", false, AnvilReq.BRONZE, new ItemStack(BetterStorageItems.keyring, 4)));
	//	manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet), null, "key", false, AnvilReq.BRONZE, new ItemStack(BetterStorageItems.key)));
	//	manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet2x), new ItemStack(TFCItems.wroughtIronIngot), "lock", false, AnvilReq.BRONZE, new ItemStack(BetterStorageItems.lock)));
	}
	
}
