package com.rjfun.cordova.ext;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import android.app.Activity;
import android.view.View;

public class CordovaPluginExt extends CordovaPlugin implements PluginAdapterDelegate {
	
	protected PluginAdapterDelegate adapter = null;
	
	public void setAdapter(PluginAdapterDelegate theAdapter) {
		adapter = theAdapter;
	}
	
	public PluginAdapterDelegate getAdapter() {
		return adapter;
	}
	
	@Override
	public View getView() {
		if(adapter != null) return adapter.getView();
		else return webView;
	}

	@Override
	public Activity getActivity() {
		if(adapter != null) return adapter.getActivity();
		return cordova.getActivity();
	}

	@Override
	public void fireEvent(String obj, String eventName, String jsonData) {
		if(adapter != null) adapter.fireEvent(obj, eventName, jsonData);
		else {
			String js;
			if("window".equals(obj)) {
				js = "var evt=document.createEvent('UIEvents');evt.initUIEvent('" + eventName + "',true,false,window,0);window.dispatchEvent(evt);";
			} else {
				js = "javascript:cordova.fireDocumentEvent('" + eventName + "'";
				if(jsonData != null) {
					js += "," + jsonData;
				}
				js += ");";
			}
			webView.loadUrl(js);
		}
	}

	@Override
	public void sendPluginResult(PluginResult result, CallbackContext context) {
		if(adapter != null) adapter.sendPluginResult(result, context);
		else {
			context.sendPluginResult(result);
		}
	}
}
