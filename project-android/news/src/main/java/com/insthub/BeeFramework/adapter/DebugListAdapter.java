package com.insthub.BeeFramework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.insthub.BeeFramework.model.DebugMessageModel;

import org.news.R;

public class DebugListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	
	public DebugListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return DebugMessageModel.messageList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return DebugMessageModel.messageList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.debug_message_item, null);
			holder.time = (TextView) convertView.findViewById(R.id.debug_item_time);
			holder.message = (TextView) convertView.findViewById(R.id.debug_item_message);
			holder.request = (TextView) convertView.findViewById(R.id.debug_item_request);
			holder.response = (TextView) convertView.findViewById(R.id.debug_item_response);
			holder.netSize = (TextView) convertView.findViewById(R.id.debug_item_netSize);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		int size = DebugMessageModel.messageList.size();
		DebugMessageModel.messageList.get(size-1-position).toString();
		holder.time.setText(DebugMessageModel.messageList.get(size-1-position).startTime);
		holder.message.setText(DebugMessageModel.messageList.get(size-1-position).message);
		holder.request.setText(DebugMessageModel.messageList.get(size-1-position).requset);
		holder.response.setText(DebugMessageModel.messageList.get(size-1-position).response);
		holder.netSize.setText(DebugMessageModel.messageList.get(size-1-position).netSize);
		
		return convertView;
	}
	
	class ViewHolder {
		private TextView time;
		private TextView message;
		private TextView request;
		private TextView response;
		private TextView netSize;
	}

}
