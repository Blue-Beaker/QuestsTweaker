package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObjectBase;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.IQuestObjectBase")
@ZenRegister
public abstract class IQuestObjectBase<T extends QuestObjectBase> {
    public final T internal;

    public IQuestObjectBase(T internal) {
        this.internal = internal;
    }

    public T getQuestObject(){
        return internal;
    };

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
