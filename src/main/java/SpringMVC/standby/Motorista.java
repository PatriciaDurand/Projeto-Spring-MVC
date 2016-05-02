package SpringMVC.standby;

import SpringMVC.model.Funcionario;

public class Motorista extends Funcionario {

    private long cartMotorista;

    //Motorista: classe derivada da classe Funcionario.
    //Para cada motorista é necessário armazenar o número da carteira de motorista.
    public Motorista(String nome, double salarioBase, long cartMot) {
        super(nome, salarioBase);
        cartMotorista = cartMot;
    }

    public void setCartMotorista(long cartMotorista) {
        this.cartMotorista = cartMotorista;
    }

    public long getCartMotorista() {
        return cartMotorista;
    }

    @Override
    public String toString() {
        return super.toString() + "cartMotorista=" + cartMotorista + '}';
    }
}
