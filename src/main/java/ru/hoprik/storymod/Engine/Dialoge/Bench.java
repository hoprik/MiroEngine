package ru.hoprik.storymod.Engine.Dialoge;


import java.io.*;

public class Bench implements Serializable {
    public String playerSay;
    public Dialog function;

    public Bench(String playerSay, Dialog function){
        this.playerSay = playerSay;
        this.function = function;
    }

    public Bench(String end){
        this.playerSay = end;
    }

}
