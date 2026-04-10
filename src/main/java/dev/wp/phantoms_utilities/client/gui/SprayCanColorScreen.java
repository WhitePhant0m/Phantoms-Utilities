package dev.wp.phantoms_utilities.client.gui;

import dev.wp.phantoms_utilities.util.PUColor;
import dev.wp.phantoms_utilities.network.server.SprayCanColorSelectPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class SprayCanColorScreen extends Screen {
    private static final int ICON_SIZE = 18;
    private static final int COLS = 6;
    private final List<ColorButton> buttons = new ArrayList<>();
    private int guiLeft;
    private int guiTop;
    private int xSize;
    private int ySize;

    public SprayCanColorScreen() {
        super(Component.translatable("gui.phantoms_utilities.spray_can_color.title"));
    }

    @Override
    protected void init() {
        this.buttons.clear();
        this.xSize = COLS * ICON_SIZE + 20;
        int rows = (int) Math.ceil((PUColor.values().length) / (double) COLS);
        this.ySize = rows * ICON_SIZE + 20;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        for (int i = 0; i < PUColor.values().length; i++) {
            PUColor color = PUColor.values()[i];
            int col = i % COLS;
            int row = i / COLS;
            int x = this.guiLeft + 10 + col * ICON_SIZE;
            int y = this.guiTop + 10 + row * ICON_SIZE;
            buttons.add(new ColorButton(x, y, color));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.fill(guiLeft, guiTop, guiLeft + xSize, guiTop + ySize, 0xAA000000);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, guiTop - 15, 0xFFFFFF);

        for (ColorButton button : buttons) {
            button.render(graphics, mouseX, mouseY);
        }

        for (ColorButton button : buttons) {
            if (button.isMouseOver(mouseX, mouseY)) {
                graphics.renderTooltip(this.font, button.getTooltip(), mouseX, mouseY);
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left click
            for (ColorButton colorButton : buttons) {
                if (colorButton.isMouseOver(mouseX, mouseY)) {
                    PacketDistributor.sendToServer(new SprayCanColorSelectPacket(colorButton.color));
                    this.onClose();
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private static class ColorButton {
        final int x, y;
        final PUColor color;
        final ItemStack stack;

        ColorButton(int x, int y, PUColor color) {
            this.x = x;
            this.y = y;
            this.color = color;
            if (color == PUColor.CLEAR) {
                this.stack = new ItemStack(Items.GLASS);
            } else {
                this.stack = new ItemStack(DyeItem.byColor(color.dye));
            }
        }

        void render(GuiGraphics graphics, int mouseX, int mouseY) {
            if (isMouseOver(mouseX, mouseY)) {
                graphics.fill(x, y, x + ICON_SIZE, y + ICON_SIZE, 0x55FFFFFF);
            }
            graphics.renderItem(stack, x + 1, y + 1);
        }

        boolean isMouseOver(double mouseX, double mouseY) {
            return mouseX >= x && mouseX < x + ICON_SIZE && mouseY >= y && mouseY < y + ICON_SIZE;
        }

        Component getTooltip() {
            return Component.translatable(color.translationKey);
        }
    }
}
