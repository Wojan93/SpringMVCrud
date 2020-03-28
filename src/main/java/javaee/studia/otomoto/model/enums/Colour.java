package javaee.studia.otomoto.model.enums;

public enum Colour {
    BLUE("Blue"), NAVY_BLUE("Navy_Blue"), BROWN("Brown"), BLACK("Black"), GOLD("Gold"), GREEN("Green"),  GREY("Grey"), ORANGE("Orange"), PINK("Pink"), RED("Red"), SILVER("Silver"), VIOLET("Violet"), YELLOW("Yellow"), WHITE("White");
    private final String displayValue;

    Colour(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
