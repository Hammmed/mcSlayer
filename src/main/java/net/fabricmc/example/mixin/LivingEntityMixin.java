package net.fabricmc.example.mixin;

import net.fabricmc.example.RegisterItems;
import net.fabricmc.example.events.AttackEntityCallback;
import net.fabricmc.example.extensions.PlayerEntityExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.security.auth.callback.Callback;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    // This is where we check whether an task related entity was killed and adjust task vars in the player
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();

            if(attacker instanceof PlayerEntity) {
                PlayerEntityExt currPlayer = (PlayerEntityExt) attacker;

//                ((PlayerEntity) currPlayer).sendMessage(new LiteralText("Currtask " + currPlayer.getSlayerTaskCount() + " " + currPlayer.getSlayerTask()), false);

                if (this.getType().toString().equals("entity.minecraft." + currPlayer.getSlayerTask())) {
//                    System.out.println("You've killed ur task");
                    currPlayer.reduceTaskCount(1);

                    if (currPlayer.getSlayerTaskCount() <= 0) {
                        currPlayer.increaseSlayerPoints(15);

                        ((PlayerEntity) currPlayer).sendMessage(new LiteralText("You've completed your task, you now have " + currPlayer.getSlayerPoints() + " points"), false);
                        ((PlayerEntityExt) currPlayer).setSlayerTask("");


                        for (ItemStack item : ((PlayerEntity) currPlayer).inventory.main) {
                            if (item.getItem() == RegisterItems.SLAYER_DINGUS) {
                                item.decrement(1);
                                break;
                            }
                        }
                    }
                }

            }

    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();

            if(attacker instanceof PlayerEntity) {
                PlayerEntityExt currPlayer = (PlayerEntityExt) attacker;

                // slayer helm functionality - multiply damage dealt by player wearing it by 1.5
                if (this.getType().toString().equals("entity.minecraft." + currPlayer.getSlayerTask())) {
                    if (((PlayerEntity) attacker).inventory.armor.get(3).toString().equals("1 obsidian_helmet")) {
                        amount = amount * 1.5F;
                    }
                }
        }
    }
}
