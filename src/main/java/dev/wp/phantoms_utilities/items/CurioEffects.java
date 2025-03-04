//package dev.wp.phantoms_utilities.items;
//
//import dev.wp.phantoms_utilities.mixin.PlayerAccessor;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
//public class CurioEffects {
//    public static void sleepCharmTick(ItemStack stack, Level level, Entity entity, boolean isWearing) {
//        if (entity instanceof Player player) {
//            if (player.isSleeping() && player instanceof PlayerAccessor accessor && player.getSleepTimer() < 90) {
//                accessor.pu$$setSleepCounter(90);
//            }
//
//            if (player instanceof ServerPlayer serverPlayer) {
//                serverPlayer.getStats().setValue(player, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), 0);
//            }
//        }
//    }
//}
