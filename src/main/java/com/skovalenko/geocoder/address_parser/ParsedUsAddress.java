package com.skovalenko.geocoder.address_parser;

public class ParsedUsAddress extends UsAddress {

    private String subUnitName = "";

    private String subUnitNumber = "";

    public ParsedUsAddress() {
    }

    public ParsedUsAddress(String streetNumber, String streetPreDir, String streetName, String streetType, String streetPostDir, String subUnitName,
            String subUnitNumber, String city, String state, String zip, String zip4) {
        super(streetNumber, streetPreDir, streetName, streetType, streetPostDir, city, state, zip, zip4);
        this.subUnitName = subUnitName == null ? "" : subUnitName;
        this.subUnitNumber = subUnitNumber == null ? "" : subUnitNumber;
    }

    public ParsedUsAddress(String streetNumber, String streetPreDir, String streetName, String streetType, String streetPostDir, String subUnitName,
            String subUnitNumber) {
        this(streetNumber, streetPreDir, streetName, streetType, streetPostDir, subUnitName, subUnitNumber, "", "", "", "");
    }

    public String getSubUnitName() {
        return subUnitName;
    }

    public void setSubUnitName(String subUnitName) {
        this.subUnitName = (subUnitName != null) ? subUnitName.toUpperCase() : "";
    }

    public String getSubUnitNumber() {
        return subUnitNumber;
    }

    public void setSubUnitNumber(String subUnitNumber) {
        this.subUnitNumber = (subUnitNumber != null) ? subUnitNumber.toUpperCase() : "";
    }

    public String toString() {
        return new StringBuilder(super.toString()).append(FIELD_SEPARATOR).append(subUnitName).append(FIELD_SEPARATOR).append(subUnitNumber).toString();
    }

    public static String getHeader() {
        return new StringBuilder(UsAddress.getHeader()).append(FIELD_SEPARATOR).append("Sub Unit Name").append(FIELD_SEPARATOR).append("Sub Unit Number").toString();
    }

    public ParsedUsAddress clone() {
        return new ParsedUsAddress(streetNumber, streetPreDir, streetName, streetType, streetPostDir, subUnitName, subUnitNumber, city, state, zip, zip4);
    }
}
