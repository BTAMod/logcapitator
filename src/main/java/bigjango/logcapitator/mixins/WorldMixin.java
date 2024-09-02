package bigjango.logcapitator.mixins;

import bigjango.logcapitator.Logcapitator;

import net.minecraft.core.world.World;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class, remap = false)
public class WorldMixin {
    boolean isLog(int id) {
        return Block.getBlock(id) instanceof BlockLog;
    }

    @Inject(method = "setBlock", at = @At("TAIL"))
    public void setBlockWrap(int x, int y, int z, int id, CallbackInfoReturnable<Boolean> cir) {
        if (Logcapitator.inTreeGen && isLog(id)) {
            ((World) (Object) this).setBlockMetadata(x, y, z, 1 << 2);
        }
    }

    @Inject(method = "setBlockWithNotify", at = @At("TAIL"))
    public void setBlockWNWrap(int x, int y, int z, int id, CallbackInfoReturnable<Boolean> cir) {
        if (Logcapitator.inTreeGen && isLog(id)) {
            ((World) (Object) this).setBlockMetadataWithNotify(x, y, z, 1 << 2);
        }
    }

    @Inject(method = "setBlockAndMetadata", at = @At("TAIL"))
    public void setBlockAndMeta(int x, int y, int z, int id, int meta, CallbackInfoReturnable<Boolean> cir) {
        if (Logcapitator.inTreeGen && isLog(id)) {
            ((World) (Object) this).setBlockMetadata(x, y, z, meta | 1 << 2);
        }
    }

    @Inject(method = "setBlockAndMetadataWithNotify", at = @At("TAIL"))
    public void setBlockAndMetaWN(int x, int y, int z, int id, int meta, CallbackInfoReturnable<Boolean> cir) {
        if (Logcapitator.inTreeGen && isLog(id)) {
            ((World) (Object) this).setBlockMetadataWithNotify(x, y, z, meta | 1 << 2);
        }
    }

    // Random tick that could grow a sapling (yeah, this is a huge hack, but I'd rather not write 13+ new mixins)
    @Inject(method = "updateBlocksAndPlayCaveSounds", at = @At("HEAD"))
    protected void updateBlocksAndPlayCaveSounds_H(CallbackInfo ci) {
        Logcapitator.inTreeGen = true;
    }
    @Inject(method = "updateBlocksAndPlayCaveSounds", at = @At("TAIL"))
    protected void updateBlocksAndPlayCaveSounds_T(CallbackInfo ci) {
        Logcapitator.inTreeGen = false;
    }
}
