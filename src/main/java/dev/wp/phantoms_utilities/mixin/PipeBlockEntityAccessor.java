package dev.wp.phantoms_utilities.mixin;

import aztech.modern_industrialization.pipes.api.PipeNetworkNode;
import aztech.modern_industrialization.pipes.impl.PipeBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.SortedSet;

@Mixin(PipeBlockEntity.class)
public interface PipeBlockEntityAccessor {
    @Accessor(remap = false)
    SortedSet<PipeNetworkNode> getPipes();
}
