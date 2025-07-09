package br.mototech.oficina.controller;

import br.mototech.oficina.model.NotaServico;
import br.mototech.oficina.service.NotaServicoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/painel-mecanico")
public class PainelMecanicoController {

    private final NotaServicoService notaServicoService;

    public PainelMecanicoController(NotaServicoService notaServicoService) {
        this.notaServicoService = notaServicoService;
    }

    @GetMapping
    public String listarOrdens(
            @RequestParam(value = "msg", required = false) String msg,
            Model model
    ) {
        List<NotaServico> notas = notaServicoService.listar();
        model.addAttribute("notas", notas);

        if (msg != null && !msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }

        return "pages/painel-mecanico";
    }

    @PostMapping("/alterar-situacao")
    public String alterarSituacao(
            @RequestParam("notaId") Integer notaId,
            @RequestParam("novaSituacao") String novaSituacao,
            RedirectAttributes redirectAttributes
    ) {
        NotaServico nota = notaServicoService.buscar(notaId);
        if (nota == null) {
            redirectAttributes.addFlashAttribute("msg", "Ordem de serviço não encontrada.");
            return "redirect:/painel-mecanico";
        }

        nota.setSituacao(novaSituacao);
        String mensagem = notaServicoService.alterar(nota);
        redirectAttributes.addFlashAttribute("msg", mensagem);

        return "redirect:/painel-mecanico";
    }

}
