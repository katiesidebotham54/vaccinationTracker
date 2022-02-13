/*

authors: @kevinarbito @katiesidebotham
 */
// Done
public enum Location {
    SOMERSET("Bridgewater", "08807", "SOMERSET"),
    MIDDLESEX("Piscataway", "08854", "MIDDLESEX"),
    MERCER("Princeton", "08542", "MERCER"),
    MORRIS("Morristown", "07960", "MORRIS"),
    UNION("Union", "07083", "UNION");

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
