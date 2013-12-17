
package com.insthub.ecmobile.protocol;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "EXPRESS")
public class EXPRESS  extends Model
{

     @Column(name = "time")
     public String time;

     @Column(name = "context")
     public String context;

 public static EXPRESS fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return null;
      }

     EXPRESS   localItem = new EXPRESS();

     JSONArray subItemArray;

     localItem.time = jsonObject.optString("time");

     localItem.context = jsonObject.optString("context");
     return localItem;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("time", time);
     localItemObject.put("context", context);
     return localItemObject;
 }

}
