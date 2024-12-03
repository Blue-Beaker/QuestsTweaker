# QuestsTweaker
A mod to integrate FTBQuests quests with CraftTweaker.  
## Features
- Add/set progress for an existing quest from crafttweaker.  
todo:  
- New 'CraftTweaker Function' quest type, that executes a function and completes the quest if the function returns true  

## Documentation

Add/set progress for an existing quest from crafttweaker:

```
import mods.questtweaker.QuestManager;
// Add progress
// IPlayer player, string or int id, long progress
QuestManager.addTaskProgress(event.player,"4c4bd563",1 as long);
QuestManager.addTaskProgress(event.player,0x4c4bd563,1 as long);
// Set progress
// IPlayer player, string or int id, long progress
QuestManager.setTaskProgress(event.player,"4c4bd563",1 as long);
```  