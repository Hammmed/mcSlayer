package net.fabricmc.example.block;

import net.fabricmc.example.extensions.PlayerEntityExt;
import net.fabricmc.example.gui.SampleGuiItemDescription;
import net.fabricmc.example.gui.SlayerScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Merchant;
import net.minecraft.world.World;

import java.util.Random;

public class ObsidianBlock extends Block {

    public ObsidianBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            MinecraftClient.getInstance().openScreen(new SlayerScreen(new SampleGuiItemDescription((PlayerEntityExt) player)));
        }

        return ActionResult.SUCCESS;
    }
}
