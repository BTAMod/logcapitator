package bigjango.logcapitator.mixins;

import bigjango.logcapitator.Logcapitator;

import net.minecraft.core.world.World;
import net.minecraft.core.block.BlockAxisAligned;
import net.minecraft.core.block.BlockLog;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockAxisAligned.class, remap = false)
public class BlockAxisAlignedMixin {
    @Redirect(
        method = "onBlockPlaced",
        at = @At(value = "INVOKE", target = "setBlockMetadataWithNotify")
    )
    public void setBlockMetaAA(World w, int x, int y, int z, int meta) {
        if (Logcapitator.reverse && ((Object) this instanceof BlockLog)) {
            w.setBlockMetadataWithNotify(x, y, z, meta | 1 << 2);
        }
    }

}