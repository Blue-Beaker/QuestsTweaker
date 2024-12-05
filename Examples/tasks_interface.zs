// Example of manipulating tasks with ITaskData.  
// Make sure to edit task ID in the script before trying this.  
// When player crafts anything, pushes the progress of the task by one.  
// Also sends information about the quest to the player.  
import mods.questtweaker.QuestManager;
import mods.queststweaker.quests.IQuestData;
import mods.queststweaker.quests.ITaskData;
import mods.queststweaker.questobjects.ITask;

events.onPlayerCrafted(function(event as crafttweaker.event.PlayerCraftedEvent){
    val taskData as ITaskData = ITaskData.getTaskData(event.player,0x58b195a7); //Change task ID here!
    if(!isNull(taskData)){
        taskData.addProgress(1);
        event.player.sendMessage("Progress: "~taskData.progress);
        event.player.sendMessage("Max progress: "~taskData.getMaxProgress());
        event.player.sendMessage("Relative progress: "~taskData.getRelativeProgress());
        val task as ITask = taskData.task;
        event.player.sendMessage("Title: "~task.title);
        event.player.sendMessage("ID: "~task.id);
        event.player.sendMessage("Parent ID: "~task.parentID);
    }
});