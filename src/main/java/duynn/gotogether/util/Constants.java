package duynn.gotogether.util;

import java.util.Arrays;

public class Constants {
    public static final int REQUEST_LOCATION_PERMISSIONS_CODE = 1;
    public static final int REQUEST_BACKGROUND_LOCATION_PERMISSIONS_CODE = 2;
    public static final int GET_START_POINT = 3;
    public static final int GET_END_POINT = 4;
    public static final int GET_LOCATION_FROM_MAP_GET_PLACE = 5;
    public static final int START_LOCATION_REQUEST_CODE = 6;
    public static final int END_LOCATION_REQUEST_CODE = 7;
    public static final int STOP_LOCATION_REQUEST_CODE = 8;
    public static final int ADD_TRANSPORT_REQUEST_CODE = 9;

    public static final String ACTION_SERVICE_START = "ACTION_SERVICE_START";
    public static final String ACTION_SERVICE_STOP = "ACTION_SERVICE_STOP";
    public static final String ACTION_NAVIGATE_TO_TRACKER_ACTIVITY = "ACTION_NAVIGATE_TO_TRACKER_ACTIVITY";

    public static final String TRACKER_NOTIFICATION_CHANNEL_ID = "TRACKER_NOTIFICATION_CHANNEL_ID";
    public static final String TRACKER_NOTIFICATION_CHANNEL_NAME = "TRACKER_NOTIFICATION_CHANNEL_NAME";
    public static final int TRACKER_NOTIFICATION_ID = 3;

    public static final int PENDING_INTENT_REQUEST_CODE = 99;

    public static final long LOCATION_UPDATE_INTERVAL = 4000L; // 4 seconds
    public static final long LOCATION_UPDATE_FASTEST_INTERVAL = 2000L; // 2 seconds

    public static final float DEFAULT_ZOOM = 15f;
    public static final float DEFAULT_ZOOM_FOR_TRACKER = 18f;
    public static final float DEFAULT_ZOOM_FOR_WORLD = 1f;
    public static final float DEFAULT_ZOOM_FOR_LANDMASS = 5f;
    public static final float DEFAULT_ZOOM_FOR_CITY = 10f;
    public static final float DEFAULT_ZOOM_FOR_STREET = 15f;
    public static final float DEFAULT_ZOOM_FOR_BUILDING = 20f;

    public static final int DEFAULT_ANIMATION_TIME = 1000;

    //for navigation activity
    public static final String Bundle = "Bundle";
    public static final String GeocodingResult = "GeocodingResult";
    public static final String Position = "Position";
    public static final String TRANSPORT = "Transport";
    public static final String GOONG_PLACE_DETAIL_RESULT = "GOONG_PLACE_DETAIL_RESULT";


    //status
    public static final String SUCCESS = "Success";
    public static final String FAIL = "Fail";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";


    public static final String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String PHONE_REGEX = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";


    public static final String TRIP_CANCEL = "TRIP_CANCEL";

    public static final String CLIENT_TRIP_ID = "CLIENT_TRIP_ID";
    public static final String DRIVER_ID = "DRIVER_ID";
    public static final String DISTANCE = "DISTANCE";
    public static final String PRICE = "PRICE";
    public static final String PASSENGER_NUM = "PASSENGER_NUM";
}

