package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.reward.Reward;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.IReward")
@ZenRegister
public class IReward extends IQuestObjectBase<Reward> {
    public IReward(Reward reward) {
        super(reward);
    }
    
    @ZenMethod
    @ZenGetter("quest")
    public IQuest getQuest(){
        return new IQuest(internal.quest);
    }
}
