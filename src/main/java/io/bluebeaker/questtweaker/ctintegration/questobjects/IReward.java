package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObjectBase;
import com.feed_the_beast.ftbquests.quest.reward.Reward;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.IReward")
@ZenRegister
public class IReward extends IQuestObject {
    public final Reward reward;
    public IReward(Reward reward) {
        this.reward = reward;
    }
    
    @ZenMethod
    @ZenGetter("quest")
    public IQuest getQuest(){
        return new IQuest(reward.quest);
    }

    @Override
    public QuestObjectBase getQuestObject() {
        return this.reward;
    }
}
