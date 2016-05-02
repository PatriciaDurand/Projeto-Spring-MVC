package SpringMVC.dao;

import SpringMVC.model.Funcionario;

import javax.sql.DataSource;
import java.util.List;

public interface FuncionarioDAO {

    void salvar(Funcionario funcionario);

    List<Funcionario> listar();

    void deletar(int ccodigo);

    Funcionario buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}