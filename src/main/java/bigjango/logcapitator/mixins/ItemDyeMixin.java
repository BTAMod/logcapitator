package bigjango.logcapitator.mixins;

import bigjango.logcapitator.Logcapitator;

import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.core.util.helper.Side;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemDye.class, remap = false)
public class ItemDyeMixin {
    @Inject(method = "onUseItemOnBlock", at = @At("HEAD"))
    public final void onUseItemOnBlockH(
        ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced,
        CallbackInfoReturnable<Boolean> ci
    ) {
        Logcapitator.inTreeGen = true;
    }

    @Inject(method = "onUseItemOnBlock", at = @At("TAIL"))
    public final void onUseItemOnBlockT(
        ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced,
        CallbackInfoReturnable<Boolean> ci
    ) {
        Logcapitator.inTreeGen = false;
    }
}
