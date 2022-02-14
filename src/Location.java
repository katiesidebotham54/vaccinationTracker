/**
 * This enum class holds different information for the 5 valid locations which are the enum types.
 * Each on holds, their city, zip code, and county name. This was used in order to keep track and to validate
 * locations that were inputted through the console. This was also for easy access to the information of each location.
 * @author @kevinarbitodelgado, @katherinesidebotham
 */
public enum Location {
    SOMERSET("Bridgewater", "08807", "SOMERSET"),
    MIDDLESEX("Piscataway", "08854", "MIDDLESEX"),
    MERCER("Princeton", "08542", "MERCER"),
    MORRIS("Morristown", "07960", "MORRIS"),
    UNION("Union", "07083", "UNION");

    private final String city;
    private final String zipCode;
    private final String county;

    /**
     * This constructor takes in the String type of each of the constants that were defined above.
     * They are all private, this associates the information with the location enums that were created.
     * This is used to have set descriptions for each of the valid locations.
     * @param city constant for city
     * @param zipCode constant for zip code
     * @param county constant for county
     */
    Location(String city, String zipCode, String county) {
        this.city = city;
        this.zipCode = zipCode;
        this.county = county;
    }
    /**
     * Retrieves the city for patient from Location
     * @return returns the patient object to where it was called from.
     */
    public String getCity() { return city; }

    /**
     * Retrieves the zipcode for patient from Location
     * @return returns the patient object to where it was called from.
     */
    public String getZipCode() { return zipCode; }

    /**
     * Retrieves the county for patient from Location
     * @return returns the patient object to where it was called from.
     */
    public String getCounty() { return county; }

}
