package br.pe.recife.tafeito.util;

public class Util {

    public static int valorInteiro(Boolean valor) {
        int res = 0;
        if (valor != null) {
            if (valor) {
                res = 1;
            }
        }
        return res;
    }

    public static boolean valorBooleano(int valor) {
        boolean res = false;
        if (valor == 1) {
            res = true;
        }
        return res;
    }
}
