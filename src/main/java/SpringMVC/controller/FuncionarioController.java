package SpringMVC.controller;

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
    DataSource dataSource;

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/cadastro")
    public String cadastrarFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "CadastroFuncionario";
    }

    @RequestMapping(value = "/adicionafuncionario", method = RequestMethod.POST)
    public String adicionaFuncionario(Funcionario funcionario) {
        if (!funcionario.getNome().equals("") && funcionario.getSalarioBase() >= 0) {
            funcionarioDAO.setDataSource(dataSource);
            funcionarioDAO.salvar(funcionario);
        }
        return "redirect:/cadastro";
    }

    @RequestMapping(value = "/lista", method=RequestMethod.GET)
    public String listarFuncionario(Model model) {
        funcionarioDAO.setDataSource(dataSource);
        model.addAttribute("lista", funcionarioDAO.listar());
        return "ListaFuncionarios";
    }

    @RequestMapping(value = "/deletar/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) {
        funcionarioDAO.deletar(codigo);
        return "redirect:/lista";
    }

}