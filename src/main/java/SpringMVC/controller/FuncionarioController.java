package SpringMVC.controller;

import SpringMVC.dao.AreaDao;
import SpringMVC.dao.FuncionarioDAO;
import SpringMVC.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/cadastroFuncionario")
    public String cadastrarFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        areaDao.setDataSource(dataSource);
        model.addAttribute("listaArea", areaDao.listar());
        return "CadastroFuncionario";
    }

    @RequestMapping(value = "/adicionaFuncionario", method = RequestMethod.POST)
    public String adicionaFuncionario(Funcionario funcionario) {
        if (!funcionario.getNome().equals("") && funcionario.getSalarioBase() >= 0) {
            funcionarioDAO.setDataSource(dataSource);
            funcionarioDAO.salvar(funcionario);
        }
        return "redirect:/cadastroFuncionario";
    }

    @RequestMapping(value = "/listaFuncionario", method=RequestMethod.GET)
    public String listarFuncionario(Model model) {
        funcionarioDAO.setDataSource(dataSource);
        areaDao.setDataSource(dataSource);
        model.addAttribute("listaFuncionario", funcionarioDAO.listar());
        model.addAttribute("listaArea", areaDao.listar());
        return "ListaFuncionarios";
    }

    @RequestMapping(value = "/deletarFuncionario/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) {
        funcionarioDAO.deletar(codigo);
        return "redirect:/listaFuncionario";
    }

}