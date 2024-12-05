package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObject;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;

@ZenClass("mods.queststweaker.questobjects.IQuestObject")
@ZenRegister
public abstract class IQuestObject {
    public QuestObject questObject;

    @ZenMethod
    @ZenGetter("title")
    public String getTitle(){
        return this.questObject.getTitle();
    }

    @ZenMethod
    @ZenGetter("parentID")
    public int getParentID(){
        return this.questObject.getParentID();
    }

    @ZenMethod
    @ZenGetter("id")
    public int getID(){
        return this.questObject.id;
    }

}
