package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.Quest;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.questtweaker.questobjects.IQuest")
@ZenRegister
public class IQuest extends IQuestObject<Quest> {
    public IQuest(Quest quest) {
        super(quest);
    }

    @ZenMethod
    @ZenGetter("tasks")
    public List<ITask> getTasks(){
        List<ITask> tasks = new ArrayList<>();
        this.internal.tasks.forEach((task)
                -> tasks.add(new ITask(task)));
        return tasks;
    }
    @ZenMethod
    @ZenGetter("rewards")
    public List<IReward> getRewards() {
        List<IReward> rewards = new ArrayList<>();
        this.internal.rewards.forEach((reward)
                -> rewards.add(new IReward(reward)));
        return rewards;
    }
}
