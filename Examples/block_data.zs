// This example needs BuildCraft to be installed.
// When the player interacts with a overheated BuildCraft engine, complete the task.
// Needs a 'dummy' task with value 215.
// Task progress shows the last engine player interacted with.

import mods.questtweaker.QuestManager;
import crafttweaker.player.IPlayer;

import crafttweaker.util.Math;
import crafttweaker.block.IBlock;
import crafttweaker.block.IBlockDefinition;
import crafttweaker.world.IBlockPos;
import crafttweaker.event.PlayerInteractBlockEvent;
import crafttweaker.events.IEventManager;
import crafttweaker.data.IData;

static engines as IBlockDefinition[] = [<block:buildcraftcore:engine>.block.definition];


events.onPlayerInteractBlock(function(event as PlayerInteractBlockEvent){
    val block as IBlock = event.block;
    if((engines has block.definition) && !isNull(block.data)){
        val data as IData = block.data;
        if(data.heat){
            val heat = data.heat.asFloat();
            val longHeat as long = Math.floor(heat);
            QuestManager.setTaskProgress(event.player, 0xd62cf22e, longHeat);
        }
    }
});
