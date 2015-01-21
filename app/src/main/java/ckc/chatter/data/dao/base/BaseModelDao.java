package ckc.chatter.data.dao.base;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidthacker on 1/8/15.
 */
public abstract class BaseModelDao<T extends SugarRecord<?>> {

    private static final String TAG = BaseModelDao.class.getSimpleName();

    protected List<T> modelList = new ArrayList<T>();

    public List<T> getModelList(){
        return modelList;
    }

    /**
     * Clear the cache of models from this DAO
     */
    public void clearModels() {
        modelList.clear();
    }

    /**
     * Save models in the modelList into the database
     *
     * @param clearModelsAfterSave
     */
    public void saveModels(boolean clearModelsAfterSave) {
        T.saveInTx(modelList);

        if (clearModelsAfterSave) {
            clearModels();
        }
    }

    /**
     * Delete models contained in the modelList from the database
     *
     * @param clearModelsAfterDeletion
     */
    public void deleteModels(boolean clearModelsAfterDeletion) {
        for (int i = 0; i < modelList.size(); i++) {
            modelList.get(i).delete();
        }

        if (clearModelsAfterDeletion) {
            clearModels();
        }
    }

    /**
     * Deletes all of the models from this model's table
     */
    public void deleteAll(){
        T.deleteAll(getClassType());
    }

    protected abstract Class<T> getClassType();

    /**
     * Return a JSONObject representation of the Model contained by the modelList at the provided inded.
     *
     * @param index of the Mode contained in the modelList
     * @return JSONObject representation of the Model
     */
    public JSONObject toJSONObject(int index) {
        if (modelList.size() < index) {
            return null;
        }

        return toJSONObject(modelList.get(index));
    }


    /**
     * Convert a model to a JSONObject representation
     *
     * @param model Model
     * @return JSONObject representation of the model
     */
    public abstract JSONObject toJSONObject(T model);

    /**
     * Get the full modelList of Models as a JSONArray
     *
     * @return JSONArray containing JSONObject representations of each Model in the modelList
     */
    public JSONArray toJSONArray() {

        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < modelList.size(); i++) {
            jsonArray.put(toJSONObject(modelList.get(i)));
        }

        return jsonArray;
    }

    /**
     * Parse a JSONObject into a Model
     *
     * @param jsonObject the JSON data to place into the Model
     * @return a Model populated with the JSONObject's data
     */
    public abstract T fromJSONObject(JSONObject jsonObject);

    /**
     * Parse a JSONArray of JSONObjects that represent Models into the modelList
     *
     * @param jsonArray
     * @return
     */
    public List<T> fromJSONArray(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                modelList.add(fromJSONObject((JSONObject) jsonArray.get(i)));
            } catch (JSONException je) {
                Log.w(TAG, je);
            } catch (ClassCastException cce) {
                Log.w(TAG, cce);
            }
        }

        return modelList;
    }
}