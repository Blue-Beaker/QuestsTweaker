package io.bluebeaker.questtweaker.ctintegration.questobjects;

import com.feed_the_beast.ftbquests.quest.QuestObject;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.data.IData;
import crafttweaker.api.minecraft.CraftTweakerMC;
import io.bluebeaker.questtweaker.ctintegration.quests.IQuestData;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.questtweaker.questobjects.IQuestObject")
@ZenRegister
public abstract class IQuestObject<T extends QuestObject> extends IQuestObjectBase<T> {

    public IQuestObject(T internal) {
        super(internal);
    }

    @ZenMethod
    public IData getAsNBT(){
        NBTTagCompound nbt = new NBTTagCompound();
        this.getQuestObject().writeData(nbt);
        return CraftTweakerMC.getIData(nbt);
    }
    @ZenMethod
    public int getRelativeProgress(IQuestData data){
        return internal.getRelativeProgress(data.questData);
    }
    @ZenMethod
    public int getRelativeProgressFromChildren(IQuestData data){
        return internal.getRelativeProgressFromChildren(data.questData);
    }
    @ZenMethod
    public boolean isStarted(IQuestData data){
        return internal.isStarted(data.questData);
    }
    @ZenMethod
    public boolean isComplete(IQuestData data){
        return internal.isComplete(data.questData);
    }
    @ZenMethod
    public boolean isVisible(IQuestData data){
        return internal.isVisible(data.questData);
    }
}
