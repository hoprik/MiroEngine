package ru.hoprik.storymod.Engine.Dialoge;


public class Bench {
    public String playerSay;
    Runnable function;

    public Bench(String playerSay, Runnable function){
        this.playerSay = playerSay;
        this.function = function;
    }
}
