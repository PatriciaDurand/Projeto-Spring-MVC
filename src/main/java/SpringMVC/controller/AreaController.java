package SpringMVC.controller;

import SpringMVC.dao.AreaDao;
import SpringMVC.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;

@Controller
public class AreaController {

    @Autowired
    AreaDao areaDAO;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/cadastroArea")
    public String cadastrarFuncionario(Model model) {
        model.addAttribute("area", new Area());
        return "CadastroArea";
    }

    @RequestMapping(value = "/adicionaArea", method = RequestMethod.POST)
    public String adicionaFuncionario(Area area) {
        if (!area.getNome().equals("")) {
            areaDAO.setDataSource(dataSource);
            areaDAO.salvar(area);
        }
        return "redirect:/cadastroArea";
    }

    @RequestMapping(value = "/listaArea", method=RequestMethod.GET)
    public String listarArea(Model model) {
        areaDAO.setDataSource(dataSource);
        model.addAttribute("lista", areaDAO.listar());
        return "ListaArea";
    }

    @RequestMapping(value = "/deletarArea/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) {
        areaDAO.deletar(codigo);
        return "redirect:/listaArea";
    }

}
