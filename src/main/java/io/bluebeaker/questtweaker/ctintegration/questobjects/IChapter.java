package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.Chapter;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.questtweaker.questobjects.IChapter")
public class IChapter extends IQuestObject<Chapter>{

    public IChapter(Chapter internal) {
        super(internal);
    }

    @ZenMethod
    @ZenGetter("quests")
    public List<IQuest> getQuests(){
        List<IQuest> quests = new ArrayList<>();
        this.internal.quests.forEach((quest)
                -> quests.add(new IQuest(quest)));
        return quests;
    }
}
