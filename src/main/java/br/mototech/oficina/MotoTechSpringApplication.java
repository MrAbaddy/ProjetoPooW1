package br.mototech.oficina;

import br.mototech.oficina.model.Funcionario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MotoTechSpringApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Funcionario.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MotoTechSpringApplication.class, args);
    }

}
