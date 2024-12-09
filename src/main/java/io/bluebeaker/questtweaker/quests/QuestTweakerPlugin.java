package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftblib.lib.icon.Icon;
import com.feed_the_beast.ftbquests.quest.reward.RewardType;
import com.feed_the_beast.ftbquests.quest.task.TaskType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class QuestTweakerPlugin {
    public static TaskType FUNCTION_TASK;
    public static RewardType FUNCTION_REWARD;

    public static TaskType DUMMY_TASK;
    public static TaskType INGREDIENT_TASK;

    public QuestTweakerPlugin(){}

    public static void preInit() {
        MinecraftForge.EVENT_BUS.register(QuestTweakerPlugin.class);
    }

    @SubscribeEvent
    public static void registerTasks(RegistryEvent.Register<TaskType> event) {
        event.getRegistry().register(FUNCTION_TASK = ((TaskType)(new TaskType(FunctionTask::new)).setRegistryName("function").setIcon(Icon.getIcon("minecraft:blocks/crafting_table_top"))));

        event.getRegistry().register(DUMMY_TASK = ((TaskType)(new TaskType(DummyTask::new)).setRegistryName("dummy").setIcon(Icon.getIcon("minecraft:blocks/planks_oak"))));
    }

    @SubscribeEvent
    public static void registerRewards(RegistryEvent.Register<RewardType> event) {
        event.getRegistry().register(FUNCTION_REWARD = ((RewardType)(new RewardType(FunctionReward::new)).setRegistryName("function").setIcon(Icon.getIcon("minecraft:blocks/crafting_table_top"))));
    }
}
