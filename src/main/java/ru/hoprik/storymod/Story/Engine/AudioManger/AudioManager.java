//package ru.hoprik.storymod.Story.Engine.AudioManger;
//
//import com.mojang.blaze3d.audio.Library;
//import net.minecraft.SharedConstants;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.resources.sounds.Sound;
//import net.minecraft.client.resources.sounds.SoundInstance;
//import net.minecraft.client.resources.sounds.TickableSoundInstance;
//import net.minecraft.client.sounds.ChannelAccess;
//import net.minecraft.client.sounds.SoundEventListener;
//import net.minecraft.client.sounds.SoundManager;
//import net.minecraft.client.sounds.WeighedSoundEvents;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.packs.resources.Resource;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.util.RandomSource;
//import net.minecraft.util.valueproviders.SampledFloat;
//import net.minecraft.world.phys.Vec3;
//import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util.ResourceUtils;
//import ru.hoprik.storymod.Story.Engine.Network.Network;
//import ru.hoprik.storymod.StoryMod;
//
//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//import java.util.Optional;
//import java.util.concurrent.CompletableFuture;
//
//import static net.minecraft.client.sounds.SoundEngine.ONLY_WARN_ONCE;
//import static net.minecraft.client.sounds.SoundEngine.shouldLoopAutomatically;
//
//public class AudioManager {
//
//    public void play(Sound sound) {
//                WeighedSoundEvents weighedsoundevents = p_120313_.resolve(this.soundManager);
//                ResourceLocation resourcelocation = p_120313_.getLocation();
//                if (sound == SoundManager.EMPTY_SOUND) {
//                    StoryMod.logger.warn("Unable to play empty soundEvent: {}", (Object)resourcelocation);
//                    } else {
//
//                                boolean flag2 = shouldLoopAutomatically(z);
//                                boolean flag3 = sound.shouldStream();
//                                CompletableFuture<ChannelAccess.ChannelHandle> completablefuture = this.channelAccess.createHandle(sound.shouldStream() ? Library.Pool.STREAMING : Library.Pool.STATIC);
//                                ChannelAccess.ChannelHandle channelaccess$channelhandle = completablefuture.join();
//                                if (channelaccess$channelhandle == null) {
//                                    if (SharedConstants.IS_RUNNING_IN_IDE) {
//                                        LOGGER.warn("Failed to create new sound handle");
//                                    }
//
//                                } else {
//                                    LOGGER.debug(MARKER, "Playing sound {} for event {}", sound.getLocation(), resourcelocation);
//                                    this.soundDeleteTime.put(p_120313_, this.tickCount + 20);
//                                    this.instanceToChannel.put(p_120313_, channelaccess$channelhandle);
//                                    this.instanceBySource.put(soundsource, p_120313_);
//                                    channelaccess$channelhandle.execute((p_194488_) -> {
//                                        p_194488_.setPitch(f3);
//                                        p_194488_.setVolume(f2);
//                                        if (soundinstance$attenuation == SoundInstance.Attenuation.LINEAR) {
//                                            p_194488_.linearAttenuation(f1);
//                                        } else {
//                                            p_194488_.disableAttenuation();
//                                        }
//
//                                        p_194488_.setLooping(flag2 && !flag3);
//                                        p_194488_.setSelfPosition(vec3);
//                                        p_194488_.setRelative(flag);
//                                    });
//                                    final SoundInstance soundinstance = p_120313_;
//                                    if (!flag3) {
//                                        this.soundBuffers.getCompleteBuffer(sound.getPath()).thenAccept((p_194501_) -> {
//                                            channelaccess$channelhandle.execute((p_194495_) -> {
//                                                p_194495_.attachStaticBuffer(p_194501_);
//                                                p_194495_.play();
//                                                net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.sound.PlaySoundSourceEvent(this, soundinstance, p_194495_));
//                                            });
//                                        });
//                                    } else {
//                                        soundinstance.getStream(this.soundBuffers, sound, flag2).thenAccept((p_194504_) -> {
//                                            channelaccess$channelhandle.execute((p_194498_) -> {
//                                                p_194498_.attachBufferStream(p_194504_);
//                                                p_194498_.play();
//                                                net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.sound.PlayStreamingSourceEvent(this, soundinstance, p_194498_));
//                                            });
//                                        });
//                                    }
//
//                                    if (p_120313_ instanceof TickableSoundInstance) {
//                                        this.tickingSounds.add((TickableSoundInstance)p_120313_);
//                                    }
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
