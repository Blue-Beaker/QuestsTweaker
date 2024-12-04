package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftblib.lib.config.ConfigGroup;
import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.reward.Reward;
import com.feed_the_beast.ftbquests.quest.reward.RewardType;
import io.bluebeaker.questtweaker.ctintegration.FunctionManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    public void writeData(NBTTagCompound nbt) {
        super.writeData(nbt);
        nbt.setString("function", this.functionID);
    }

    public void readData(NBTTagCompound nbt) {
        super.readData(nbt);
        this.functionID = nbt.getString("function");
    }

    public void writeNetData(DataOut data) {
        super.writeNetData(data);
        data.writeString(this.functionID);
    }

    public void readNetData(DataIn data) {
        super.readNetData(data);
        this.functionID = data.readString();
    }

    @Override
    public void claim(EntityPlayerMP player, boolean notify) {
        FunctionManager.runFunction(this.functionID,player);
    }

    public String getAltTitle() {
        return this.getType().getDisplayName() +": " + this.functionID;
    }

    @SideOnly(Side.CLIENT)
    public void getConfig(ConfigGroup config) {
        super.getConfig(config);
        config.addString("functionID",()->this.functionID,(id)->this.functionID=id, "");
    }
}
