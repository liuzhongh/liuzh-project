
package com.insthub.ecmobile.protocol;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "ORDER_GOODS_LIST")
public class ORDER_GOODS_LIST  extends Model
{

     @Column(name = "goods_number")
     public String goods_number;

     @Column(name = "goods_id")
     public String goods_id;

     @Column(name = "name")
     public String name;

     @Column(name = "img")
     public PHOTO   img;

     @Column(name = "formated_shop_price")
     public String formated_shop_price;

     @Column(name = "subtotal")
     public String subtotal;

 public static ORDER_GOODS_LIST fromJson(JSONObject jsonObject)  throws JSONException
 {
     if(null == jsonObject){
       return null;
      }

     ORDER_GOODS_LIST   localItem = new ORDER_GOODS_LIST();

     JSONArray subItemArray;

     localItem.goods_number = jsonObject.optString("goods_number");

     localItem.goods_id = jsonObject.optString("goods_id");

     localItem.name = jsonObject.optString("name");
     localItem.img = PHOTO.fromJson(jsonObject.optJSONObject("img"));

     localItem.formated_shop_price = jsonObject.optString("formated_shop_price");

     localItem.subtotal = jsonObject.optString("subtotal");
     return localItem;
 }

 public JSONObject  toJson() throws JSONException 
 {
     JSONObject localItemObject = new JSONObject();
     JSONArray itemJSONArray = new JSONArray();
     localItemObject.put("goods_number", goods_number);
     localItemObject.put("goods_id", goods_id);
     localItemObject.put("name", name);
     if(null!=img)
     {
       localItemObject.put("img", img.toJson());
     }
     localItemObject.put("formated_shop_price", formated_shop_price);
     localItemObject.put("subtotal", subtotal);
     return localItemObject;
 }

}
