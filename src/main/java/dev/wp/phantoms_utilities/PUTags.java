package dev.wp.phantoms_utilities;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class PUTags {
    private final static Map<TagKey<Item>, String> TRANSLATIONS = Maps.newHashMap();

    public final static class Blocks {
        public final static TagKey<Block> SPRAY_CAN_BLACKLIST = block("spray_can_blacklist");
    }

    public static TagKey<Item> item(String path, String englishName) {
        TagKey<Item> tag = TagKey.create(BuiltInRegistries.ITEM.key(), PhantomsUtilities.id(path));
        TRANSLATIONS.put(tag, englishName);
        return tag;
    }

    public static TagKey<Item> itemCommon(String path) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Block> block(String path) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), PhantomsUtilities.id(path));
    }

    public static TagKey<Block> blockCommon(String path) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("c", path));
    }
}
