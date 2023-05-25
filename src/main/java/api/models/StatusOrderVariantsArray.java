package api.models;

public class StatusOrderVariantsArray {
    public static final String[] orderStatusArray() {
        String[] orderStatus = new String[3];

        orderStatus[0] = "IN_PROGRESS";

        orderStatus[1] = "APPROVED";

        orderStatus[2] = "REFUSED";

        return orderStatus;
    }
}
