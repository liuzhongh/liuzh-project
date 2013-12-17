
package com.insthub.ecmobile.protocol;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "PRICE")
public class PRICE  extends Model
{

     @Column(name = "PRICE_id")
     public int id;

     @Column(name = "price")
     public String price;

     @Column(name = "rank_name")
     public String rank_name;

 public static PRICE fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return null;
      }

     PRICE   localItem = new PRICE();

     JSONArray subItemArray;

     localItem.id = jsonObject.optInt("id");

     localItem.price = jsonObject.optString("price");

     localItem.rank_name = jsonObject.optString("rank_name");
     return localItem;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("id", id);
     localItemObject.put("price", price);
     localItemObject.put("rank_name", rank_name);
     return localItemObject;
 }

}
