
package org.news.protocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "PAGINATION")
public class PAGINATION  extends Model
{

     @Column(name = "count")
     public int count;

     @Column(name = "page")
     public int page;

 public static PAGINATION fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return null;
      }

     PAGINATION   localItem = new PAGINATION();

     JSONArray subItemArray;

     localItem.count = jsonObject.optInt("count");
     localItem.page = jsonObject.optInt("page");
     return localItem;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("count", count);
     localItemObject.put("page", page);
     return localItemObject;
 }

}
