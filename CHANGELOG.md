## 1.0.1
Fixed crafttweaker function having a different package
## 1.0.2
Added Function: 
- `QuestManager.getTaskProgress(IPlayer iPlayer, string/int taskID)`  
## 1.1.0
Added various zenClasses to manipulate with quests, tasks and rewards.  
Changed QuestManager to only change task progress when its unlocked and uncompleted.  
# 1.1.1
Fix potential NullPointerException occurring in this mod, which is hard to debug when scripting.
Fix zenClass has different package names. Replace `queststweaker` to `questtweaker` in your scripts.
# 1.1.2
Added support for ZenUtil's reload to reload functions  
Add option to make function ID selectable in quests screen  
# 1.1.3  
Fixed NoSuchMethodError when trying to query quests from some fake-players. It now simply returns a null.  
# 1.2.0
Added Ingredient Filter for Item Filters. It matches items that matches the given `IIngredient`.  
The `IIngredient`s for the filter is registered in a way similar to custom functions:  
```
IngredientManager.addIngredient(String ingredientID, IIngredient ingredient);
```