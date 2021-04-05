package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.first_item.ObsidianIngot;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final ItemGroup OBSIDIAN_GROUP = FabricItemGroupBuilder.build(
			new Identifier("modid", "obsidian_group"),
			() -> new ItemStack(Blocks.OBSIDIAN));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");

		RegisterItems.register();
	}
}
