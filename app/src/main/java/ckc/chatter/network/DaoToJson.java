package ckc.chatter.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ckc.chatter.data.dao.base.BaseModelDao;
import ckc.chatter.data.model.Model;

/**
 * Created by davidthacker on 1/20/15.
 */
public class DaoToJson {

    private static final String TAG = DaoToJson.class.getSimpleName();

    private BaseModelDao<? extends Model> mDao;
    private List<? extends Model> mModelList;

    public DaoToJson(BaseModelDao<? extends Model> dao) {
        mDao = dao;
        mModelList = mDao.getModelList();
    }

    public JSONObject convertSingleModelToJson() {

        if (mModelList.size() < 1) {
            Log.w(TAG, "A model was requested from a empty model list");
            return null;
        } else if (mModelList.size() != 1) {
            Log.w(TAG, "WARNING: Requesting single model from a list that has multiple models");
        }

        return convertModelToJson(0);
    }

    public JSONObject convertModelToJson(int modelIndex) {

        if (modelIndex > mModelList.size()) {
            return null;
        }

        JSONObject jsonObject = parseModel(mModelList.get(modelIndex));
        return jsonObject;
    }

    public JSONObject parseModel(Model model) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonString = gson.toJson(model);
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.w(TAG, e);
            return null;
        }
    }


}
