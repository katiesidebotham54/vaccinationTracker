public enum Location {
    BRIDGEWATER("Bridgewater", "08807", "Somerset County"),
    PISCATAWAY("Piscataway", "08854", "Middlesex County"),
    PRINCETON("Princeton", "08542", "Mercer County"),
    MORRISTOWN("Morristown", "07960", "Morris County"),
    UNION("Union", "07083", "Union County");

    private final String city;
    private final String zipCode;
    private final String county;

    Location(String city, String zipCode, String county) {
        this.city = city;
        this.zipCode = zipCode;
        this.county = county;
    }
}
