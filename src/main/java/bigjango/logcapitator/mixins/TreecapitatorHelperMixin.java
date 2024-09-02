package bigjango.logcapitator.mixins;

import bigjango.logcapitator.Logcapitator;

import net.minecraft.core.data.gamerule.TreecapitatorHelper;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.block.BlockLog;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TreecapitatorHelper.class, remap = false)
public class TreecapitatorHelperMixin {
    private int getBlockWrapper(World world, int x, int y, int z) {
        int id = world.getBlockId(x, y, z);
        Block b = Block.getBlock(id);
        if (b instanceof BlockLog) {
            int meta = world.getBlockMetadata(x, y, z);
            meta >>= 2;
            if (Logcapitator.reverse == false) {
                return (meta == 0 ? 0 : id);
            } else {
                return (meta == 0 ? id : 0);
            }
        }
        return id;
    }

    @Redirect(
        method = "chopTree",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/world/World;getBlockId"
        )
    )
    private int getBlockId_chopTree(World w, int x, int y, int z) {
        return getBlockWrapper(w, x, y, z);
    }

    @Redirect(
        method = "isLogInRange",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/world/World;getBlock"
        )
    )
    private Block getBlock_isLogInRange(World w, int x, int y, int z) {
        return Block.getBlock(getBlockWrapper(w, x, y, z));
    }

    @Redirect(
        method = "isConnectedToLog",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/world/World;getBlock"
        )
    )
    private Block getBlock_isConnectedToLog(World w, int x, int y, int z) {
        return Block.getBlock(getBlockWrapper(w, x, y, z));
    }
}
