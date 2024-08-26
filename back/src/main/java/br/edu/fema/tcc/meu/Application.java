package br.edu.fema.tcc.meu;

import br.edu.fema.tcc.meu.Repository.ServicoRepository;
import br.edu.fema.tcc.meu.Service.CidadeService;
import br.edu.fema.tcc.meu.model.Cidade;
import br.edu.fema.tcc.meu.model.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    CidadeService cidadeService;

    @Autowired
    ServicoRepository servicoRepository;

    @Override
    public void run(String... args) throws Exception {
        cidadeService.salvar(Cidade.builder().nome("Assis").uf("SP").ibge(1).build());
        cidadeService.salvar(Cidade.builder().nome("Cândido Mota").uf("SP").ibge(2).build());
        cidadeService.salvar(Cidade.builder().nome("Londrina").uf("PR").ibge(3).build());
        System.out.println("Carga de dados de Cidade realizada com sucesso");

        servicoRepository.save(Servico.builder().descricao("Pedreiro").valor(BigDecimal.valueOf(1000.00)).build());
        servicoRepository.save(Servico.builder().descricao("Job").valor(BigDecimal.valueOf(2000.00)).build());
        System.out.println("Carga de dados de Serviço realizada com sucesso");
    }
}
