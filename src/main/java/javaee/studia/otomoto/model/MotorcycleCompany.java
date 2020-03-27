package javaee.studia.otomoto.model;

public enum MotorcycleCompany {
    APRILIA("Aprilia"), BAJAJ("Bajaj"), BENELLI("Benelli"), BIMOTA("Bimota"), BMW("BMW"), BRAMMO("Brammo"), BUELL("Buell"), CAGIVA("Cagiva"), CAN_AM("Can-Am"), CCM("CCM"), CFMOTO("CFMoto"), CLEVELAND_CYCLEWERKS("Cleveland CycleWerks"), CONFEDERATE("Confederate"), CPI("CPI"), DAELIM("Daelim"), DERBI("Derbi"), DUCATI("Ducati"), ERIK_BUELL_RACING("Erik Buell Racing"), FANTIC("Fantic"), GENUINE("Genuine"), GILERA("Gilera"), HARLEY_DAVIDSON("Harley-Davidson"), HERO("Hero"), HONDA("Honda"), HOREX("Horex"), HUSABERG("Husaberg"), HUSQVARNA("Husqvarna"), HYOSUNG("Hyosung"), INDIAN("Indian"), JAWA("JAWA"), JOHNNY_PAG("Johnny Pag"), KAWASAKI("Kawasaki"), KEEWAY("Keeway"), KREIDLER("Kreidler"), KTM("KTM"), KYMCO("Kymco"), LML("LML"), LONCIN("Loncin"), LOTUS("Lotus"), MAHINDRA("Mahindra"), MEGELLI("Megelli"), MINERVA("Minerva"), MISSION_MOTORS("Mission Motors"), MOTO_GUZZI("Moto Guzzi"), MOTO_MORINI("Moto Morini"), MOTUS("Motus"), MV_AGUSTA("MV Agusta"), MZ("MZ"), NORTON("Norton"), PEUGEOT("Peugeot"), PGO("PGO"), PIAGGIO("Piaggio"), QINGQI("Qingqi"), RIEJU("Rieju"), ROYAL_ENFIELD("Royal Enfield"), SACHS("Sachs"), SUZUKI("Suzuki"), SYM("Sym"), TRIUMPH("Triumph"), TVS("TVS"), URAL("Ural"), VESPA("Vespa"), VICTORY("Victory"), VYRUS("Vyrus"), YAMAHA("Yamaha"), ZERO("Zero");

    private final String displayValue;

    MotorcycleCompany(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
