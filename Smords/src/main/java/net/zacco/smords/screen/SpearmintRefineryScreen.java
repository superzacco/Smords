package net.zacco.smords.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zacco.smords.Smords;

public class SpearmintRefineryScreen extends HandledScreen<SpearmintRefineryScreenHandler>
{
    private static final Identifier TEXTURE = new Identifier(Smords.MOD_ID, "textures/gui/container/spearmint_refinery.png");

    public SpearmintRefineryScreen(SpearmintRefineryScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Filled part of the arrow lives to the right of the background in the texture
        int arrow = handler.GetArrowProgress();
        if (arrow > 0)
        {
            context.drawTexture(TEXTURE, x + 79, y + 34, 176, 0, arrow, 17);
        }
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY)
    {
        super.drawForeground(context, mouseX, mouseY);

        if (!handler.IsStructureComplete())
        {
            Text warning = Text.translatable("container.smords.spearmint_refinery.incomplete");
            context.drawText(textRenderer, warning, (backgroundWidth - textRenderer.getWidth(warning)) / 2, 60, 0xAA2222, false);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
