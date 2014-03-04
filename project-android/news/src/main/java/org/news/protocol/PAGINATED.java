
package org.news.protocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "PAGINATED")
public class PAGINATED  extends Model
{

     @Column(name = "total")
     public int total;

     @Column(name = "more")
     public int more;

     @Column(name = "count")
     public int count;

 public static PAGINATED fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return null;
      }

     PAGINATED   localItem = new PAGINATED();

     JSONArray subItemArray;

     localItem.total = jsonObject.optInt("total");

     localItem.more = jsonObject.optInt("more");

     localItem.count = jsonObject.optInt("count");
     return localItem;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("total", total);
     localItemObject.put("more", more);
     localItemObject.put("count", count);
     return localItemObject;
 }

}
