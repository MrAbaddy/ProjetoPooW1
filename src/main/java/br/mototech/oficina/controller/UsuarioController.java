package br.mototech.oficina.controller;

import br.mototech.oficina.model.Usuario;
import br.mototech.oficina.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(
            @RequestParam(value = "opcao", required = false) String opcao,
            @RequestParam(value = "info", required = false) Integer info,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (opcao != null && info != null) {
            if (opcao.equals("editar")) {
                Usuario usuario = usuarioService.buscar(info);
                model.addAttribute("usuarioEditar", usuario);
            } else if (opcao.equals("excluir")) {
                String valor = usuarioService.excluir(info);
                redirectAttributes.addFlashAttribute("msg", valor);
                return "redirect:/usuario";
            }
        }

        List<Usuario> usuarios = usuarioService.listar();
        model.addAttribute("usuarios", usuarios);

        return "pages/usuario"; // corresponde ao /WEB-INF/pages/usuario.jsp
    }

    @PostMapping
    public String salvarUsuario(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam String email,
            @RequestParam String senha,
            Model model
    ) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        String retorno;
        if (id != null) {
            retorno = usuarioService.alterar(usuario);
        } else {
            retorno = usuarioService.inserir(usuario);
        }

        model.addAttribute("retorno", retorno);
        model.addAttribute("usuarios", usuarioService.listar());

        return "pages/usuario";
    }
}
