package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class Main {

    @Subtrair
    private BigDecimal a;

    @Subtrair
    private BigDecimal b;

    public Main(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        System.out.println(new CalculadorDeClasses().totalizar(new Main(new BigDecimal(5), new BigDecimal(20))));
    }
}
