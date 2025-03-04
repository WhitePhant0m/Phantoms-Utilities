//package dev.wp.phantoms_utilities.compat;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import top.theillusivec4.curios.api.CuriosApi;
//import top.theillusivec4.curios.api.SlotResult;
//
//import java.util.List;
//
//public class Curios {
//    public static List<ItemStack> findCurios(Entity entity, Item item) {
//        if (!(entity instanceof Player player)) return List.of();
//        return CuriosApi.getCuriosInventory(player)
//                .map(inventory -> inventory.findCurios(item).stream()
//                        .map(SlotResult::stack)
//                        .toList())
//                .orElse(List.of());
//    }
//}