package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.FTBQuests;
import com.feed_the_beast.ftbquests.quest.*;
import com.feed_the_beast.ftbquests.quest.task.Task;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IWorld;
import io.bluebeaker.questtweaker.utils.QuestUtils;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.questtweaker.questobjects.IQuestFile")
@ZenRegister
public class IQuestFile extends IQuestObject<QuestFile> {

    public IQuestFile(QuestFile questFile) {
        super(questFile);
    }

    @ZenMethod
    @Nullable
    public static IQuestFile getQuestFile(@Nullable IWorld world){
        QuestFile questFile1 = FTBQuests.PROXY.getQuestFile(CraftTweakerMC.getWorld(world));
        return questFile1!=null ? new IQuestFile(questFile1) : null;
    }


    @ZenMethod
    @ZenGetter("chapters")
    public List<IChapter> getChapters(){
        List<IChapter> chapters = new ArrayList<>();
        this.internal.chapters.forEach((chapter)
                -> chapters.add(new IChapter(chapter)));
        return chapters;
    }

    @ZenMethod
    @Nullable
    public IQuest getQuest(int id){
        Quest quest = internal.getQuest(id);
        return quest!=null ? new IQuest(quest) : null;
    }
    @ZenMethod
    @Nullable
    public IQuest getQuest(String id){
        return getQuest(QuestUtils.parseTaskID(id));
    }

    @ZenMethod
    @Nullable
    public ITask getTask(int id){
        Task task = internal.getTask(id);
        return task!=null ? new ITask(task) : null;
    }
    @ZenMethod
    @Nullable
    public ITask getTask(String id){
        return getTask(QuestUtils.parseTaskID(id));
    }

    @ZenMethod
    @Nullable
    public IChapter getChapter(int id){
        Chapter chapter = internal.getChapter(id);
        return chapter!=null ? new IChapter(chapter) : null;
    }
    @ZenMethod
    @Nullable
    public IChapter getChapter(String id){
        return getChapter(QuestUtils.parseTaskID(id));
    }
}
