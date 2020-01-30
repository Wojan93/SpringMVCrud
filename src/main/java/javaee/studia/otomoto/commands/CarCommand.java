package javaee.studia.otomoto.commands;

/**
 *
 * @author Wojciech Polubiec
 * Część wzorca projektowego Command - Polecenie. Wzorzec ten zamienia żądanie w obiekt żądania.
 *
 */

public class CarCommand {


    private Long id;
    private String company;
    private String model;
    private String name;
    private String vinNumber;
    private String colour;
    private Double price;
    private int year;
    private int motorCapacity;
    private String fuelType;
    private Double kilometers;
    private Boolean safetyLock;
    private Boolean aluWheels;
    private Long seller;
    private Long buyer;
    private Byte[] image;

    public CarCommand() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(int motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getKilometers() {
        return kilometers;
    }

    public void setKilometers(Double kilometers) {
        this.kilometers = kilometers;
    }

    public Boolean getSafetyLock() {
        return safetyLock;
    }

    public void setSafetyLock(Boolean safetyLock) {
        this.safetyLock = safetyLock;
    }

    public Boolean getAluWheels() {
        return aluWheels;
    }

    public void setAluWheels(Boolean aluWheels) {
        this.aluWheels = aluWheels;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
