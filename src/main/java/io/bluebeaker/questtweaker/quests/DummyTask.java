package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftblib.lib.config.ConfigGroup;
import com.feed_the_beast.ftblib.lib.gui.GuiHelper;
import com.feed_the_beast.ftblib.lib.io.DataIn;
import com.feed_the_beast.ftblib.lib.io.DataOut;
import com.feed_the_beast.ftbquests.net.MessageSubmitTask;
import com.feed_the_beast.ftbquests.quest.Quest;
import com.feed_the_beast.ftbquests.quest.QuestData;
import com.feed_the_beast.ftbquests.quest.task.Task;
import com.feed_the_beast.ftbquests.quest.task.TaskData;
import com.feed_the_beast.ftbquests.quest.task.TaskType;
import io.bluebeaker.questtweaker.ctintegration.FunctionManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DummyTask extends Task {
    /**When the function returns a value greater than this, quest will be complete */
    public int value;

    public DummyTask(Quest q) {
        super(q);
        this.value = 1;
    }

    @Override
    public TaskType getType() {
        return QuestTweakerPlugin.DUMMY_TASK;
    }

    public void writeData(NBTTagCompound nbt) {
        super.writeData(nbt);
        if (this.value != 1) {
            nbt.setInteger("value", this.value);
        }

    }

    public void readData(NBTTagCompound nbt) {
        super.readData(nbt);

        this.value = nbt.hasKey("value") ? (int)Math.min(2147483647L, nbt.getLong("value")) : 1;
        if (this.value < 1) {
            this.value = 1;
        }
    }

    public void writeNetData(DataOut data) {
        super.writeNetData(data);
        data.writeVarInt(this.value);
    }

    public void readNetData(DataIn data) {
        super.readNetData(data);
        this.value = data.readVarInt();
    }

    @SideOnly(Side.CLIENT)
    public void getConfig(ConfigGroup config) {
        super.getConfig(config);
        config.addInt("value", () -> this.value, (v) -> this.value = v, 1, 1, Integer.MAX_VALUE);
    }

    // DummyTask can't be submitted
    @Override
    @SideOnly(Side.CLIENT)
    public void onButtonClicked(boolean canClick) {
    }
    // DummyTask can't be submitted
    public int autoSubmitOnPlayerTick() {
        return 0;
    }

    @Override
    public TaskData<DummyTask> createData(QuestData questData) {
        return new DummyTask.Data(this,questData);
    }

    public static class Data extends TaskData<DummyTask> {
        private Data(DummyTask task, QuestData data) {
            super(task, data);
        }

        public String getProgressString() {
            return Integer.toString((int)this.progress);
        }

        public void submitTask(EntityPlayerMP player, ItemStack item) {
        }
    }
}
