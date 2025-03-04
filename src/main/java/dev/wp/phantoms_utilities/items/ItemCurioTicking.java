//package dev.wp.phantoms_utilities.items;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
//public class ItemCurioTicking extends ItemCurio {
//    private final ITickable effect;
//
//    public ItemCurioTicking(ITickable effect) {
//        super();
//        this.effect = effect;
//    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
//        super.inventoryTick(stack, level, entity, slot, selected);
//        effect.apply(stack, level, entity, selected);
//    }
//
//    @FunctionalInterface
//    public interface ITickable {
//        void apply(ItemStack stack, Level level, Entity entity, boolean selected);
//    }
//}
