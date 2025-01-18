package dev.wp.phantoms_utilities.helpers;

import net.minecraft.world.item.ItemStack;

public interface IMouseWheelItem {
    void onScroll(ItemStack stack, boolean up);
}
