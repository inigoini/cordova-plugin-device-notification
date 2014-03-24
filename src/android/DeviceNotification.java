/**
 * This file is part of Notification plugin for Cordova
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.iniciacomunicacion.devicenotification;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.app.Activity;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;

/**
 * This class provides access to Notification manager on the device.
 *
 * @author Inigo Gonzalez
 */
public class DeviceNotification extends CordovaPlugin {

	//private static CordovaWebView webView = null;
	protected static Activity activity = null; 
	protected static Context context = null;
	
	/**
	 * Constructor.
	 */
	public DeviceNotification() {
	}
 
	@Override
	public void initialize (CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

		//DeviceNotification.webView = super.webView;
		DeviceNotification.activity = super.cordova.getActivity();
		DeviceNotification.context = super.cordova.getActivity().getApplicationContext();
	}

	/**
	 * Executes the request and returns PluginResult.
	 *
	 * @param action			The action to execute ('add', 'cancel' or 'cancelAll').
	 * @param args			  JSONArray of arguments for the plugin.
	 * @param callbackContext   The callback context used when calling back into JavaScript.
	 * @return				  True when the action was valid, false otherwise.
	 */
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		// Add notification
		if (action.equals("add")) {
			String ticker	= args.getJSONObject(0).getString("ticker");
			String title	= args.getJSONObject(0).getString("title");
			String message	= args.getJSONObject(0).getString("message");
			int id			= args.getJSONObject(0).getInt("id");
			this.add(callbackContext, ticker, title, message, id);

		// Cancel notification by ID
		} else if (action.equals("cancel")) {
			int id = args.getJSONObject(0).getInt("id");
			this.cancel(callbackContext, id);

		// Cancel all notifications
		} else if (action.equals("cancelAll")) {
			this.cancelAll(callbackContext);
		} else {
			return false;
		}
		 // Only alert and confirm are async.
		 callbackContext.success();
		 return true;
	}

	/**
	 * Adds notification
	 * 
	 * @param callbackContext, Callback context of the request from Cordova
	 * @param title, The title of notification
	 * @param message, The content text of the notification
	 * @param Id, The unique ID of the notification
	 * @param seconds
	 */
	public void add(CallbackContext callbackContext, String ticker, String title, String message, int id) {

		Resources res = DeviceNotification.context.getResources();
		int ic_launcher = res.getIdentifier("icon", "drawable", cordova.getActivity().getPackageName());
		
		/* Version 4.x
		NotificationManager notificationManager = (NotificationManager)DeviceNotification.activity.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification.Builder(DeviceNotification.Context)
		.setTicker(ticker)
		.setContentTitle(title)
		.setContentText(message)
		.setSmallIcon(ic_launcher)
		.setWhen(System.currentTimeMillis())
		.setAutoCancel(true)
		.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND;
		notificationManager.notify(id, notification);*/
		
		//Version 2.x
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(DeviceNotification.activity)
		.setSmallIcon(ic_launcher)
		.setWhen(System.currentTimeMillis())
		.setContentTitle(title)
		.setContentText(message)
			.setTicker(ticker);
		Intent resultIntent = new Intent(DeviceNotification.activity, DeviceNotification.activity.getClass());
		PendingIntent resultPendingIntent = PendingIntent.getActivity(DeviceNotification.activity, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotifyMgr = (NotificationManager)DeviceNotification.activity.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		mNotifyMgr.notify(id, mBuilder.build());
	}

	/**
	 * Cancel a specific notification that was previously registered.
	 * 
	 * @param callbackContext, Callback context of the request from Cordova
	 * @param id, notification id registered using add()
	 * @see add
	 */
	public void cancel(CallbackContext callbackContext, int id) {
		NotificationManager notificationManager = (NotificationManager)cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(id);
	}

	/**
	 * Cancels all notifications
	 *
	 * @param callbackContext Callback context of the request from Cordova
	 */
	public void cancelAll(CallbackContext callbackContext) {
		NotificationManager notificationManager = (NotificationManager)cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}
}