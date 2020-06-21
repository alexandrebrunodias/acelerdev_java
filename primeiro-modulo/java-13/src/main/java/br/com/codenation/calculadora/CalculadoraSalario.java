package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		if (salarioBase < 1039)
			return 0;

		salarioBase = this.calcularInss(salarioBase);
		salarioBase = this.calcularIrrf(salarioBase);

		return Math.round(salarioBase);
	}

	private double calcularInss(double salario) {
		if(salario <= 1500) {
			return salario * .92;
		} else if(salario <= 4000) {
			return salario * .91;
		}
		return salario * .89;
	}

	private double calcularIrrf(double salario) {
		if(salario <= 3000.01) {
			return salario;
		} else if (salario <= 6000) {
			return salario * .925;
		}
		return salario * .85;
	}

}