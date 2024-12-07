package io.bluebeaker.questtweaker.ctintegration;

import com.feed_the_beast.ftbquests.quest.task.TaskData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import io.bluebeaker.questtweaker.QuestTweakerMod;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import net.minecraft.entity.player.EntityPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("rawtypes")
@ZenClass("mods.questtweaker.QuestManager")
@ZenRegister
public class QuestManager {

    /**
     * @param iPlayer IPlayer representing player
     * @param taskID ID for the task
     * @param progress Progress to add
     */
    @ZenMethod
    public static void addTaskProgress(IPlayer iPlayer, String taskID, long progress) {
        addTaskProgress(iPlayer, Integer.parseUnsignedInt(taskID,16) ,progress);
    }
    @ZenMethod
    public static void addTaskProgress(IPlayer iPlayer, int taskID, long progress) {
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);
        TaskData taskData = QuestUtils.getTaskData(player,taskID);

        if(taskData==null){
            QuestTweakerMod.getLogger().atInfo().log("addTaskProgress: Cant find task with id "+Integer.toHexString(taskID));
            return;
        }
        if(QuestUtils.isTaskActive(taskData))
            taskData.addProgress(progress);
    }

    /**
     * @param iPlayer IPlayer representing player
     * @param taskID ID for the task
     * @param progress Progress to set
     */
    @ZenMethod
    public static void setTaskProgress(IPlayer iPlayer, String taskID, long progress) {
        setTaskProgress(iPlayer, Integer.parseUnsignedInt(taskID,16) ,progress);
    }
    @ZenMethod
    public static void setTaskProgress(IPlayer iPlayer, int taskID, long progress) {
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);

        TaskData taskData = QuestUtils.getTaskData(player,taskID);
        if(taskData==null){
            QuestTweakerMod.getLogger().atInfo().log("setTaskProgress: Cant find task with id "+Integer.toHexString(taskID));
            return;
        }
        if(QuestUtils.isTaskActive(taskData))
            taskData.setProgress(progress);
    }

    /**
     * @param iPlayer IPlayer representing the player
     * @param taskID ID for the task
     * @return Progress of the task for the player. Returns -1L if the task isn't available.
     */
    @ZenMethod
    public static long getTaskProgress(IPlayer iPlayer, String taskID) {
        return getTaskProgress(iPlayer, Integer.parseUnsignedInt(taskID,16));
    }
    @ZenMethod
    public static long getTaskProgress(IPlayer iPlayer, int taskID) {
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);
        TaskData taskData = QuestUtils.getTaskData(player,taskID);
        if(QuestUtils.isTaskUnlocked(taskData))
            return taskData.progress;
        else{
            if(taskData==null){
                QuestTweakerMod.getLogger().atInfo().log("getTaskProgress: Cant find task with id "+Integer.toHexString(taskID));
                return -1L;
            }
        }
        return -1L;
    }

}
