package ckc.chatter.data.dao.base;

import ckc.chatter.data.model.Message;

/**
 * Created by davidthacker on 1/8/15.
 */
public abstract class BaseMessageDao<T extends Message> extends BaseModelDao<T> {

    protected Message.MessageKeyDefinitions keyDefinitions;

    public BaseMessageDao(Message.MessageKeyDefinitions keyDefinitions){
        this.keyDefinitions = keyDefinitions;
    }
}