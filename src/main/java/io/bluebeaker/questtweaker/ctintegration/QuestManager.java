package io.bluebeaker.questtweaker.ctintegration;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.Task;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import io.bluebeaker.questtweaker.QuestTweakerMod;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import net.minecraft.entity.player.EntityPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

@ZenClass("mods.questtweaker.QuestManager")
@ZenRegister
public class QuestManager {

    /**Adds progress to the specified task for the player. */
    @ZenMethod
    public static void addTaskProgress(IPlayer iPlayer, String taskID, long progress) {
        addTaskProgress(iPlayer, Integer.parseUnsignedInt(taskID,16) ,progress);
    }
    /**Adds progress to the specified task for the player. */
    @ZenMethod
    public static void addTaskProgress(IPlayer iPlayer, int taskID, long progress) {
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);
        TaskData taskData = QuestUtils.getTask(player,taskID);
        if(QuestUtils.isTaskUnlocked(taskData))
            taskData.addProgress(progress);
        else{
            if(taskData==null)
                QuestTweakerMod.getLogger().atInfo().log("addTaskProgress: Cant find task with id "+Integer.toHexString(taskID));
        }
    }

    /**Sets progress to the specified task for the player. */
    @ZenMethod
    public static void setTaskProgress(IPlayer iPlayer, String taskID, long progress) {
        setTaskProgress(iPlayer, Integer.parseUnsignedInt(taskID,16) ,progress);
    }
    /**Sets progress to the specified task for the player. */
    @ZenMethod
    public static void setTaskProgress(IPlayer iPlayer, int taskID, long progress) {
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);
        TaskData taskData = QuestUtils.getTask(player,taskID);
        if(QuestUtils.isTaskUnlocked(taskData))
            taskData.setProgress(progress);
        else{
            if(taskData==null)
                QuestTweakerMod.getLogger().atInfo().log("setTaskProgress: Cant find task with id"+Integer.toHexString(taskID));
        }
    }

}