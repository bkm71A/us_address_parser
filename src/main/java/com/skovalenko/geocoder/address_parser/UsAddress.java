package com.skovalenko.geocoder.address_parser;
import static com.skovalenko.geocoder.address_parser.us.ParserUtilities.nullSafeUpperCase;
public abstract class UsAddress {

	static final String FIELD_SEPARATOR = "|";
	protected String streetNumber = "";
	protected String streetPreDir = "";
	protected String streetName = "";
	protected String streetType = "";
	protected String streetPostDir = "";
	protected String city = "";
	protected String state = "";
	protected String zip = "";
	protected String zip4 = "";
	protected String county = "";
	protected String country = "USA";

	public UsAddress() {
	}

    public UsAddress(String streetNumber, String streetPreDir, String streetName, String streetType, String streetPostDir, String city, String state, String zip, String zip4) {
        this.streetNumber = nullSafeUpperCase(streetNumber);
        this.streetPreDir = nullSafeUpperCase(streetPreDir);
        this.streetName = nullSafeUpperCase(streetName);
        this.streetType = nullSafeUpperCase(streetType);
        this.streetPostDir = nullSafeUpperCase(streetPostDir);
        this.city = nullSafeUpperCase(city);
        this.state = nullSafeUpperCase(state);
        this.zip = nullSafeUpperCase(zip);
        this.zip4 = nullSafeUpperCase(zip4);
    }

    public UsAddress(String streetNumber, String streetPreDir, String streetName, String streetType, String streetPostDir) {
        this(streetNumber, streetPreDir, streetName, streetType, streetPostDir, "", "", "", "");
    }

	public String getCity() {
		return city;
	}

    public void setCity(String city) {
        this.city = nullSafeUpperCase(city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = nullSafeUpperCase(state);
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = nullSafeUpperCase(streetName);
    }

	public String getStreetNumber() {
		return streetNumber;
	}

    public int intStreetNumber() {
        try {
            return Integer.parseInt(streetNumber);
        } catch (NumberFormatException nfe) {
        }
        return -1;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = nullSafeUpperCase(streetNumber);
    }

    public String getStreetPostDir() {
        return streetPostDir;
    }

    public void setStreetPostDir(String streetPostDir) {
        this.streetPostDir = nullSafeUpperCase(streetPostDir);
    }

    public String getStreetPreDir() {
        return streetPreDir;
    }

    public void setStreetPreDir(String streetPreDir) {
        this.streetPreDir = nullSafeUpperCase(streetPreDir);
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = nullSafeUpperCase(streetType);
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = nullSafeUpperCase(zip);
    }

    public String getZip4() {
        return zip4;
    }

    public void setZip4(String zip4) {
        this.zip4 = nullSafeUpperCase(zip4);
    }

    public String toString() {
        return new StringBuilder().append(zip).append(FIELD_SEPARATOR).append(zip4).append(FIELD_SEPARATOR).append(state).append(FIELD_SEPARATOR).append(city)
                .append(FIELD_SEPARATOR).append(streetNumber).append(FIELD_SEPARATOR).append(streetName).append(FIELD_SEPARATOR).append(streetPreDir)
                .append(FIELD_SEPARATOR).append(streetType).append(FIELD_SEPARATOR).append(streetPostDir).toString();
    }

    protected static String getHeader() {
        return new StringBuilder().append("Zip").append(FIELD_SEPARATOR).append("Zip4").append(FIELD_SEPARATOR).append("State").append(FIELD_SEPARATOR)
                .append("City").append(FIELD_SEPARATOR).append("Street Number").append(FIELD_SEPARATOR).append("Street Name").append(FIELD_SEPARATOR)
                .append("Street Pre Dir").append(FIELD_SEPARATOR).append("Street Type").append(FIELD_SEPARATOR).append("Street Post Dir").toString();
    }

	public boolean checkValid() {
		if (streetName.length() == 0) return false; // street name present ?
		if (streetNumber.length() == 0 || !streetNumber.matches("^\\d+$")) return false; // street number present ?
		if (city.length() == 0 && zip.length() == 0) return false; // city of zip present ?
		return true;
	}

	public String getCounty() {
		return county;
	}

    public void setCounty(String county) {
        this.county = nullSafeUpperCase(county);
    }

	public String getCountry() {
		return country;
	}

    public void setCountry(String country) {
        this.country = (country != null) ? country.toUpperCase() : "USA";
    }

	public String getFullZip() {
	    StringBuilder builder = new StringBuilder();
		appendPortion(builder, getZip(), false);
		appendPortion(builder, getZip4(), true, "-");
		return builder.toString();
	}

	/*
	 * Returns a formatted string containing all specified street fields in the
	 * appropriate order.
	 */
	public String getFullStreet() {
		// if we've been assigned one explicitly, just use that
		if (fullStreet != null) {
			return fullStreet;
		}

		StringBuilder builder = new StringBuilder();
		appendPortion(builder, getStreetNumber(), false);
		appendPortion(builder, getStreetPreDir());
		appendPortion(builder, getStreetName());
		appendPortion(builder, getStreetType());
		appendPortion(builder, getStreetPostDir());
		// appendPortion(builder, getSubUnitName());
		// appendPortion(builder, getSubUnitNumber());
		return builder.toString();
	}

	// HACK: this is used to create a stub fetched address when you're
	// creating from address history, and which you don't have a parsed
	// address string
	private String fullStreet;

    public void setFullStreet(String fullStreet) {
        this.fullStreet = fullStreet;
    }

    private void appendPortion(StringBuilder builder, String portion) {
        appendPortion(builder, portion, true);
    }

    private void appendPortion(StringBuilder builder, String portion, boolean insertDelimiter) {
        appendPortion(builder, portion, insertDelimiter, " ");
    }

    private void appendPortion(StringBuilder builder, String portion, boolean insertDelimiter, String delimiter) {
        if (isBlank(portion)) {
            return;
        }
        if (insertDelimiter && builder.length() > 0) {
            builder.append(delimiter);
        }
        builder.append(portion);
    }

    private boolean isBlank(String string) {
        return string == null || string.trim().length() == 0;
    }

    public boolean equals(UsAddress address) {
        return this.streetNumber.equalsIgnoreCase(address.getStreetNumber()) && this.streetPreDir.equalsIgnoreCase(address.getStreetPreDir())
                && this.streetName.equalsIgnoreCase(address.getStreetName()) && this.streetType.equalsIgnoreCase(address.getStreetType())
                && this.streetPostDir.equalsIgnoreCase(address.getStreetPostDir()) && this.city.equalsIgnoreCase(address.getCity())
                && this.state.equalsIgnoreCase(address.getState()) && this.zip.equalsIgnoreCase(address.getZip())
                && this.zip4.equalsIgnoreCase(address.getZip4()) && this.county.equalsIgnoreCase(address.getCounty())
                && this.country.equalsIgnoreCase(address.getCountry());
    }
}
