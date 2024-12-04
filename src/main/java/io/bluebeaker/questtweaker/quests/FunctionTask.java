package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftblib.lib.config.ConfigGroup;
import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.*;
import io.bluebeaker.questtweaker.ctintegration.FunctionManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class FunctionTask extends Task {
    /**When the function returns a value greater than this, quest will be complete */
    public long value;
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
        return QuestTweakerPlugin.FUNCTION_TASK;
    }

    public void writeData(NBTTagCompound nbt) {
        super.writeData(nbt);
        nbt.setString("function", this.functionID);
        if (this.value != 1) {
            nbt.setLong("value", this.value);
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
        if (this.interval < 0) {
            this.interval = 0;
        }
    }

    public void writeNetData(DataOut data) {
        super.writeNetData(data);
        data.writeString(this.functionID);
        data.writeVarLong(this.value);
        data.writeVarInt(this.interval);
    }

    public void readNetData(DataIn data) {
        super.readNetData(data);
        this.functionID = data.readString();
        this.value = data.readVarInt();
        this.interval = data.readVarInt();
    }

    public String getAltTitle() {
        return this.getType().getDisplayName() +": " + this.functionID;
    }

    @SideOnly(Side.CLIENT)
    public void getConfig(ConfigGroup config) {
        super.getConfig(config);

        config.addString("functionID",()->this.functionID,(id)->this.functionID=id, "");
        config.addLong("value", () -> this.value, (v) -> this.value = v, 1, 1, Long.MAX_VALUE);
        config.addInt("interval", () -> this.interval, (v) -> this.interval = v, 10, 0, Integer.MAX_VALUE);
    }
    // Set to 0 to for manual submit
    public int autoSubmitOnPlayerTick() {
        return this.interval;
    }
    // Adds 'Click to submit' if the task is not automatic
    @SideOnly(Side.CLIENT)
    public void addMouseOverText(List<String> list, @Nullable TaskData data) {
        if (this.interval==0) {
            list.add("");
            list.add(TextFormatting.YELLOW.toString() + TextFormatting.UNDERLINE + I18n.format("ftbquests.task.click_to_submit", new Object[0]));
        }

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
                long set = Math.min(((FunctionTask)this.task).value, FunctionManager.runFunction(((FunctionTask)this.task).functionID,player));
                if(set>=0)
                    this.setProgress((long)set);
            }
        }
    }
}
