
 1. INTRODUCTION 
 
GeoFencing is a boundary or region of interest in the geographical region.Geo-fencing is used for many applications and it provides many benefits to users. One of the major applications for Geofencing is security, when anyone enters or leaves a particular area, an alert or text messages has been sent to the user . In military applications this can be used, when the enemy vehicles enter into our boundary it gives an alarm sound to alert the officers and takes necessary actions. 
 Geofence is also a solution to the commonly occurring problems. For example- It happens that the person is unaware of the blood bank in his area where he can find the required blood at that time, thus he may have to travel far and waste his time. So to overcome this problem geofence technology can be used, where he would specify his range of travel(geofencing radius) and will be able to see all the blood banks within his geofence radius just on his mobile phone so that his time is not wasted. 
 
Geofencing combines awareness of current user's location with awareness of the user's proximity Geofencing combines awareness of current user's location with awareness of the user's proximity to locations which can be specified 
 
using its latitude and longitude. Radius is added to adjust the proximity for the location.Hence to define a 
 
geofence,i.e. creating a circular area, or fence, around the location of interest , the radius, longitude, latitude are required[3].One can have multiple geofences active at a time,with a limit of 100 per device. 
 
 


Geofence on Android: 
 
On Android, there are different ways to deal with geofences. One can use Google’s GeofencingApi. This API is component of Google’s Location Apis which has GeofencingRequest, GeofenceApi, Geofence, and GeofencingEvents.. The GeofencingApi class is the entry point for all interactions with Google's geofencing API. The GeofencingApi can be used to add geofence by calling addGeofence() method and remove the geofences by calling removeGeofences() method. Now to check whether a person is within a geofence range we can make use of different   algorithms such as Ray-casting,   Winding Number ,TWC   (Triangle   Weight   Characterization)   and Circular Geofencing using Haversine  Formula.

Some important link on this concept:


1.https://developer.android.com/training/location/geofencing#java 

2.https://developers.google.com/android/reference/com/google/android/gms/maps/model/Circle 

3.https://www.raywenderlich.com/7372-geofencing-api-tutorial-for-android 

4.https://code.tutsplus.com/tutorials/how-to-work-with-geofences-on-android--cms-26639 

5.https://github.com/EmmanuelCorrales/polygon-geofences-android

6.https://github.com/deckameron/Ti.Android.Geofence

7.https://github.com/vhoen/Android-Geofence-21

8.https://github.com/DanishAmjad12/Geofence-Android-Demo

9.https://3en.cloud/insight/2018/4/24/setting-up-geofencing-with-notifications-on-android

10.http://www.coderzheaven.com/2016/06/20/geofencing-in-android-a-simple-example/

11.https://stackoverflow.com/questions/14372947/android-get-notification-when-user-enter-in-specific-location

12.https://www.raywenderlich.com/7372-geofencing-api-tutorial-for-android

13.https://www.raywenderlich.com/1773-geofences-on-android-with-googleapiclient

14.https://abhiandroid.com/programming/googlemaps

15.https://github.com/googlemaps/android-samples

16.https://www.digit.in/apps/how-to-implement-map-and-geofence-features-in-android-business-apps-23443.html

17.https://software.intel.com/en-us/android/articles/implementing-map-and-geofence-features-in-android-business-apps

18.https://github.com/gusterwoei/Android-Geofence-Example

19.github.com/googlesamples/android-Geofencing/

20.http://www.zoftino.com/android-location-proximity-alert-using-google-maps-geofencing-example

21.https://stackoverflow.com/questions/37139929/geofence-alert-does-not-show

22.https://developers.google.com/location-context/geofencing/

23.https://www.programcreek.com/java-api-examples/index.php?api=com.google.android.gms.location.Geofence

24.https://www.programcreek.com/java-api-examples/?Query=android&action=search_project&submit=Search
25.http://www.zoftino.com/android

26.https://docs.huihoo.com/android/4.4/training/location/geofencing.html

27.https://en.proft.me/2017/06/6/geographical-boundaries-geofencing-android/

28.https://github.com/Manuaravind1989/GeofencingDemo

29.https://www.cs.dartmouth.edu/~campbell/cs65/cs65.html

30.https://www.cs.dartmouth.edu/~campbell/cs65/lecture17/lecture17.html

31. https://developers.google.com/maps/documentation/android-sdk/styling



Some improtant link for push notification:



1.https://developer.android.com/training/notify-user/build-notification#java

2.https://developer.android.com/training/notify-user/build-notification

3.https://pulsate.readme.io/v6.0/docs/adding-polgonal-geofences

4.http://stefanbangels.blogspot.com/2013/10/geo-fencing-sample-code.html

5 http://alienryderflex.com/polygon/

6.http://vinsol.com/blog/2016/08/04/ray-casting-algorithm/

7.https://gis.stackexchange.com/questions/240297/looking-for-a-us-state-file-for-geofences-with-as-few-geofences-per-state-as-pos

8.https://www.journaldev.com/15468/android-notification-styling

9.https://www.intertech.com/Blog/android-nougat-development-tutorial-part-2-nougat-bundled-notifications/

10.https://www.techotopia.com/index.php/An_Android_7_Notifications_Tutorial

11.https://stackoverflow.com/questions/33209501/what-is-the-difference-between-v4-app-notificationcompat-and-v7-app-notification/33209603

12.https://developer.android.com/reference/android/app/Notification.Builder

13.https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder

14.https://www.journaldev.com/10463/android-notification-pendingintent



![alt text](https://github.com/sudhanshujha21/GeofenceExample/blob/master/geofence_push_notification.gif)
