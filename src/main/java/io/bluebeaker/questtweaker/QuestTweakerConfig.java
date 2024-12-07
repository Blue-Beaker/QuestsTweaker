package io.bluebeaker.questtweaker;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = QuestTweakerMod.MODID,type = Type.INSTANCE,category = "general")
public class QuestTweakerConfig {
    @Config.Comment({
            "Make function IDs selectable from registered functions in FTBQuests.",
            "To input them manually, turn off this."})
    @Config.LangKey("config.questtweaker.enum_functionIDs")
    public static boolean enumFunctionIDs = false;
}