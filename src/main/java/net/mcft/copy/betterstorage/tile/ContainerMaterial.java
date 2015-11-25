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
	public static ContainerMaterial diamond = new ContainerMaterial(2, "diamond", "gemDiamond");
	public static ContainerMaterial emerald = new ContainerMaterial(3, "emerald", "gemEmerald");
	public static ContainerMaterial ruby = new ContainerMaterial(3, "ruby", "gemRuby");
	public static ContainerMaterial iron    = new ContainerMaterial(0, "iron",    "plateIron");
	public static ContainerMaterial gold    = new ContainerMaterial(1, "gold",    "plateGold");
	public static ContainerMaterial copper = new ContainerMaterial(5, "copper", "plateCopper");
	public static ContainerMaterial tin    = new ContainerMaterial(6, "tin",    "plateTin");
	public static ContainerMaterial silver = new ContainerMaterial(7, "silver", "plateSilver");
	public static ContainerMaterial zinc   = new ContainerMaterial(8, "zinc",   "plateZinc");
	public static ContainerMaterial steel  = new ContainerMaterial(   "steel",  "plateSteel");
	public static ContainerMaterial bismuth  = new ContainerMaterial(   "bismuth",  "plateBismuth");
	public static ContainerMaterial bismuthbronze  = new ContainerMaterial(   "bismuthbronze",  "plateBismuthBronze");
	public static ContainerMaterial blackbronze  = new ContainerMaterial(   "blackbronze",  "plateBlackBronze");
	public static ContainerMaterial blacksteel  = new ContainerMaterial(   "blacksteel",  "plateBlackSteel");
	public static ContainerMaterial bluesteel  = new ContainerMaterial(   "bluesteel",  "plateBlueSteel");
	public static ContainerMaterial brass  = new ContainerMaterial(   "brass",  "plateBrass");
	public static ContainerMaterial bronze  = new ContainerMaterial(   "bronze",  "plateBronze");
	public static ContainerMaterial lead  = new ContainerMaterial(   "lead",  "plateLead");
	public static ContainerMaterial nickel  = new ContainerMaterial(   "nickel",  "plateNickel");
	public static ContainerMaterial pigiron  = new ContainerMaterial(   "pigiron",  "platePigIron");
	public static ContainerMaterial redsteel  = new ContainerMaterial(   "redsteel",  "plateRedSteel");
	public static ContainerMaterial rosegold  = new ContainerMaterial(   "rosegold",  "plateRoseGold");
	public static ContainerMaterial platinum  = new ContainerMaterial(   "platinum",  "platePlatinum");
	public static ContainerMaterial sterlingsilver  = new ContainerMaterial(   "sterlingsilver",  "plateSterlingSilver");



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
	
	private final Object sheet;

	private ContainerMaterial(String name, Object sheet) {
		this.name = name;
		this.sheet = sheet;
		materialMap.put(name, this);
		materials.add(this);
	}
	private ContainerMaterial(String name) { this(name, null); }
	
	private ContainerMaterial(int id, String name, Object sheet) {
		this(name, sheet);
		materialMapOld.put(id, this);
	}
	
	public ShapedOreRecipe getReinforcedChestRecipe(String middle, Block result) {
		if (sheet == null) return null;
		return new ShapedOreRecipe(setMaterial(new ItemStack(result)),
				"oSo",
				"#C#",
				"oOo", 'C', middle,
				       '#', "logWood",
				       'o', "woodLumber",
				       'S', sheet,
				       'O', "plateSteel");
	}

	public ShapedOreRecipe getReinforcedLockerRecipe(Block middle, Block result) {
		if (sheet == null) return null;
		return new ShapedOreRecipe(setMaterial(new ItemStack(result)),
				"oSo",
				"#C#",
				"oOo", 'C', middle,
				'#', "logWood",
				'o', "woodLumber",
				'S', sheet,
				'O', "plateSteel");
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
