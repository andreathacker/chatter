package ckc.chatter.data.decorator;

import ckc.chatter.data.model.KeyDefinitions;
import ckc.chatter.data.model.Message;

/**
 * Created by davidthacker on 1/8/15.
 */
public class MessageDecorator extends ModelDecorator {


    public MessageDecorator(Message model) {
        super(model);
    }

    public static class MessageDecoratorKeys extends Message.MessageKeyDefinitions{
        public MessageDecoratorKeys(String id, String message) {
            super(id, message);
        }
    }
}
