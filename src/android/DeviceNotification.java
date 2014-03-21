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
package com.iniciacomunicacion.phonegap.plugin.devicenotification;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;//TODO
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.app.Notification;
import android.app.NotificationManager;

/**
 * This class provides access to Notification manager on the device.
 *
 * @author Inigo Gonzalez
 */
public class DeviceNotification extends CordovaPlugin {

	/**
	 * Constructor.
	 */
	public DeviceNotification() {
	}
 
	/**
	 * Executes the request and returns PluginResult.
	 *
	 * @param action            The action to execute ('add', 'cancel' or 'cancelAll').
	 * @param args              JSONArray of arguments for the plugin.
	 * @param callbackContext   The callback context used when calling back into JavaScript.
	 * @return                  True when the action was valid, false otherwise.
	 */
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        // Add notification
		if (action.equals("add")) {
			String title = args.getJSONObject(1).getString("title");
			String message = args.getJSONObject(1).getString("message");
            int id = args.getJSONObject(1).getInt("id");
			this.add(callbackContext, title, message, 0, id);

        // Cancel notification by ID
		} else if (action.equals("cancel")) {
			int id = args.getJSONObject(1).getInt("id");
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
	public void add(CallbackContext callbackContext, String title, String message, int smallIcon, int id) {

        //cordova.getActivity().getApplicationContext()
		NotificationManager notificationManager = (NotificationManager)cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification;
		notification = new Notification.Builder(cordova.getActivity())
		.setContentTitle(title)
		.setContentText(message)
		//.setSmallIcon(R.drawable.ic_launcher)
		//.setLargeIcon(R.drawable.ic_launcher)
		.build();

		//Notification notification = new Notification(R.drawable.ic_launcher, getString(R.string.app_name), System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND;
		//notification.setLatestEventInfo(this, title, message, contentIntent);
		notificationManager.notify(id, notification);
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