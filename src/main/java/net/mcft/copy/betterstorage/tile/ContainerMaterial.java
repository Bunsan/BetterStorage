package net.mcft.copy.betterstorage.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcft.copy.betterstorage.misc.BetterStorageResource;
import net.mcft.copy.betterstorage.utils.StackUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ContainerMaterial {
	
	public static final String TAG_NAME = "Material";
	
	private static Map<String, ContainerMaterial> materialMap = new HashMap<String, ContainerMaterial>();
	private static Map<Integer, ContainerMaterial> materialMapOld = new HashMap<Integer, ContainerMaterial>();
	private static List<ContainerMaterial> materials = new ArrayList<ContainerMaterial>();
	
	// Vanilla materials

	public static ContainerMaterial diamond = new ContainerMaterial(2, "diamond", "craftingBlueGem");
	public static ContainerMaterial emerald = new ContainerMaterial(3, "emerald", "craftingGreenGem");
	public static ContainerMaterial ruby = new ContainerMaterial(3, "ruby", "craftingRedGem");
	public static ContainerMaterial iron    = new ContainerMaterial(0, "iron",    "nuggetIron");
	public static ContainerMaterial gold    = new ContainerMaterial(1, "gold",    "nuggetGold");
	public static ContainerMaterial copper = new ContainerMaterial(5, "copper", "nuggetCopper");
	public static ContainerMaterial tin    = new ContainerMaterial(6, "tin",    "nuggetTin");
	public static ContainerMaterial silver = new ContainerMaterial(7, "silver", "nuggetSilver");
	public static ContainerMaterial zinc   = new ContainerMaterial(8, "zinc",   "nuggetZinc");
	public static ContainerMaterial steel  = new ContainerMaterial(   "steel",  "nuggetSteel");
	public static ContainerMaterial bismuth  = new ContainerMaterial(   "bismuth",  "nuggetBismuth");
	public static ContainerMaterial bismuthbronze  = new ContainerMaterial(   "bismuthbronze",  "nuggetBismuthBronze");
	public static ContainerMaterial blackbronze  = new ContainerMaterial(   "blackbronze",  "nuggetBlackBronze");
	public static ContainerMaterial blacksteel  = new ContainerMaterial(   "blacksteel",  "nuggetBlackSteel");
	public static ContainerMaterial bluesteel  = new ContainerMaterial(   "bluesteel",  "nuggetBlueSteel");
	public static ContainerMaterial brass  = new ContainerMaterial(   "brass",  "nuggetBrass");
	public static ContainerMaterial bronze  = new ContainerMaterial(   "bronze",  "nuggetBronze");
	public static ContainerMaterial lead  = new ContainerMaterial(   "lead",  "nuggetLead");
	public static ContainerMaterial nickel  = new ContainerMaterial(   "nickel",  "nuggetNickel");
	public static ContainerMaterial pigiron  = new ContainerMaterial(   "pigiron",  "nuggetPigIron");
	public static ContainerMaterial redsteel  = new ContainerMaterial(   "redsteel",  "nuggetRedSteel");
	public static ContainerMaterial rosegold  = new ContainerMaterial(   "rosegold",  "nuggetRoseGold");
	public static ContainerMaterial platinum  = new ContainerMaterial(   "platinum",  "nuggetPlatinum");
	public static ContainerMaterial sterlingsilver  = new ContainerMaterial(   "sterlingsilver",  "nuggetSterlingSilver");



	public static List<ContainerMaterial> getMaterials() { return materials; }
	
	public static ContainerMaterial get(String name) { return materialMap.get(name); }
	public static ContainerMaterial get(int id) { return materialMapOld.get(id); }
	
	/** Gets the material of the stack, either using the new method, the
	 *  old ID lookup or if everything fails, it'll return the default. */
	public static ContainerMaterial getMaterial(ItemStack stack, ContainerMaterial _default) {
		String name = StackUtils.get(stack, (String)null, TAG_NAME);
		ContainerMaterial material = ((name != null) ? get(name) : get(stack.getItemDamage()));
		return ((material != null) ? material : _default);
	}
	
	
	public final String name;
	
	private final Object nugget;

	private ContainerMaterial(String name, Object nugget) {
		this.name = name;
		this.nugget = nugget;
		materialMap.put(name, this);
		materials.add(this);
	}
	private ContainerMaterial(String name) { this(name, null); }
	
	private ContainerMaterial(int id, String name, Object nugget) {
		this(name, nugget);
		materialMapOld.put(id, this);
	}
	
	public ShapedOreRecipe getReinforcedChestRecipe(String middle, Block result) {
		if (nugget == null) return null;
		return new ShapedOreRecipe(setMaterial(new ItemStack(result)),
				"oOo",
				"#C#",
				"oSo", 'o', nugget,
				       'O', "plateSteel",
				       'C', middle,
				       '#', "logWood",
				       'S', "craftingToolHardSaw");
	}

	public ShapedOreRecipe getReinforcedLockerRecipe(Block middle, Block result) {
		if (nugget == null) return null;
		return new ShapedOreRecipe(setMaterial(new ItemStack(result)),
				"oOo",
				"#C#",
				"oSo", 'o', nugget,
				'O', "plateSteel",
				'C', middle,
				'#', "logWood",
				'S', "craftingToolHardSaw");
	}

	public ResourceLocation getChestResource(boolean large) {
		return new BetterStorageResource("textures/models/chest" + (large ? "_large/" : "/") + name + ".png");
	}
	public ResourceLocation getLockerResource(boolean large) {
		return new BetterStorageResource("textures/models/locker" + (large ? "_large/" : "/") + name + ".png");
	}
	
	public ItemStack setMaterial(ItemStack stack) {
		StackUtils.set(stack, name, TAG_NAME);
		return stack;
	}
	
}
