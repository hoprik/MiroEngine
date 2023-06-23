# MiroEngine

[![Minecraft](https://github.com/hoprik/MiroEngine/blob/information/minecraft_title.png)]()

[![GitHub issues](https://img.shields.io/github/issues/hoprik/MiroEngine)](https://github.com/hoprik/MiroEngine/issues)
[![GitHub stars](https://img.shields.io/github/stars/hoprik/MiroEngine)](https://github.com/hoprik/MiroEngine/stargazers)
[![wakatime](https://wakatime.com/badge/user/dfcbe794-c409-4097-a53e-aedc2d8b21d6/project/475de062-7bf0-41c8-8c61-e3049b8d6f47.svg)]()
[![documentation](https://img.shields.io/badge/documentation-8A2BE2)](https://hoprik.github.io/MiroDocumentation/)
[![discord](https://img.shields.io/discord/1107386749346398369)]()

## Description

MiroEngine is a library that provides functions for quickly writing storylines with characters and more. It is designed to be used with Minecraft versions 1.16.5 and 1.19.2. With this engine, you can:

1. Separate code from Minecraft ticks and continue through a button.
2. Work with characters.
3. Work with dialogs.
4. Work with story functions, such as chat messages and more.
5. Create NPCs.
6. Work with transitions.
7. Convert NBT structures to Java.

## Installation

To start using the story engine, follow these steps:

1. Download MiroEngine based on the desired version:
    - [1.16.5](https://github.com/hoprik/MiroEngine/tree/1.16.5)
    - [1.19.2](https://github.com/hoprik/MiroEngine/tree/1.19.2)
    - [2.0](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
2. Download the engine.
3. Open it in your preferred code editor (IntelliJ/Eclipse/VS Code).

# Example

```java
@Mod.EventBusSubscriber(modid = StoryMod.MODID)
public class Script {
    static boolean cooldown = false;
    static Player player;
    static Dialog dialog = new Dialog("Hello, I will spawn a mob. Do you want it?", new Bench[]{
        new Bench("Yes", new Dialog(1, (Serializable & Runnable) () -> {
            yes();
        })),
        new Bench("No", new Dialog(2, (Serializable & Runnable) () -> {
            no();
        }))
    });

    @SubscribeEvent
    public static void Test(BlockEvent.BreakEvent event) {
        player = event.getPlayer();
        dialog.show(event.getPlayer());
    }

    public static void yes() {
        Hero hero = new Hero(new NpcEntity(InitEntity.HOPRIK.get(), player.level), new BlockPos(0, -60, 0));
        hero.moveEntity(new Vector3d(9, -60, 9), 0.4F);
    }

    public static void no() {
        Hero hero = new Hero(new NpcEntity(InitEntity.YBLEDOK.get(), player.level), new BlockPos(0, -60, 0));
        hero.moveEntity(new Vector3d(9, -60, 9), 0.4F);
    }
}
```

## Usage Example

The above script is an example of using the **story engine**. It demonstrates event handling and the creation of interactive dialog scenarios. When

 interacting with a block, a dialog will be displayed, offering the player the choice of "Yes" or "No". Depending on the player's choice, corresponding actions will be executed.

## Contributing

If you have any ideas, suggestions, or would like to contribute to the development of this **engine**, feel free to make a pull request or create an issue on [GitHub](https://github.com/hoprik/MiroEngine/issues).



**by hoprik**
