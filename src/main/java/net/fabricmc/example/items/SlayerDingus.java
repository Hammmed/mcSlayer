package net.fabricmc.example.items;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.fabricmc.example.RegisterItems;
import net.fabricmc.example.extensions.PlayerEntityExt;
import net.fabricmc.example.gui.SampleGuiItemDescription;
import net.fabricmc.example.gui.SlayerScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Random;

public class SlayerDingus extends Item {


    public SlayerDingus(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            PlayerEntityExt serverPlayer = (PlayerEntityExt) user;

            if (serverPlayer.getSlayerTaskCount() <= 0) {
                serverPlayer.setRandomTask();
            }

            MinecraftClient.getInstance().openScreen(new SlayerScreen(new SampleGuiItemDescription(serverPlayer)));
        }

        return super.use(world, user, hand);
    }

}
