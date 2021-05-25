package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.networking.v1.S2CPlayChannelEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;


import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class ExampleMod implements ModInitializer {

	public static final ItemGroup OBSIDIAN_GROUP = FabricItemGroupBuilder.build(
			new Identifier("modid", "obsidian_group"),
			() -> new ItemStack(Blocks.OBSIDIAN));

	private static final List<Identifier> ENTITY_LIST = new ArrayList<>();



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");

		RegisterItems.register();

		ENTITY_LIST.add(new Identifier("minecraft", "entities/sheep"));
		ENTITY_LIST.add(new Identifier("minecraft", "entities/chicken"));
		ENTITY_LIST.add(new Identifier("minecraft", "entities/cow"));
		ENTITY_LIST.add(new Identifier("minecraft", "entities/zombie"));
		ENTITY_LIST.add(new Identifier("minecraft", "entities/skeleton"));
		ENTITY_LIST.add(new Identifier("minecraft", "entities/spider"));

		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			for ( Identifier entity : ENTITY_LIST) {
				if(entity.equals(id))
				{
					FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
							.rolls(ConstantLootTableRange.create(1))
							.withEntry(ItemEntry.builder(RegisterItems.SLAYER_DINGUS).build());
					supplier.withPool(poolBuilder.build());
				}
			}
		});
	}
}
