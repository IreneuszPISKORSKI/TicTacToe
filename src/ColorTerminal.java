public enum ColorTerminal {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    BLACK_B("\u001B[40m"),
    RED_B("\u001B[41m"),
    GREEN_B("\u001B[42m"),
    YELLOW_B("\u001B[43m"),
    BLUE_B("\u001B[44m"),
    PURPLE_B("\u001B[45m"),
    CYAN_B("\u001B[46m"),
    WHITE_B("\u001B[47m");

    private final String code;

    ColorTerminal(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
