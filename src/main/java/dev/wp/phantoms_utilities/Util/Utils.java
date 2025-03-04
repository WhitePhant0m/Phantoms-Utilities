package dev.wp.phantoms_utilities.Util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Set;
//import java.util.function.BiFunction;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Utils {
    public final static boolean isAE2Loaded = isModLoaded("ae2");
    public final static boolean isCuriosLoaded = isModLoaded("curios");

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean mayBreakBlock(Level level, BlockPos pos, BlockState state, Player player) {
        return !NeoForge.EVENT_BUS.post(new BlockEvent.BreakEvent(level, pos, state, player)).isCanceled();
    }

    public static ResourceLocation getRecoloredBlockID(ResourceLocation originalId, PUColor color) {
        if (color == PUColor.CLEAR) return originalId;

        String namespace = originalId.getNamespace();
        String path = originalId.getPath();

        Set<String> validColors = PUColor.VALID_COLORS.stream().map(validColor -> validColor.dye.toString()).collect(Collectors.toSet());

        String colorPattern = validColors.stream().sorted((a, b) -> Integer.compare(b.length(), a.length())).collect(Collectors.joining("|"));

        Pattern pattern = Pattern.compile("(?<=^|_)(" + colorPattern + ")(?=_|$)");
        Matcher matcher = pattern.matcher(path);

        if (matcher.find()) {
            String updatedPath = matcher.replaceFirst(color.name().toLowerCase());

            return ResourceLocation.fromNamespaceAndPath(namespace, updatedPath);
        }

        return originalId;
    }

    public static <T extends Comparable<T>> BlockState copyProperties(BlockState oldState, BlockState newState, Property<T> property) {
        if (newState.hasProperty(property)) return newState.setValue(property, oldState.getValue(property));
        return newState;
    }

//    public static final List<BiFunction<Player, Predicate<ItemStack>, Boolean>> curioResolvers = new ArrayList<>();
//
//    public static boolean hasCurio(Entity entity, Supplier<Item> item) {
//        if (entity instanceof Player player) {
//            return curioResolvers.stream().anyMatch(resolver -> resolver.apply(player, stack -> stack.is(item.get())));
//        }
//        return false;
//    }
}
