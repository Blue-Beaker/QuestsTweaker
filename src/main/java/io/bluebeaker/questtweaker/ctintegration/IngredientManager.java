package io.bluebeaker.questtweaker.ctintegration;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import io.bluebeaker.questtweaker.QuestTweakerMod;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.reload.Reloadable;

import javax.annotation.Nullable;
import java.util.HashMap;

@ZenClass("mods.questtweaker.IngredientManager")
@ZenRegister
public class IngredientManager {
    public static final HashMap<String, IIngredient> INGREDIENTS = new HashMap<String, IIngredient>();


    /**
     * @param ingredientID ID of the ingredient
     * @param ingredient the ingredient
     */
    @ZenMethod
    public static void addIngredient(String ingredientID, IIngredient ingredient){
        CraftTweakerAPI.apply(new AddIngredientAction(ingredientID, ingredient));
    }

    /**
     * @param ingredientID ID of the ingredient
     * @return the ingredient
     */
    public static @Nullable IIngredient getIngredient(String ingredientID){
        return INGREDIENTS.get(ingredientID);
    }

    @Reloadable
    public static class AddIngredientAction implements IAction {

        private final String ingredientID;
        private final IIngredient ingredient;

        public AddIngredientAction(String ingredientID, IIngredient ingredient) {
            this.ingredientID = ingredientID;
            this.ingredient = ingredient;
        }

        @Override
        public void apply() {
            if(INGREDIENTS.containsKey(ingredientID)){
                QuestTweakerMod.getLogger().warn("Duplicate Ingredient ID: '"+ingredientID+"'. Ingredient IDs should be unique, please check your scripts. ");
            }
            INGREDIENTS.put(ingredientID, ingredient);
        }
        public void undo(){
            INGREDIENTS.remove(ingredientID);
        }

        @Override
        public String describe() {
            return "Registering Ingredient ID "+ingredientID+ " = "+ingredient.toCommandString();
        }
    }
}
