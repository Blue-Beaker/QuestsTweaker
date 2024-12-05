package io.bluebeaker.questtweaker.ctintegration.quests;

import com.feed_the_beast.ftbquests.quest.task.TaskData;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import io.bluebeaker.questtweaker.ctintegration.questobjects.ITask;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import net.minecraft.entity.player.EntityPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;

@ZenClass("mods.queststweaker.quests.ITaskData")
@ZenRegister
public class ITaskData {
    public final TaskData taskData;
    public ITaskData(TaskData taskData){
        this.taskData=taskData;
    }
    @ZenGetter("task")
    public ITask getTask(){
        return new ITask(taskData.task);
    }

    /**Returns whether the task is unlocked and uncompleted. */
    @ZenMethod
    public boolean isActive(){
        return QuestUtils.isTaskActive(taskData);
    }

    @ZenMethod
    public boolean isComplete(){
        return taskData.isComplete();
    }

    /**Returns whether all the dependencies of the task is completed. */
    @ZenMethod
    public boolean isTaskUnlocked(){
        return QuestUtils.isTaskUnlocked(taskData);
    }

    /**Current progress */
    @ZenMethod
    @ZenGetter("progress")
    public long getProgress(){
        return taskData.progress;
    }
    
    @ZenMethod
    public void setProgressForce(long progress){
        taskData.setProgress(progress);
    }

    @ZenMethod
    public void addProgressForce(long progress){
        taskData.addProgress(progress);
    }

    /**When the task is active, sets its progress value */
    @ZenMethod
    public void setProgress(long progress){
        if(this.isActive())
            taskData.setProgress(progress);
    }
    /**When the task is active, adds its progress value */
    @ZenMethod
    public void addProgress(long progress){
        if(this.isActive())
            taskData.addProgress(progress);
    }

    /**Get progress value needed to complete the task */
    @ZenMethod
    @ZenGetter("maxProgress")
    public long getMaxProgress(){
        return taskData.task.getMaxProgress();
    }
    /**Get relative progress */
    @ZenMethod
    @ZenGetter("relativeProgress")
    public int getRelativeProgress(){
        return taskData.getRelativeProgress();
    }

    /**Gets ITaskData object */
    @ZenMethod
    public static @Nullable ITaskData getTaskData(IPlayer iPlayer, String taskID){
        return getTaskData(iPlayer,Integer.parseUnsignedInt(taskID,16));
    }

    /**Gets ITaskData object */
    @ZenMethod
    public static @Nullable ITaskData getTaskData(IPlayer iPlayer, int taskID){
        EntityPlayer player = CraftTweakerMC.getPlayer(iPlayer);
        TaskData taskData = QuestUtils.getTask(player,taskID);
        if(taskData==null)
            return null;
        return new ITaskData(taskData);
    }
}
