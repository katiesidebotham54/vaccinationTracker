/*

authors: @kevinarbito @katiesidebotham
 */
// Done
public enum Location {
    SOMERSET("Bridgewater", "08807", "Somerset County"),
    MIDDLESEX("Piscataway", "08854", "Middlesex County"),
    MERCER("Princeton", "08542", "Mercer County"),
    MORRIS("Morristown", "07960", "Morris County"),
    UNION("Union", "07083", "Union County");

    private final String city;
    private final String zipCode;
    private final String county;

    Location(String city, String zipCode, String county) {
        this.city = city;
        this.zipCode = zipCode;
        this.county = county;
    }
    
    public String getCity() { return city; }

    public String getZipCode() { return zipCode; }

    public String getCounty() { return county; }

}
/*
public s
 */
