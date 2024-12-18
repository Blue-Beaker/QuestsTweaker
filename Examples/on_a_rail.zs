// An example of replicating the "On A Rail" achievement of legacy versions of Minecraft.
// In FTBQuests, add a new 'Function' task with functionID 'minecart_distance', and value '1000'. 
// Then take a ride of minecart and check the task to see the results.

import mods.questtweaker.FunctionManager;
import crafttweaker.util.Position3f;
import crafttweaker.event.EntityMountEvent;
import crafttweaker.entity.IEntity;
import crafttweaker.player.IPlayer;
import crafttweaker.util.Math;
import crafttweaker.entity.IEntityDefinition;

static playerMountLocations as Position3f[IEntity] = {} ;
static carts as string[] = ["minecraft:minecart"];

function isMinecart(entity as IEntity) as bool{
    if(entity){
        return carts in entity.definition.id;
    }
    return false;
}

events.onEntityMount(function(event as EntityMountEvent){
    val mountingEntity = event.mountingEntity;
    if(mountingEntity instanceof IPlayer){
        val player as IPlayer = mountingEntity;
        val mount = event.mountedEntity;
        if(isMinecart(mount)){
            playerMountLocations[player] = crafttweaker.util.Position3f.create(mount.x, mount.y, mount.z);
        }
    }
});

FunctionManager.addFunction("minecart_distance",function(player as IPlayer){
    if(!isMinecart(player.getRidingEntity())){
        return 0 as long;
    }
    val startPos = playerMountLocations[player];
    if(startPos){
        var distance = Math.sqrt(pow(startPos.x-player.x,2)+pow(startPos.y-player.y,2)+pow(startPos.z-player.z,2));
        return Math.floor(distance);
    }
    return 0 as long;
});