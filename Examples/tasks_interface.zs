// Example of manipulating tasks with ITaskData.  
import mods.questtweaker.QuestManager;
import mods.queststweaker.quests.IQuestData;
import mods.queststweaker.quests.ITaskData;
import mods.queststweaker.questobjects.ITask;
import mods.queststweaker.questobjects.IQuestObject;

events.onPlayerInteractBlock(function(event as crafttweaker.event.PlayerInteractBlockEvent){
    val taskData as ITaskData = ITaskData.getTaskData(event.player,0x58b195a7);
    if(!isNull(taskData)){
        taskData.addProgress(1);
        event.player.sendMessage("Progress: "~taskData.progress);
        event.player.sendMessage("Max progress: "~taskData.getMaxProgress());
        event.player.sendMessage("Relative progress: "~taskData.getRelativeProgress());
        val task as ITask = taskData.task;
        if(!isNull(task)){
            event.player.sendMessage("Title: "~task.title);
            event.player.sendMessage("ID: "~task.id);
            event.player.sendMessage("Parent ID: "~task.parentID);
        }
    }
});