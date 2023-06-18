package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.util;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;

public class Auxiliary {
    public static Message makeLogMessage(String message, Object... arguments) {
        return new ParameterizedMessage(message, arguments);
    }
}