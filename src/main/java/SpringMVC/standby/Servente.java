package SpringMVC.standby;

import SpringMVC.model.Funcionario;

public class Servente extends Funcionario {

    private double insal;
    
    //Servente: classe derivada da classe Funcionario.
    //Um servente recebe um adicional de 5% a título de insalubridade
    public Servente(String nome, double salarioBase, double insalubridade) {
        super(nome, salarioBase);
        insal = insalubridade;
    }

    @Override
    public double getSalarioLiquido() {
        double salario = super.getSalarioLiquido();
        insal = salario * 0.05;
        double novoSalario = salario + insal;
        return novoSalario;
        // ou return super.getSalarioLiquido() + getSalarioLiquido()*0.05;
    }
}
