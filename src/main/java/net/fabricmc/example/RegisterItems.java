package net.fabricmc.example;

import net.fabricmc.example.materials.ObsidianArmorMaterial;
import net.fabricmc.example.first_item.ObsidianIngot;
import net.fabricmc.example.materials.ObsidianToolMaterial;
import net.fabricmc.example.tools.CustomAxeItem;
import net.fabricmc.example.tools.CustomHoeItem;
import net.fabricmc.example.tools.CustomPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
    public static final ArmorMaterial OBSIDIAN_ARMOR_MATERIAL = new ObsidianArmorMaterial();
    public static final ObsidianIngot OBSIDIAN_INGOT = new ObsidianIngot(new FabricItemSettings().group(ExampleMod.OBSIDIAN_GROUP));

    public static final Item OBSIDIAN_HELMET = new ArmorItem(OBSIDIAN_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static final Item OBSIDIAN_CHESTPLATE = new ArmorItem(OBSIDIAN_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static final Item OBSIDIAN_LEGGINGS = new ArmorItem(OBSIDIAN_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static final Item OBSIDIAN_BOOTS = new ArmorItem(OBSIDIAN_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));

    //TODO: give these guys some real dmg + speed nums
    public static ToolItem OBSIDIAN_SHOVEL = new ShovelItem(ObsidianToolMaterial.INSTANCE, 50, 3.0F, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static ToolItem OBSIDIAN_SWORD = new SwordItem(ObsidianToolMaterial.INSTANCE, 50, 3.0F, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    //TODO: give these guys recipes
    public static ToolItem OBSIDIAN_PICKAXE = new CustomPickaxeItem(ObsidianToolMaterial.INSTANCE, 50, 3.0F, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static ToolItem OBSIDIAN_AXE = new CustomAxeItem(ObsidianToolMaterial.INSTANCE, 50, 3.0F, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));
    public static ToolItem OBSIDIAN_HOE = new CustomHoeItem(ObsidianToolMaterial.INSTANCE, 50, 3.0F, new Item.Settings().group(ExampleMod.OBSIDIAN_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_ingot"), OBSIDIAN_INGOT);

        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_helmet"), OBSIDIAN_HELMET);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_chestplate"), OBSIDIAN_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_leggings"), OBSIDIAN_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_boots"), OBSIDIAN_BOOTS);

        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_shovel"), OBSIDIAN_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_sword"), OBSIDIAN_SWORD);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_pickaxe"), OBSIDIAN_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_axe"), OBSIDIAN_AXE);
        Registry.register(Registry.ITEM, new Identifier("modid", "obsidian_hoe"), OBSIDIAN_HOE);
    }
}
