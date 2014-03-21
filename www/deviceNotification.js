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

var exec = require("cordova/exec");

if (window.cordova) {
	window.document.addEventListener("deviceready", function () {
		//exec(null, null, "DeviceNotification", "ready", []);
	}, false);
}

var DeviceNotification = function () {};

DeviceNotification.prototype.add = function ( options ) {
	exec(null, null, "DeviceNotification", "add", [options]);
};

DeviceNotification.prototype.cancel = function ( id ) {
	exec(null, null, "DeviceNotification", "cancel", [id]);
};
	
DeviceNotification.prototype.cancelAll = function () {
	exec(null, null,"DeviceNotification", "cancelAll", []);
};


// Instantiate DeviceNotification
window.deviceNotification = new DeviceNotification();
module.exports = deviceNotification;
