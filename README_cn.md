# QuestsTweaker
用于联动 FTBQuests 与 CraftTweaker 的模组.   

## 功能
- 对于已存在的任务, 用CraftTweaker增加/设置其进度.  
- 新的 '函数' 任务类型, 通过执行 CraftTweaker 函数来检查进度.  
- 新的 '函数' 奖励类型, 完成时执行 CraftTweaker 函数.  

## CraftTweaker API
### mods.questtweaker.QuestManager
对于任意可用任务, 用CraftTweaker增加/设置其进度:  
当任务不可用时(如前置未完成),此函数不起作用.  
`QuestManager.addTaskProgress(IPlayer player, string/int id, long progress);`
`QuestManager.setTaskProgress(IPlayer player, string/int id, long progress);`
```
import mods.questtweaker.QuestManager;
// Add progress
// IPlayer player, string/int id, long progress
QuestManager.addTaskProgress(event.player,"4c4bd563",1 as long);
// Or use hex int
QuestManager.addTaskProgress(event.player,0x4c4bd563,1 as long);
// Set progress
// IPlayer player, string/int id, long progress
QuestManager.setTaskProgress(event.player,"4c4bd563",1 as long);
```  
例子: 当玩家捡起物品, 将任务进度设为捡起的物品堆叠数:  
```
import mods.questtweaker.QuestManager;
events.onPlayerPickupItem(function(event as crafttweaker.event.PlayerPickupItemEvent){
    QuestManager.setTaskProgress(event.player,"52725826",event.item.item.amount as long);
});
```

### mods.questtweaker.FunctionManager
添加一个新函数, 用于本模组的'函数'任务和奖励:  
`FunctionManager.addFunction(string functionID, function(IPlayer) -> long);`  
函数必须返回 __long__ 类型.  
```
import mods.queststweaker.FunctionManager;
import crafttweaker.player.IPlayer;
// string functionID, function(IPlayer) -> long
FunctionManager.addFunction("height",function(player as IPlayer){
    return player.y as long;
});
```
示例 2: 在铁路上 [on_a_rail.zs](Examples/on_a_rail.zs)  

## FTBQuests 集成
### 函数任务
周期性执行一个函数, 将其返回值作为任务进度.  
参数:  
- 函数 ID: 脚本中注册的函数 ID.  
- 值: 要完成任务, 进度需要达到此值.  
- 间隔: 执行函数的间隔. 设置为 0 以使用手动提交.  

### 函数奖励
收集时执行函数.  
参数:  
- 函数 ID: 脚本中注册的函数 ID.  

### 虚拟任务
自身没有作用. 目的是搭配 QuestManager 使用.  
参数:  
- 值: 要完成任务, 进度需要达到此值.  