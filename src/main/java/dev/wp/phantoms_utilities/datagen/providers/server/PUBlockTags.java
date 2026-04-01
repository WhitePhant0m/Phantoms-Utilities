package dev.wp.phantoms_utilities.datagen.providers.server;

import dev.wp.phantoms_utilities.PUTags;
import dev.wp.phantoms_utilities.PhantomsUtilities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class PUBlockTags extends BlockTagsProvider {
    public PUBlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), PhantomsUtilities.ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PUTags.Blocks.SPRAY_CAN_BLACKLIST)
                .addOptionalTag(key("occultism:chalk_glyphs"));
    }

    private static TagKey<Block> key(ResourceLocation id) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), id);
    }

    private static TagKey<Block> key(String id) {
        return key(ResourceLocation.parse(id));
    }
}
