package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestObjectBase;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.questtweaker.questobjects.IQuest")
@ZenRegister
public class IQuest extends IQuestObject {
    public final Quest quest;
    public IQuest(Quest quest) {
        this.quest = quest;
    }

    @ZenMethod
    @ZenGetter("tasks")
    public List<ITask> getTasks(){
        List<ITask> tasks = new ArrayList<>();
        this.quest.tasks.forEach((task)
                -> tasks.add(new ITask(task)));
        return tasks;
    }
    @ZenMethod
    @ZenGetter("rewards")
    public List<IReward> getRewards() {
        List<IReward> rewards = new ArrayList<>();
        this.quest.rewards.forEach((reward)
                -> rewards.add(new IReward(reward)));
        return rewards;
    }

    @Override
    public QuestObjectBase getQuestObject() {
        return this.quest;
    }
}
