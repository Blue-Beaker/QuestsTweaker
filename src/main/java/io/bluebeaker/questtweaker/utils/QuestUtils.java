package io.bluebeaker.questtweaker.utils;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.Task;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

@SuppressWarnings("rawtypes")
public class QuestUtils {

    /**
     * @param player The player
     * @return QuestData or null if player==null
     */
    public static @Nullable QuestData getQuestDataForPlayer(@Nullable EntityPlayer player) {
        if(player==null){
            return null;
        }
        try{
            return FTBQuests.PROXY.getQuestData(player.getEntityWorld(), player.getPersistentID());
        }
        // Workaround for some fake-players
        catch (Exception e){
            return null;
        }
    }

    /**
     * @param player The player to get TaskData from
     * @param id The id of the task
     * @return The TaskData or null if player is null, or task is not found
     */
    public static @Nullable TaskData getTaskData(@Nullable EntityPlayer player, int id){
        QuestData questData = getQuestDataForPlayer(player);
        if(questData==null)
            return null;
        return getTaskData(questData,id);
    }

    public static @Nullable TaskData getTaskData(QuestData data, int id) {
        Task task = data.getFile().getTask(data.getFile().getID(id));
        if (task != null){
            return data.getTaskData(task);
        }
        return null;
    }

    /**
     * @param taskData TaskData to get state from
     * @return True when the task is nonnull and unlocked.
     */
    public static boolean isTaskUnlocked(@Nullable TaskData taskData){
        if(taskData==null)
            return false;
        Quest quest = taskData.task.quest;
        return quest.areDependenciesComplete(taskData.data);
    }

    /**
     * @param taskData TaskData to get state from
     * @return True when task is nonnull, unlocked and uncomplete. False otherwise.
     */
    public static boolean isTaskActive(@Nullable TaskData taskData){
        return isTaskUnlocked(taskData) && !taskData.isComplete();
    }

    public static int parseTaskID(String taskID) {
        return Integer.parseUnsignedInt(taskID, 16);
    }

    public static String getStringFromTaskID(int taskID) {
        return Integer.toHexString(taskID);
    }
}
