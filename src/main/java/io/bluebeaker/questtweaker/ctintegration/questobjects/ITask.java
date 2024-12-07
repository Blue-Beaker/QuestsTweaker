package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObjectBase;
import com.feed_the_beast.ftbquests.quest.task.Task;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.ITask")
@ZenRegister
public class ITask extends IQuestObject {
    public final Task task;
    public ITask(Task task) {
        this.task = task;
    }

    @ZenMethod
    @ZenGetter("quest")
    public IQuest getQuest(){
        return new IQuest(task.quest);
    }

    @ZenMethod
    @ZenGetter("maxProgress")
    public long getMaxProgress(){
        return task.getMaxProgress();
    }

    @Override
    public QuestObjectBase getQuestObject() {
        return this.task;
    }
}
