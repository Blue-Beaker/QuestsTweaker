package io.bluebeaker.questtweaker.utils;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.Task;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class QuestUtils {
    public static QuestData getQuestDataForPlayer(EntityPlayer player) {
        return FTBQuests.PROXY.getQuestData(player.getEntityWorld(), player.getPersistentID());
    }

    public static @Nullable TaskData getTask(QuestData data, int id) {
        Task task = data.getFile().getTask(data.getFile().getID(id));
        if (task != null){
            return data.getTaskData(task);
        }
        return null;
    }

    public static @Nullable TaskData getTask(EntityPlayer player,int id){
        return getTask(getQuestDataForPlayer(player),id);
    }

    public static boolean isTaskUnlocked(TaskData taskData){
        if(taskData==null)
            return false;
        Quest quest = taskData.task.quest;
        return quest.areDependenciesComplete(taskData.data);
    }

    public static boolean isTaskActive(TaskData taskData){
        return isTaskUnlocked(taskData) && !taskData.isComplete();
    }
}
