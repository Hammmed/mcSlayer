package net.fabricmc.example.mixin;

import net.fabricmc.example.extensions.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin  extends LivingEntity implements PlayerEntityExt {

    private String slayerTask;
    private int slayerTaskCount;
    private int slayerPoints;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);

        this.slayerTaskCount = 0;
        this.slayerPoints = 0;
    }

    public void reduceTaskCount(int amt) {
        this.slayerTaskCount-= amt;
    }

    public void increaseSlayerPoints(int amt) {
        this.slayerPoints+= amt;
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
