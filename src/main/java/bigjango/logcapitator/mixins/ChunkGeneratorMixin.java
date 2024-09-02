package bigjango.logcapitator.mixins;

import bigjango.logcapitator.Logcapitator;

import net.minecraft.core.world.generate.chunk.ChunkGenerator;
import net.minecraft.core.world.chunk.Chunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChunkGenerator.class, remap = false)
public class ChunkGeneratorMixin {
    @Inject(method = "decorate", at = @At("HEAD"))
    public final void decorateH(Chunk chunk, CallbackInfo ci) {
        Logcapitator.inTreeGen = true;
    }

    @Inject(method = "decorate", at = @At("TAIL"))
    public final void decorateT(Chunk chunk, CallbackInfo ci) {
        Logcapitator.inTreeGen = false;
    }
}
