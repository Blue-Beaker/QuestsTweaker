package io.bluebeaker.questtweaker.ctintegration;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.player.IPlayer;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass("mods.queststweaker.ITaskFunction")
@ZenRegister
public interface IQuestsFunction {
    int process(IPlayer player);
}
