package api.models;

public class TariffsTypeVariantsArray {
    public static final String[] typesArray() {
        String[] type = new String[3];

        type[0] = "CONSUMER";

        type[1] = "MORTGAGE";

        type[2] = "SALE";

        return type;
    }
}
