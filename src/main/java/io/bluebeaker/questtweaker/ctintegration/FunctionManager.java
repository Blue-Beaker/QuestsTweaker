package io.bluebeaker.questtweaker.ctintegration;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.entity.player.EntityPlayer;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.HashMap;

@ZenClass("mods.queststweaker.FunctionManager")
@ZenRegister
public class FunctionManager {
    public static final HashMap<String, IQuestsFunction> FUNCTIONS = new HashMap<String, IQuestsFunction>();

    /** Function: IPlayer -> int. Negative value is ignored and can be used to cancel the function. */
    @ZenMethod
    public static void addFunction(String functionID,IQuestsFunction function){
        CraftTweakerAPI.apply(new AddFunctionAction(functionID, function));
    }

    public static IQuestsFunction getFunction(String functionID){
        return FUNCTIONS.get(functionID);
    }

    public static int runFunction(String functionID,EntityPlayer player){
        IQuestsFunction function = getFunction(functionID);
        if(function==null)
            return -1;
        return function.process(CraftTweakerMC.getIPlayer(player));
    }

    private static class AddFunctionAction implements IAction {

        private final String functionID;
        private final IQuestsFunction function;

        public AddFunctionAction(String functionID, IQuestsFunction function) {
            this.functionID = functionID;
            this.function = function;
        }

        @Override
        public void apply() {
            FUNCTIONS.put(functionID, function);
        }

        @Override
        public String describe() {
            return "Adding function with ID " + functionID;
        }
    }
}
