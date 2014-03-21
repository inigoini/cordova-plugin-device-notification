# phonegap-plugin-deviceNotifications

- PhoneGap Version : 3.0
- last update : 01/10/2013

# Description

PhoneGap plugin for providing native local notification system to JavaScript.

** NOTE: Notification queuing is iOS ONLY.**

# Credits

Inigo Gonzalez 2014 - Copyright (c) GPL Licensed

## Install (with Plugman) 

iOS
	
	cordova plugin add https://github.com/Wizcorp/phonegap-plugin-notifications
	build ios
	
	< or >
	
	phonegap local plugin add https://github.com/Wizcorp/phonegap-plugin-deviceNotifications
	phonegap build ios

Android
	
	cordova plugin add https://github.com/Wizcorp/phonegap-plugin-deviceNotifications
	build android
	
	< or >
	
	phonegap local plugin add https://github.com/Wizcorp/phonegap-plugin-deviceNotifications
	phonegap build android


## APIs

### Add a notification

	deviceNotification.add(Int id, JSONObject options);

** NOTE: Adding a notification with the same id stops the notification and adds another. **

	{
    	id: 30,
    	title: 'Notification title',
    	message: "New notification!!!!"
	}; 
	
### Cancel a notification

	deviceNotification.cancel(Int id); 


### Cancel all notifications

	deviceNotification.cancelAll(); 

