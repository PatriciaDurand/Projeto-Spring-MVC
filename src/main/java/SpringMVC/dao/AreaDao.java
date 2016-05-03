package SpringMVC.dao;

import SpringMVC.model.Area;

import javax.sql.DataSource;
import java.util.List;

public interface AreaDao {

    void salvar(Area area);

    List<Area> listar();

    void deletar(int ccodigo);

    Area buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}
