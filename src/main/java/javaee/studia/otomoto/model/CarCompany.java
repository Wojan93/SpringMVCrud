package javaee.studia.otomoto.model;

public enum CarCompany {
    ABARTH("Abarth"), ACURA("Acura"), AIXAM("Aixam"), ALFA_ROMEO("Alfa_Romeo"), ARO("Aro"), ASTON_MARTIN("Aston_Martin"), AUDI("Audi"), BENTLEY("Bentley"), BMW("BMW"), BUICK("Buick"), CADILLAC("Cadillac"), CHEVROLET("Chevrolet"), CHRYSLER("Chrysler"), CITROEN("Citroen"), DACIA("Dacia"), DAEWOO("Daewoo"), DAIHATSU("Daihatsu"), DODGE("Dodge"), FERRARI("Ferrari"), FIAT("Fiat"), FORD("Ford"), HONDA("Honda"), HUMMER("Hummer"), HYUNDAI("Hyundai"), INFINITY("Infiniti"), ISUZU("Isuzu"), JAGUAR("Jaguar"), JEEP("Jeep"), KIA("Kia"), LAMBORGHINI("Lamborghini"), LANCIA("Lancia"), LAND_ROVER("Land_Rover"), LEXUS("Lexus"), LIGIER("Ligier"), LINCOLN("Lincoln"), MCLAREN("McLaren"), ŁADA("Łada"), MASERATI("Maserati"), MAZDA("Mazda"), MERCEDES_BENZ("Mercedes_Benz"), MG("MG"), MICROCAR("Microcar"), MINI("Mini"), MITSUBISHI("Mitsubishi"), NISSAN("Nissan"), OPEL("Opel"), PEUGEOT("Peugeot"), PLYMOUTH("Plymouth"), POLONEZ("Polonez"), PONTIAC("Pontiac"), PORSCHE("Porsche"), RENAULT("Renault"), ROVER("Rover"), SAAB("Saab"), SEAT("SEAT"), SKODA("Skoda"), SMART("Smart"), SSANGYONG("SsangYong"), SUBARU("Subaru"), SUZUKI("Suzuki"), TATA("Tata"), TESLA("Tesla"), TOYOTA("Toyota"), TRABANT("Trabant"), UAZ("UAZ"), VOLKSWAGEN("Volkswagen"), VOLVO("Volvo"), WARTBURG("Wartburg");

    private final String displayValue;

    CarCompany(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
