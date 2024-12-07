package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObjectBase;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.IQuestObject")
@ZenRegister
public abstract class IQuestObject {

    public abstract QuestObjectBase getQuestObject();

    @ZenMethod
    @ZenGetter("title")
    public String getTitle(){
        return this.getQuestObject().getTitle();
    }

    @ZenMethod
    @ZenGetter("parentID")
    public int getParentID(){
        return this.getQuestObject().getParentID();
    }

    @ZenMethod
    @ZenGetter("id")
    public int getID(){
        return this.getQuestObject().id;
    }

}
