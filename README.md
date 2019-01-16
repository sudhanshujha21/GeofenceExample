
 1. INTRODUCTION 
 
GeoFencing is a boundary or region of interest in the geographical region.Geo-fencing is used for many applications and it provides many benefits to users. One of the major applications for Geofencing is security, when anyone enters or leaves a particular area, an alert or text messages has been sent to the user . In military applications this can be used, when the enemy vehicles enter into our boundary it gives an alarm sound to alert the officers and takes necessary actions. 
 Geofence is also a solution to the commonly occurring problems. For example- It happens that the person is unaware of the blood bank in his area where he can find the required blood at that time, thus he may have to travel far and waste his time. So to overcome this problem geofence technology can be used, where he would specify his range of travel(geofencing radius) and will be able to see all the blood banks within his geofence radius just on his mobile phone so that his time is not wasted. 
 
Geofencing combines awareness of current user's location with awareness of the user's proximity Geofencing combines awareness of current user's location with awareness of the user's proximity to locations which can be specified 
 
using its latitude and longitude. Radius is added to adjust the proximity for the location.Hence to define a 
 
geofence,i.e. creating a circular area, or fence, around the location of interest , the radius, longitude, latitude are required[3].One can have multiple geofences active at a time,with a limit of 100 per device. 
 
 


Geofence on Android: 
 
On Android, there are different ways to deal with geofences. One can use Google’s GeofencingApi. This API is component of Google’s Location Apis which has GeofencingRequest, GeofenceApi, Geofence, and GeofencingEvents.. The GeofencingApi class is the entry point for all interactions with Google's geofencing API. The GeofencingApi can be used to add geofence by calling addGeofence() method and remove the geofences by calling removeGeofences() method. Now to check whether a person is within a geofence range we can make use of different   algorithms such as Ray-casting,   Winding Number ,TWC   (Triangle   Weight   Characterization)   and Circular Geofencing using Haversine  Formula.
 

