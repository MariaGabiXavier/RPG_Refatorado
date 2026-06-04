package br.shadowhunters.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class InputUtil {

    private InputUtil() {}

    public static int lerIntervalo(Scanner sc, int min, int max) {
        while (true) {
            try {
                int valor = sc.nextInt();
                if (valor < min || valor > max) {
                    System.out.println("Digite um número entre " + min + " e " + max + ":");
                    continue;
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida — digite um número:");
                sc.next();
            }
        }
    }
}