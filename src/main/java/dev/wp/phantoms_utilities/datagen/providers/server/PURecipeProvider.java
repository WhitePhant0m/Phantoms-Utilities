package dev.wp.phantoms_utilities.datagen.providers.server;

import dev.wp.phantoms_utilities.PUItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;


public final class PURecipeProvider extends RecipeProvider {
    public PURecipeProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PUItems.SPRAY_CAN.get())
                .pattern("ICI")
                .pattern("IMI")
                .pattern("IYI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('C', Tags.Items.DYES_CYAN)
                .define('M', Tags.Items.DYES_MAGENTA)
                .define('Y', Tags.Items.DYES_YELLOW)
                .unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);
    }
}
