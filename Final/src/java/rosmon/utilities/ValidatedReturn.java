package rosmon.utilities;

public class ValidatedReturn {

    int code;
    String message;

    public ValidatedReturn(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
