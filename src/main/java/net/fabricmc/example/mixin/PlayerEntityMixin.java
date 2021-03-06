package net.fabricmc.example.mixin;

import net.fabricmc.example.RegisterItems;
import net.fabricmc.example.extensions.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {

    private static final String[] taskListArr = {"chicken", "cow", "sheep", "zombie", "skeleton", "spider"};

    @Shadow @Final public PlayerInventory inventory;
    private String slayerTask;
    private int slayerTaskCount;
    private int slayerPoints;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);

        this.slayerTaskCount = 0;
        this.slayerPoints = 0;
    }

    public void reduceTaskCount(int amt) {

        if (this.getSlayerTaskCount() > 0) {
            this.slayerTaskCount-= amt;
        }
    }

    public void increaseSlayerPoints(int amt) {
        this.slayerPoints+= amt;
    }

    public void reduceSlayerPoints(int amt) {
        this.slayerPoints-= amt;
    }

    public int getSlayerTaskCount() {
        return slayerTaskCount;
    }

    public void setSlayerTaskCount(int taskCount) {
        this.slayerTaskCount = taskCount;
    }

    public String getSlayerTask() {
        return slayerTask;
    }

    public void setSlayerTask(String task) {
        this.slayerTask = task;
    }

    public int getSlayerPoints() {
        return slayerPoints;
    }

    public void setRandomTask() {
        this.setSlayerTask(getRandomString(taskListArr));
        this.setSlayerTaskCount(((int) (Math.random() * (10 - 1)) + 1));
    }

    public String getRandomString(String[] arr) {
        return arr[(new Random()).nextInt(arr.length)];
    }

    @Inject(method = "writeCustomDataToTag", at = @At("RETURN"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        tag.putString("slayerTask", this.slayerTask);
        tag.putInt("slayerTaskCount", this.slayerTaskCount);
        tag.putInt("slayerPoints", this.slayerPoints);
    }

    @Inject(method = "readCustomDataFromTag", at = @At("RETURN"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        this.slayerTask = tag.getString("slayerTask");
        this.slayerTaskCount = tag.getInt("slayerTaskCount");
        this.slayerPoints = tag.getInt("slayerPoints");
    }
}
