package io.bluebeaker.questtweaker.ctintegration.quests;


import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import io.bluebeaker.questtweaker.ctintegration.questobjects.ITask;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import net.minecraft.entity.player.EntityPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

@SuppressWarnings("rawtypes")
@ZenClass("mods.questtweaker.quests.IQuestData")
@ZenRegister
public class IQuestData {
    public final QuestData questData;
    public IQuestData(QuestData questData){
        this.questData=questData;
    }

    /**Gets IQuestData from the player */
    @ZenMethod
    public static @Nullable IQuestData getQuestData(IPlayer iplayer){
        EntityPlayer player = CraftTweakerMC.getPlayer(iplayer);
        QuestData data = QuestUtils.getQuestDataForPlayer(
                player);
        if(data==null)
            return null;
        return new IQuestData(data);
    }

    /**Gets Task data by its ID */
    @ZenMethod
    public @Nullable ITaskData getTaskData(String taskID){
        return getTaskData(QuestUtils.parseTaskID(taskID));
    }
    /**Gets Task data by its ID */
    @ZenMethod
    public @Nullable ITaskData getTaskData(int taskID){
        TaskData taskData = QuestUtils.getTaskData(this.questData,taskID);
        if(taskData==null)
            return null;
        return new ITaskData(taskData);
    }

    @ZenMethod
    public ITaskData getTaskData(ITask task){
        return new ITaskData(this.questData.getTaskData(task.task));
    }
}
