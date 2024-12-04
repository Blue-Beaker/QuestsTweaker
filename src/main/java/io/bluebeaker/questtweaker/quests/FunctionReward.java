package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.reward.Reward;
import com.feed_the_beast.ftbquests.quest.reward.RewardType;
import io.bluebeaker.questtweaker.ctintegration.FunctionManager;
import net.minecraft.entity.player.EntityPlayerMP;

public class FunctionReward extends Reward {
    /**The ID of the function to call */
    public String functionID;

    public FunctionReward(Quest q) {
        super(q);
        this.functionID = "";
    }

    @Override
    public RewardType getType() {
        return QuestTweakerPlugin.FUNCTION_REWARD;
    }

    @Override
    public void claim(EntityPlayerMP player, boolean b) {
        FunctionManager.runFunction(this.functionID,player);
    }
}
