# QuestsTweaker
A mod to integrate FTBQuests with CraftTweaker.  

## Features
- Add/set progress for an existing quest from crafttweaker.  
- New 'Function' task type that runs a crafttweaker function to check progress.  
- New 'Function' reward type that runs a crafttweaker funtion on completion.  

## CraftTweaker API
### mods.questtweaker.QuestManager
Add/set progress for any existing and available task from crafttweaker:  
When the task is unavailable (for example has dependencies uncompleted), this function does nothing.  
`QuestManager.addTaskProgress(IPlayer player, string/int id, long progress);`
`QuestManager.setTaskProgress(IPlayer player, string/int id, long progress);`
```
import mods.questtweaker.QuestManager;
// Add progress
// IPlayer player, string/int id, long progress
QuestManager.addTaskProgress(event.player,"4c4bd563",1 as long);
// Or use hex int
QuestManager.addTaskProgress(event.player,0x4c4bd563,1 as long);
// Set progress
// IPlayer player, string/int id, long progress
QuestManager.setTaskProgress(event.player,"4c4bd563",1 as long);
```  
Example when player pickups an item, set the task progress to stack size:
```
import mods.questtweaker.QuestManager;
events.onPlayerPickupItem(function(event as crafttweaker.event.PlayerPickupItemEvent){
    QuestManager.setTaskProgress(event.player,"52725826",event.item.item.amount as long);
});
```

### mods.questtweaker.FunctionManager
Add a new function to use with this mod's 'Function' tasks and rewards:  
`FunctionManager.addFunction(string functionID, function(IPlayer) -> long);`  
The function __must__ return an long.  
```
import mods.queststweaker.FunctionManager;
import crafttweaker.player.IPlayer;
// string functionID, function(IPlayer) -> long
FunctionManager.addFunction("height",function(player as IPlayer){
    return player.y as long;
});
```

## FTBQuests Integration
### Function Task
Executes a function peridocally, and uses its return value as the progress.  
Parameters:  
- Function ID: the function ID registered in scripts.  
- Value: The value needed to complete the task.  
- Interval: The interval to run the function. Set to 0 for manual submission.  

### Function Reward
Executes a function when collected.  
Parameters:  
- Function ID: the function ID registered in scripts.  

### Dummy Task
Does nothing on its own. Intended to use with the QuestManager above.  
Parameters:  
- Value: The value needed to complete the task.  