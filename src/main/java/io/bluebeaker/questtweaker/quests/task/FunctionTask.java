package io.bluebeaker.questtweaker.quests.task;

import com.feed_the_beast.ftblib.lib.config.ConfigGroup;
import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftblib.lib.util.misc.NameMap;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.*;
import io.bluebeaker.questtweaker.ctintegration.FunctionManager;
import io.bluebeaker.questtweaker.quests.QuestTweakerPlugin;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FunctionTask extends Task {
    /**When the function returns a value greater than this, quest will be complete */
    public int value;
    /**The interval to call function */
    public int interval;
    /**The ID of the function to call */
    public String functionID;

    public FunctionTask(Quest q) {
        super(q);
        this.value=1;
        this.interval=10;
        this.functionID="";
    }

    public long getMaxProgress() {
        return (long)this.value;
    }

    @Override
    public TaskType getType() {
        return QuestTweakerPlugin.FUNCTION;
    }

    public void writeData(NBTTagCompound nbt) {
        super.writeData(nbt);
        nbt.setString("function", this.functionID);
        if (this.value != 1) {
            nbt.setInteger("value", this.value);
        }
        if (this.interval != 10) {
            nbt.setInteger("interval", this.interval);
        }

    }

    public void readData(NBTTagCompound nbt) {
        super.readData(nbt);
        this.functionID = nbt.getString("function");

        this.value = nbt.hasKey("value") ? (int)Math.min(2147483647L, nbt.getLong("value")) : 1;
        if (this.value < 1) {
            this.value = 1;
        }
        this.interval = nbt.hasKey("interval") ? (int)Math.min(2147483647L, nbt.getLong("interval")) : 10;
        if (this.interval < 1) {
            this.interval = 1;
        }
    }

    public void writeNetData(DataOut data) {
        super.writeNetData(data);
        data.writeString(this.functionID);
        data.writeVarInt(this.value);
        data.writeVarInt(this.interval);
    }

    public void readNetData(DataIn data) {
        super.readNetData(data);
        this.functionID = data.readString();
        this.value = data.readVarInt();
        this.interval = data.readVarInt();
    }

    public String getAltTitle() {
        return "Function: " + this.functionID;
    }

    @SideOnly(Side.CLIENT)
    public void getConfig(ConfigGroup config) {
        super.getConfig(config);

        config.addString("functionID",()->this.functionID,(id)->this.functionID=id, "");
        config.addInt("value", () -> this.value, (v) -> this.value = v, 1, 1, Integer.MAX_VALUE);
        config.addInt("interval", () -> this.interval, (v) -> this.interval = v, 10, 1, Integer.MAX_VALUE);
    }

    public int autoSubmitOnPlayerTick() {
        return this.interval;
    }

    @Override
    public TaskData<FunctionTask> createData(QuestData questData) {
        return new FunctionTask.Data(this,questData);
    }

    public static class Data extends TaskData<FunctionTask> {
        private Data(FunctionTask task, QuestData data) {
            super(task, data);
        }

        public String getProgressString() {
            return Integer.toString((int)this.progress);
        }

        public void submitTask(EntityPlayerMP player, ItemStack item) {
            if (!this.isComplete()) {
                int set = Math.min(((FunctionTask)this.task).value, FunctionManager.runFunction(((FunctionTask)this.task).functionID,player));
                if(set>=0)
                    this.setProgress((long)set);
            }
        }
    }
}
