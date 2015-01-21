package ckc.chatter.data.model;

import com.orm.SugarRecord;

/**
 * Created by davidthacker on 1/8/15.
 */
public class Message extends SugarRecord<Model> implements Model {

    public long id;
    public String message;

    public Message(){

    }

    public Message(String message){
        this.message = message;
    }

    @Override
    public String getLogTag() {
        return Message.class.getSimpleName();
    }

    public static abstract class MessageKeyDefinitions implements KeyDefinitions{
        public String key_id;
        public String key_message;

        public MessageKeyDefinitions(String id, String message){
            this.key_id = id;
            this.key_message = message;
        }

    }

    public static class MessageKeys extends MessageKeyDefinitions{

        public MessageKeys(){
            super("id", "message");
        }
    }

}
