package com.intuit.parkinglot.constant;

public interface Constants {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String FAILURE_MESSAGE = "Failure";


    public static final String	CREATE_PARKING_LOT					= "create_parking_lot";
    public static final String PARKING_LOT_ALREADY_CREATED          = "parking_lot_already_created";
    public static final String VEHICLE_NOT_FOUND                    = "vehicle_not_found";

    public static final int SPOT_PER_ROW = 10;
    public static final double PERCENTAGE_OF_LARGE_SPOTS = 0.70;
    public static final double PERCENTAGE_OF_COMPACT_SPOTS = 0.20;

    public static final int SPOT_SIZE_FOR_MOTORCYCLE = 1;
    public static final int SPOT_SIZE_FOR_CAR = 1;
    public static final int SPOT_SIZE_FOR_BUS = 5;

}
