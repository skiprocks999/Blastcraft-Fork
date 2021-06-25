package blastcraft.common.recipe.categories.o2o.specificmachines;

import blastcraft.common.recipe.BlastCraftRecipeInit;
import electrodynamics.common.recipe.categories.o2o.O2ORecipe;
import electrodynamics.common.recipe.recipeutils.CountableIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class BlastCompressorRecipe extends O2ORecipe {

    public static final String RECIPE_GROUP = "blast_compressor_recipe";
    public static final String MOD_ID = blastcraft.References.ID;
    public static final ResourceLocation RECIPE_ID = new ResourceLocation(MOD_ID, RECIPE_GROUP);

    public BlastCompressorRecipe(ResourceLocation id, CountableIngredient input, ItemStack output) {
	super(id, input, output);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
	return BlastCraftRecipeInit.BLAST_COMPRESSOR_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
	return Registry.RECIPE_TYPE.getOrDefault(RECIPE_ID);
    }

}
