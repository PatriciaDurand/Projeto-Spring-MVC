import SpringMVC.model.Funcionario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FuncionarioTest {

    @Test
    public void deveRetornarSalarioLiquidoIgualA900ParaSalarioBaseIgualA1000() throws Exception {
        Funcionario funcionario1 = new Funcionario("João", 1000);

        double salarioLiquido = funcionario1.getSalarioLiquido();

        assertEquals(900, salarioLiquido, 1);
    }

    @Test
    public void deveRetornarSalarioLiquidoIgualA900ParaSalarioBaseMaiorQue2000() throws Exception {
        Funcionario funcionario2 = new Funcionario("Ana", 3000);

        double salarioLiquido = funcionario2.getSalarioLiquido();

        assertEquals(2500, salarioLiquido, 1);
    }

}
