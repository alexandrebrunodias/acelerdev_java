package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    private BigDecimal realizaOperacaoMatematica(Object object, Class<? extends Annotation> annotation) {
        Field[] fields = object.getClass().getDeclaredFields();

        BigDecimal resultado = BigDecimal.ZERO;
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(annotation)) {
                try {
                    if(annotation.equals(Somar.class)) {
                        resultado = resultado.add((BigDecimal) field.get(object));
                        continue;
                    }
                    resultado = resultado.subtract((BigDecimal) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultado;
    }

    @Override
    public BigDecimal somar(Object object) {
        return this.realizaOperacaoMatematica(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return this.realizaOperacaoMatematica(object, Subtrair.class).abs();
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return this.somar(object).subtract(this.subtrair(object));
    }
}
