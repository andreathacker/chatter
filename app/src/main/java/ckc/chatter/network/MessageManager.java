package ckc.chatter.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davidthacker on 1/25/15.
 */
public class MessageManager {

    private static String TAG = MessageManager.class.getSimpleName();

    private static String MESSAGE_ENDPOINT = "http://chattime.herokuapp.com/messages/";

    private static RequestQueue sRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(context);
        }

        return sRequestQueue;
    }

    public static void createGetMessagesRequest(Context context, final MessageResponseCallback callback) {

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, MESSAGE_ENDPOINT,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(convertStringToJSON(response));
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });

        // Add the request to the RequestQueue.
        getRequestQueue(context).add(stringRequest);

    }

    private static JSONObject convertStringToJSON(String string) {

        JSONObject object = null;
        try {
            object = new JSONObject(string);

        } catch (JSONException je) {
            Log.w(TAG, je);
        }

        return object;
    }

}
