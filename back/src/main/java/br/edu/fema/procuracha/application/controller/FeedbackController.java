package br.edu.fema.procuracha.application.controller;

import br.edu.fema.procuracha.application.form.FeedbackForm;
import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import br.edu.fema.procuracha.domain.repository.ContratoRepository;
import br.edu.fema.procuracha.domain.repository.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackRepository feedbackRepository;

    private ContratoRepository contratoRepository;

    public FeedbackController(FeedbackRepository feedbackRepository,
                              ContratoRepository contratoRepository) {
        this.feedbackRepository = feedbackRepository;
        this.contratoRepository = contratoRepository;
    }

    @GetMapping("/buscar/{idProfissional}")
    public List<FeedbackEntity> getFeedback(@PathVariable Long idProfissional) {
        return this.feedbackRepository.findAllByContratoEntity_ClienteEntity_IdAndAceitoTrueAndResolvidoFalseAndNotaAndDescricao(idProfissional, 0l, "");
    }

    @GetMapping("/aceitar/{idContrato}")
    public FeedbackEntity aceitarFeedback(@PathVariable Long idContrato) {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setAceito(true);
        feedback.setResolvido(false);
        feedback.setDescricao("");
        feedback.setNota(0);
        feedback.setContratoEntity(this.contratoRepository.findById(idContrato).get());

        return this.feedbackRepository.save(feedback);
    }

    @PostMapping("/{id}")
    public FeedbackEntity postFeedback(@RequestBody @Valid FeedbackForm feedbackForm, @PathVariable Long id) {
        System.out.println(feedbackForm.getDescricao());
        return this.feedbackRepository.save(feedbackForm.converterFormForEntity(
                this.feedbackRepository.findById(id).get()
                )
        );
    }

}
