package com.shangkang.android;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.shangkang.android.constant.Constant;
import com.shangkang.bo.Sku;
import com.shangkang.dto.ProductDetailDto;
import com.shangkang.dto.SkuDto;
import com.shangkang.helper.CacheHelper;
import com.shangkang.tools.UtilHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends ArrayAdapter<ProductDetailDto> {

    private static final String TAG = "ProductAdapter";
    private LayoutInflater mLayoutInflater;
	private int resourceId;

	public ProductAdapter(Context context, int textViewResourceId,
			List<ProductDetailDto> objects) {
		super(context, textViewResourceId, objects);

		// 获取LayoutInflater 服务,用来从预定义的xml布局创建view对象.
		this.resourceId = textViewResourceId;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

//		if (convertView == null) {
			// 创建新的view视图.
			convertView = mLayoutInflater.inflate(resourceId, parent, false);
//		}

        Log.i(TAG, " position ======== " + position);
		// 获取当前要显示的数据
        ProductDetailDto dto = getItem(position);

		TextView skuName = (TextView) convertView.findViewById(R.id.skuName);
		TextView factoryName = (TextView) convertView.findViewById(R.id.factoryName);
//		TextView maxSale = (TextView) convertView.findViewById(R.id.maxSale);
		TextView skwPrice = (TextView) convertView.findViewById(R.id.skwPrice);
//		TextView unit = (TextView) convertView.findViewById(R.id.unit);
		ImageView img = (ImageView) convertView.findViewById(R.id.iView);

		String imgUrl = dto.getMainFileUpload().getUrl();
		img.setImageBitmap(null);

		if (!UtilHelper.isEmpty(imgUrl)) {
			//异步加载图片
			LoadImage task = new LoadImage(img);
			
			task.execute(imgUrl);
		}

		skuName.setText(dto.getProduct().getProductName());
		factoryName.setText("生产厂家:" + dto.getFactory().getFactoryName());
//		maxSale.setText(String.valueOf(sku.getMaxSale()) + sku.getUnit());
		skwPrice.setText("价格:" + String.valueOf(dto.getShowPrice()) + "元");
//		unit.setText(sku.getUnit());

		return convertView;
	}

	class LoadImage extends AsyncTask<String, Void, Bitmap> {

		private ImageView view;

		public LoadImage(ImageView view) {
			this.view = view;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			view.setImageBitmap(result);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String imgUrl = params[0];

			InputStream is = null;
			URLConnection conn = null;
			Bitmap bitmap = null;
			try {
				if (!UtilHelper.isEmpty(imgUrl)) {

                    SoftReference softBitmap = (SoftReference) CacheHelper.getSingleton().getCache().get(imgUrl);

                    if (softBitmap != null)
                        bitmap = (Bitmap) softBitmap.get();
                    else
                    {
                        URL url = new URL(Constant.REMOTE_URL + imgUrl);

                        // 取得链接
                        conn = url.openConnection();
                        conn.connect();
                        // 打开盖URL对应的资源的输入流
                        is = conn.getInputStream();
                        // 从inputstream中解析图片
                        bitmap = BitmapFactory.decodeStream(is);
                        CacheHelper.getSingleton().getCache().put(imgUrl, new SoftReference<Object>(bitmap));
                    }
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
//				e.printStackTrace();
			} finally {
				try {
					if (null != is)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return bitmap;
		}

	}

}
