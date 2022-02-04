public enum Month {
    JANUARY("1"),
    FEBRUARY("2"),
    MARCH("3"),
    APRIL("4"),
    MAY("5"),
    JUNE("6"),
    JULY("7"),
    AUGUST("8"),
    SEPTEMBER("9"),
    OCTOBER("10"),
    NOVEMBER("11"),
    DECEMBER("12");

    private final String month;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    Month(String month) {
        this.month = month;
    }

}
