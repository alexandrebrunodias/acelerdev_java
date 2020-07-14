package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class Main {

    @Subtrair
    private BigDecimal a;

    @Somar
    private BigDecimal b;

    @Somar
    private BigDecimal c;

    public Main(BigDecimal a, BigDecimal b, BigDecimal c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void main(String[] args) {
        Main teste = new Main(new BigDecimal(5), new BigDecimal(20), new BigDecimal(30));

        CalculadorDeClasses calculador = new CalculadorDeClasses();
        BigDecimal total = calculador.totalizar(teste);

        System.out.println("Resultado: " + total);
    }
}
