package com.intuit.parkinglot.constant;

public interface Constants {

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final long TWO = 2;
    public static final long THREE = 3;

    public static final long NO_AVAILABLE_SLOT = 0;
    public static final long VEHICLE_ALREADY_PARKED = -2;
    public static final long SLOT_ALREADY_EMPTY = -3;


    public static final String	CREATE_PARKING_LOT					= "create_parking_lot";
    public static final String PARKING_LOT_ALREADY_CREATED          = "parking_lot_already_created";
    public static final String VEHICLE_NOT_FOUND                    = "vehicle_not_found";
    public static final String	PARK								= "park";
    public static final String	LEAVE								= "leave";
    public static final String	STATUS								= "status";
    public static final String	REG_NUMBER_FOR_CARS_WITH_COLOR		= "registration_numbers_for_cars_with_colour";
    public static final String	SLOTS_NUMBER_FOR_CARS_WITH_COLOR	= "slot_numbers_for_cars_with_colour";
    public static final String	SLOTS_NUMBER_FOR_REGISTRATION_NUMBER= "slot_number_for_registration_number";
    public static final String EXIT 								= "exit";
    public static final long ERROR_OCCURED = 400;

    public static final int SPOT_PER_ROW = 10;
    public static final double PERCENTAGE_OF_LARGE_SPOTS = 0.25;
    public static final double PERCENTAGE_OF_COMPACT_SPOTS = 0.25;

    public static final int SPOT_SIZE_FOR_MOTORCYCLE = 1;
    public static final int SPOT_SIZE_FOR_CAR = 1;
    public static final int SPOT_SIZE_FOR_BUS = 5;

}
