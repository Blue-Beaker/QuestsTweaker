package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.task.Task;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.queststweaker.questobjects.ITask")
@ZenRegister
public class ITask extends IQuestObject {
    public final Task questObject;
    public ITask(Task task) {
        this.questObject = task;
    }

    @ZenMethod
    @ZenGetter("quest")
    public IQuest getQuest(){
        return new IQuest(questObject.quest);
    }

    @ZenMethod
    @ZenGetter("maxProgress")
    public long getMaxProgress(){
        return questObject.getMaxProgress();
    }
}
