// Example for controlling tasks with QuestManager.
// In FTBQuests, add a 'Dummy' task with value '64', and replace "52725826" in this script with the task ID.
// Drop some items on ground and pick up to see the result. Task will be done when you pick up 64 items in a single stack.

import mods.questtweaker.QuestManager;

events.onPlayerPickupItem(function(event as crafttweaker.event.PlayerPickupItemEvent){
    QuestManager.setTaskProgress(event.player,"52725826",event.item.item.amount as long);
});