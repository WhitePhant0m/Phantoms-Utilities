package dev.wp.phantoms_utilities.datagen.providers.server;

import dev.wp.phantoms_utilities.PUTags;
import dev.wp.phantoms_utilities.PhantomsUtilities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

public class PUBlockTags extends BlockTagsProvider {
    public PUBlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), PhantomsUtilities.ID, event.getExistingFileHelper());
    }

    private final List<String> xyColors = List.of("blue", "green", "red", "dark", "light");
    private final List<String> xyVariants = List.of(
            "kivi_pillar",
            "kivi_trim",
            "immortal_stone",
            "aluminum_pillar",
            "aluminum_trim",
            "aluminum_immortal",
            "matte_xychorium_bricks",
            "matte_xychorium_bricks_shiny",
            "matte_xychorium_layers_shiny"
    );

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PUTags.Blocks.SPRAY_CAN_BLACKLIST)
                .addOptionalTag(key("occultism:chalk_glyphs"))
                .addOptionalTag(key("xycraft:colored_clouds"))
                .addOptional(ResourceLocation.parse("xycraft_world:aurey_block_matte_pink"))
                .addOptional(ResourceLocation.parse("xycraft_world:aurey_block_matte_glowing_pink"));

        for (String xyColor : xyColors) {
            for (String xyVariant : xyVariants) {
                tag(PUTags.Blocks.SPRAY_CAN_BLACKLIST)
                        .addOptional(ResourceLocation.parse("xycraft_world:" + xyVariant + "_" + xyColor));
            }
        }
    }

    private static TagKey<Block> key(ResourceLocation id) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), id);
    }

    private static TagKey<Block> key(String id) {
        return key(ResourceLocation.parse(id));
    }
}
