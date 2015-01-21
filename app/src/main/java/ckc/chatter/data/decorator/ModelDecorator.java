package ckc.chatter.data.decorator;

import ckc.chatter.data.model.Model;

/**
 * Created by davidthacker on 11/13/14.
 */
public abstract class ModelDecorator<T extends Model> implements Model {

    protected T model;

    public ModelDecorator(T model){
        this.model = model;
    }
}
