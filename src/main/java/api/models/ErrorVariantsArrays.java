package api.models;

public class ErrorVariantsArrays {
    public static final String[] codeArray() {
        String[] сode = new String[6];

        сode[0] = "TARIFF_NOT_FOUND";

        сode[1] = "ORDER_NOT_FOUND";

        сode[2] = "ORDER_IMPOSSIBLE_TO_DELETE";

        сode[3] = "TRY_LATER";

        сode[4] = "LOAN_ALREADY_APPROVED";

        сode[5] = "LOAN_CONSIDERATION";

        return сode;
    }

    public static final String[] messageArray() {
        String[] message = new String[6];

        message[0] = "Тариф не найден";

        message[1] = "Заявка не найдена";

        message[2] = "Заявку невозможно удалить";

        message[3] = "Попробуйте позже";

        message[4] = "Заявка уже одобрена";

        message[5] = "Заявка на рассмотрении";

        return message;
    }
}
