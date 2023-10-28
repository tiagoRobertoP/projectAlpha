package br.com.cg.projectAlpha;

import br.com.cg.projectAlpha.repository.PessoaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PessoaRepository.class)
public class ProjectAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAlphaApplication.class, args);
	}

}
