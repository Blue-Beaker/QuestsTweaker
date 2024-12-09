package io.bluebeaker.questtweaker.filters;

import com.latmod.mods.itemfilters.api.StringValueFilterVariant;
import com.latmod.mods.itemfilters.filters.FilterBase;
import com.latmod.mods.itemfilters.filters.StringValueFilter;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import io.bluebeaker.questtweaker.ctintegration.IngredientManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IIngredientFilter extends StringValueFilter {

    private IIngredient ingredient;

    @Override
    public String getID() {
        return "questtweaker_ingredient";
    }

    public void setValue(String v){
        super.setValue(v);
        this.ingredient=IngredientManager.getIngredient(v);
    }

    public void getValidItems(List<ItemStack> list) {
        this.ingredient.getItems().forEach((item)->{
            list.add(CraftTweakerMC.getItemStack(item));
        });
    }

    @SideOnly(Side.CLIENT)
    public Collection<StringValueFilterVariant> getValueVariants() {
        List<StringValueFilterVariant> variants = new ArrayList<>();
        IngredientManager.INGREDIENTS.forEach((s,ingredient)->{
            StringValueFilterVariant variant = new StringValueFilterVariant(s);
            List<IItemStack> items = ingredient.getItems();
            if(!items.isEmpty()){
                variant.icon=CraftTweakerMC.getItemStack(items.get(0));
            }
            variants.add(variant);
        });
        return variants;
    }

    @Override
    public boolean filter(ItemStack itemStack) {
        if(this.ingredient==null)
            return false;
        return this.ingredient.matches(CraftTweakerMC.getIItemStack(itemStack));
    }
}
