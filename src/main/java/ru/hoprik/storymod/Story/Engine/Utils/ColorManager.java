package ru.hoprik.storymod.Story.Engine.Utils;

public class ColorManager {
    public static int colorConvertForBG(int r, int g, int b, int alpha){
        int id = alpha;
        id += r * 256;
        id += g * 256 * 256;
        id += b * 256 * 256 * 256;
        return id;
    }
}
