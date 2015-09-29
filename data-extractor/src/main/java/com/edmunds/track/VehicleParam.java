package com.edmunds.track;

/**
 * Created by ahaurylau on 29.09.2015.
 */
public enum VehicleParam {
    MAKE_NAME("makeName", false),
    BODY("body", false),
    TRANSMISSION_TYPE("transmissionType", false),
    HORSEPOWER("horsepower", true),
    ENGINE_TYPE("engineType", false),
    MPG_HIGHWAY("mpgHighway", true),
    MPG_CITY("mpgCity", true);

    private final String name;

    private final boolean numericFlag;

    VehicleParam(String name, boolean numericFlag) {
        this.name = name;
        this.numericFlag = numericFlag;
    }

    public String getName() {
        return name;
    }

    public boolean isNumeric() {
        return numericFlag;
    }
}
