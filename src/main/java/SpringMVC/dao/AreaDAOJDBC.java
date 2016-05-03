package SpringMVC.dao;

import SpringMVC.model.Area;
import SpringMVC.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("AreaDAO")
@Transactional
public class AreaDAOJDBC implements AreaDao {

    @Autowired
    private FuncionarioDAO funcionarioDAO;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void salvar(Area area) {
        String SQL = "INSERT INTO AREA (NOME) VALUES (?)";
        jdbcTemplateObject.update(SQL, area.getNome());
        System.out.println("Área salva com sucesso!\n Nome = " + area.getNome());
        return;
    }

    @Override
    public List<Area> listar() {
        String SQL = "SELECT * FROM AREA";
        List <Area> areas = jdbcTemplateObject.query(SQL, new AreaMapper());
        return areas;
    }

    @Override
    public Area buscarPorCodigo(int codigo){
        String SQL = "SELECT * FROM AREA WHERE ID = ?";
        Area area = jdbcTemplateObject.queryForObject(SQL, new Object[]{codigo}, new AreaMapper());
        return area;
    }

    @Override
    public void deletar(int codigo) throws Exception {
        try {
            String SQL = "DELETE FROM AREA WHERE ID = ?";
            jdbcTemplateObject.update(SQL, codigo);
            System.out.println("Área deletada com sucesso!!\n ID = " + codigo);
        } catch (Exception e){
            throw new Exception("Não foi possível deletar a área " + buscarPorCodigo(codigo).getNome() + " porque existe funcionários associados a ela.");
        }
        return;
    }

    @Override
    public void deletarCascata(int codigo){
        List<Funcionario> listaFuncionarios = funcionarioDAO.listarPorArea(codigo);
        for (Funcionario funcionario : listaFuncionarios) {
            funcionarioDAO.deletar(funcionario.getCodigo());
        }
        String SQL = "DELETE FROM AREA WHERE ID = ?";
        jdbcTemplateObject.update(SQL, codigo);
        System.out.println("Área deletada com sucesso!!\n ID = " + codigo);
        return;
    }

}
