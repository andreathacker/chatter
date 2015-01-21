package ckc.chatter.data.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ckc.chatter.data.dao.base.BaseMessageDao;
import ckc.chatter.data.model.Message;

/**
 * Created by davidthacker on 1/8/15.
 */
public class MessageDao extends BaseMessageDao<Message> {

    public MessageDao() {
        super(new Message.MessageKeys());
    }

    @Override
    protected Class<Message> getClassType() {
        return Message.class;
    }

    @Override
    public JSONObject toJSONObject(Message message) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(keyDefinitions.key_id, message.id);
            jsonObject.put(keyDefinitions.key_message, message.message);
        } catch (JSONException je) {
            Log.w(message.getLogTag(), je);
            return null;
        }

        return jsonObject;
    }

    @Override
    public Message fromJSONObject(JSONObject jsonObject) {

        Message drawerItem = new Message();
        try {
            drawerItem.id = jsonObject.getLong(keyDefinitions.key_id);
            drawerItem.message = jsonObject.getString(keyDefinitions.key_message);
        } catch (JSONException je) {
            Log.w(drawerItem.getLogTag(), je);
            return null;
        }

        return drawerItem;
    }
}