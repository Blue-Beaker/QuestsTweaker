package io.bluebeaker.questtweaker.ctintegration;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.player.IPlayer;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass("mods.questtweaker.ITaskFunction")
@ZenRegister
public interface IQuestsFunction {
    long process(IPlayer player);
}
