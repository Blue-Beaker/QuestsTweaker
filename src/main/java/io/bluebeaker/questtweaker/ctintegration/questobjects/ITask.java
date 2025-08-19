package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.task.Task;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.ITask")
@ZenRegister
public class ITask extends IQuestObject<Task> {
    public ITask(Task task) {
        super(task);
    }

    @ZenMethod
    @ZenGetter("quest")
    public IQuest getQuest(){
        return new IQuest(internal.quest);
    }

    @ZenMethod
    @ZenGetter("maxProgress")
    public long getMaxProgress(){
        return internal.getMaxProgress();
    }
}
