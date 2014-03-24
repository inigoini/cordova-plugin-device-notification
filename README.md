# phonegap-plugin-deviceNotifications

- PhoneGap Version : 3.0
- last update : 01/10/2013

# Description

PhoneGap plugin for providing native local notification system to JavaScript.

The plugin runs on Android version 2.x+


** NOTE: Notification queuing is iOS ONLY.**

# Credits

Inigo Gonzalez 2014 - Copyright (c) GPL Licensed

## Install (with Plugman) 

iOS (not ready)
	
	cordova plugin add https://github.com/inigoini/cordova-plugin-device-notification
	build ios
	
	< or >
	
	phonegap local plugin add https://github.com/inigoini/cordova-plugin-device-notification
	phonegap build ios

Android
	
	cordova plugin add https://github.com/inigoini/cordova-plugin-device-notification
	build android
	
	< or >
	
	phonegap local plugin add https://github.com/inigoini/cordova-plugin-device-notification
	phonegap build android


## APIs

### Add a notification

	deviceNotification.add( JSONObject options );

** NOTE: Adding a notification with the same id stops the notification and adds another. **

	{
    	id: 30,
    	ticker: 'ticker title' 
    	title: 'Notification title',
    	message: "New notification!!!!"
	}; 
	
### Cancel a notification

	deviceNotification.cancel( int id ); 


### Cancel all notifications

	deviceNotification.cancelAll(); 
