package br.shadowhunters.ui;

public final class Console {

    private static final String NEGRITO = "\u001B[1m";
    private static final String RESET   = "\u001B[0m";

    private Console() {}

    public static void negrito(String texto) {
        System.out.print(NEGRITO + texto + RESET);
    }

    public static void titulolinha(String texto) {
        System.out.println(NEGRITO + texto + RESET);
    }

    public static void titulo(String texto) {
        System.out.println("\n" + NEGRITO + "=== " + texto + " ===" + RESET);
    }

    public static String emNegrito(String texto) {
        return NEGRITO + texto + RESET;
    }
}