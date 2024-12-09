#reloadable
// Example to filter quest items by IIngredient.
// First add the script below to register ingredients.
// Get an 'IIngredient Filter (QuestTweaker)' from Item Filters, input ingredient id in it and save.
// In FTBQuests, Add a plain 'Item' task and set item to the filter we created. Now it matches items by the IIngredient.
//
// Notes:
// If consumeItems set to true, items won't give back anything after consuming, even if it gives back something in crafting recipes.
// If a stacksize is set in an IItemStack for the IIngredient, the count won't display in 'Valid Items' in FTB Quests, but is still needed to complete the task.

import mods.questtweaker.IngredientManager;

IngredientManager.addIngredient("ingredient1",<minecraft:writable_book>.withTag({pages: ["111"]}) | <minecraft:command_block> | <liquid:water>);
IngredientManager.addIngredient("ingredient2",<ore:oreGold>*32 | <liquid:lava>*1000 | <liquid:water>);