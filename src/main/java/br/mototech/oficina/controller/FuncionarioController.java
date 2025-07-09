package br.mototech.oficina.controller;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.service.FuncionarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String listarFuncionarios(
            @RequestParam(value = "opcao", required = false) String opcao,
            @RequestParam(value = "info", required = false) Integer info,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (opcao != null && info != null) {
            if (opcao.equals("editar")) {
                Funcionario funcionario = funcionarioService.buscar(info);
                model.addAttribute("funcionarioEditar", funcionario);
            } else if (opcao.equals("excluir")) {
                String msg = funcionarioService.excluir(info);
                redirectAttributes.addFlashAttribute("msg", msg);
                return "redirect:/funcionario";
            }
        }

        List<Funcionario> funcionarios = funcionarioService.listar();
        model.addAttribute("funcionarios", funcionarios);

        return "pages/funcionario";
    }

    @PostMapping
    public String salvarFuncionario(
            @ModelAttribute Funcionario funcionario,
            Model model) {

        String retorno;
        if (funcionario.getId() != null) {
            retorno = funcionarioService.alterar(funcionario);
        } else {
            retorno = funcionarioService.inserir(funcionario);
        }

        model.addAttribute("retorno", retorno);
        model.addAttribute("funcionarios", funcionarioService.listar());

        return "pages/funcionario";
    }
}
