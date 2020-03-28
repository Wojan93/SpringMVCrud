package javaee.studia.otomoto.model.enums;

public enum FuelType {

    PETROL("PETROL"), PETROL_LPG("PETROL+LPG"), DIESEL("DIESEL"), HYBRID("HYBRID"),ELECTRICITY("ELECTRICITY");

    private final String displayValue;

    FuelType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
