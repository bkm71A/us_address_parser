package com.skovalenko.geocoder.address_parser;

public class UnparsedAddress {
    private String addressLine = "";

    private String city = "";

    private String zip = "";

    public UnparsedAddress(final String addressLine, final String city, final String zip) {
        this.addressLine = addressLine == null ? "" : addressLine;
        this.city = city == null ? "" : city;
        this.zip = zip == null ? "" : zip;
    }

    public UnparsedAddress() {
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine == null ? "" : addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? "" : city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? "" : zip;
    }

    public String toString() {
        return new StringBuilder().append(addressLine).append(UsAddress.FIELD_SEPARATOR).append(city).append(UsAddress.FIELD_SEPARATOR).append(zip).toString();
    }

    public static String getHeader() {
        return new StringBuilder().append("Unparsed Addres Line").append(UsAddress.FIELD_SEPARATOR).append("Unparsed City").append(UsAddress.FIELD_SEPARATOR).append("Unparsed Zip").toString();
    }
}
