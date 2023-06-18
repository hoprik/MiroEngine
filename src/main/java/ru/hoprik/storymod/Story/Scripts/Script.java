package ru.hoprik.storymod.Story.Scripts;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Gecolib3.resource.GeckoLibCache;
import ru.hoprik.storymod.Story.Engine.CutsceneManager;
import ru.hoprik.storymod.Story.Engine.Executer.Executer;
import ru.hoprik.storymod.Story.Engine.StoryFunction;
import ru.hoprik.storymod.StoryMod;

@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class Script {
    @SubscribeEvent
    public static void ScriptCall(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player && !event.getLevel().isClientSide) {
            CutsceneManager cutsceneManager = new CutsceneManager();
            Executer button = new Executer();
            button.addB(() -> {StoryFunction.Message((Player) event.getEntity(), "her", "hhh"); cutsceneManager.show((Player) event.getEntity()); cutsceneManager.moveCamera(new BlockPos(0,-52,0), 5);});
            button.addB(() -> cutsceneManager.moveCamera(new BlockPos(10,-55,3), 2));
            button.addB(() -> cutsceneManager.exitCutscene((Player) event.getEntity()));
            button.addS(()-> GeckoLibCache.getInstance().getGeoModels().forEach((resourceLocation, geoModel) ->
                    StoryFunction.Message((Player) event.getEntity(), "test", resourceLocation.getNamespace()+":"+resourceLocation.getPath()+":"+geoModel.toString()+" her mod")), 5);
            button.addNullTaskS(2);
            button.addS(()-> StoryFunction.Message((Player) event.getEntity(), "her", "hhh"), 5);
            button.addB(() -> {StoryFunction.Message((Player) event.getEntity(), "her", "hhh"); cutsceneManager.show((Player) event.getEntity()); cutsceneManager.moveCamera(new BlockPos(0,-52,0), 5);});
            button.addB(() -> cutsceneManager.moveCamera(new BlockPos(10,-55,3), 2));
            button.addB(() -> cutsceneManager.exitCutscene((Player) event.getEntity()));
            button.exec();

        }
    }

    @SubscribeEvent
    public static void Block(BlockEvent.BreakEvent event){
        event.getLevel().playSound(event.getPlayer(), event.getPos(), SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 1,1);
//        if (!event.getLevel().isClientSide()){
//        Hero hero = new Hero(new NpcEntity(InitEntity.HOPRIK.get(), event.getPlayer().getLevel()));
//        hero.setTime();
////        hero.pickupOn();
//        //hero.giveItem();
//        hero.spawnEntity(event.getPlayer().getOnPos().above(2));
    }
}
