package io.bluebeaker.questtweaker.quests;

import com.feed_the_beast.ftbquests.quest.task.TaskType;
import io.bluebeaker.questtweaker.quests.task.FunctionTask;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class QuestTweakerPlugin {
    public static TaskType FUNCTION;

    public QuestTweakerPlugin(){}

    public static void preInit() {
        MinecraftForge.EVENT_BUS.register(QuestTweakerPlugin.class);
    }

    @SubscribeEvent
    public static void registerTasks(RegistryEvent.Register<TaskType> event) {
        event.getRegistry().register(FUNCTION = ((TaskType)(new TaskType(FunctionTask::new)).setRegistryName("questtweaker_function")));
    }
}
