package ckc.chatter.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by davidthacker on 1/25/15.
 */
public interface MessageResponseCallback {

    public void onSuccess(JSONObject object);

    public void onError(VolleyError error);

}
