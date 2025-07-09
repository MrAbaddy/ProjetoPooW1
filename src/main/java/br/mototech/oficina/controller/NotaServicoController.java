package br.mototech.oficina.controller;

import br.mototech.oficina.model.Funcionario;
import br.mototech.oficina.model.NotaServico;
import br.mototech.oficina.service.FuncionarioService;
import br.mototech.oficina.service.NotaServicoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/notaservico")
public class NotaServicoController {

    private final NotaServicoService notaServicoService;
    private final FuncionarioService funcionarioService;

    public NotaServicoController(NotaServicoService notaServicoService, FuncionarioService funcionarioService) {
        this.notaServicoService = notaServicoService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String listarNotas(
            @RequestParam(value = "opcao", required = false) String opcao,
            @RequestParam(value = "info", required = false) Integer info,
            @RequestParam(value = "msg", required = false) String msg,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (msg != null && !msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }

        if ("editar".equalsIgnoreCase(opcao) && info != null) {
            NotaServico nota = notaServicoService.buscar(info);
            if (nota != null) {
                model.addAttribute("notaEditar", nota);
            } else {
                model.addAttribute("msg", "Nota de serviço não encontrada.");
            }

        } else if ("excluir".equalsIgnoreCase(opcao) && info != null) {
            String resultado = notaServicoService.excluir(info);
            redirectAttributes.addFlashAttribute("msg", resultado);
            return "redirect:/notaservico";
        }

        List<NotaServico> notas = notaServicoService.listar();
        List<Funcionario> funcionarios = funcionarioService.listar();

        model.addAttribute("notas", notas);
        model.addAttribute("funcionarios", funcionarios);

        return "pages/notaservico";
    }

    @PostMapping
    public String salvarNota(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam String cliente,
            @RequestParam String moto,
            @RequestParam(value = "cilindradas", required = false) Integer cilindradas,
            @RequestParam String descricao,
            @RequestParam String material,
            @RequestParam(value = "funcionarioId", required = false) Integer funcionarioId,
            @RequestParam String situacao,
            RedirectAttributes redirectAttributes
    ) {
        Funcionario funcionario = null;

        if (funcionarioId != null) {
            funcionario = funcionarioService.buscar(funcionarioId);
            if (funcionario == null) {
                redirectAttributes.addFlashAttribute("msg", "Funcionário inválido.");
                return "redirect:/notaservico";
            }
        }

        NotaServico nota = new NotaServico();
        nota.setId(id);
        nota.setCliente(cliente);
        nota.setMoto(moto);
        nota.setCilindradas(cilindradas);
        nota.setDescricao(descricao);
        nota.setMaterial(material);
        nota.setFuncionario(funcionario);
        nota.setSituacao(situacao);

        String mensagem;
        if (id != null) {
            mensagem = notaServicoService.alterar(nota);
        } else {
            mensagem = notaServicoService.inserir(nota);
        }

        redirectAttributes.addFlashAttribute("msg", mensagem);
        return "redirect:/notaservico";
    }
}