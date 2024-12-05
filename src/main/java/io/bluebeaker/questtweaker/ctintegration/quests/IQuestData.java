package io.bluebeaker.questtweaker.ctintegration.quests;


import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import io.bluebeaker.questtweaker.ctintegration.questobjects.ITask;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

@ZenClass("mods.queststweaker.quests.IQuestData")
@ZenRegister
public class IQuestData {
    public final QuestData questData;
    public IQuestData(QuestData questData){
        this.questData=questData;
    }

    /**Gets IQuestData from the player */
    @ZenMethod
    public static IQuestData getQuestData(IPlayer iplayer){
        return new IQuestData(QuestUtils.getQuestDataForPlayer(
                CraftTweakerMC.getPlayer(iplayer)));
    }

    /**Gets Task data by its ID */
    @ZenMethod
    public @Nullable ITaskData getTaskData(String taskID){
        return getTaskData(Integer.parseUnsignedInt(taskID,16));
    }
    /**Gets Task data by its ID */
    @ZenMethod
    public @Nullable ITaskData getTaskData(int taskID){
        TaskData taskData = QuestUtils.getTask(this.questData,taskID);
        if(taskData==null)
            return null;
        return new ITaskData(taskData);
    }

    @ZenMethod
    public ITaskData getTaskData(ITask task){
        return new ITaskData(this.questData.getTaskData(task.task));
    }
}
