package br.edu.fema.procuracha.application.dto;

import br.edu.fema.procuracha.domain.entity.FeedbackEntity;
import br.edu.fema.procuracha.domain.entity.ProfissionalEntity;
import br.edu.fema.procuracha.domain.repository.FeedbackRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfissionalDTO {

    private Long id;

    private Long mediaFeedback;

    private List<FeedbackProfissionalDTO> feedbacksProfissional;

    private ClienteDTO cliente;

    private List<ServicoDTO> servicos;

    public ProfissionalDTO(ProfissionalEntity profissionalEntity) {
        this.id = profissionalEntity.getId();
        this.cliente = new ClienteDTO(profissionalEntity.getClienteEntity());
        this.servicos = ServicoDTO.converterEntityForDTO(profissionalEntity.getServicoEntities());
    }

    public ProfissionalDTO(ProfissionalEntity profissionalEntity, List<FeedbackEntity> feedbackEntity) {
        this.id = profissionalEntity.getId();
        this.cliente = new ClienteDTO(profissionalEntity.getClienteEntity());
        this.servicos = ServicoDTO.converterEntityForDTO(profissionalEntity.getServicoEntities());
        this.mediaFeedback = 0L;
        if (feedbackEntity != null) {
            this.feedbacksProfissional = FeedbackProfissionalDTO.converterEntityForDto(feedbackEntity);
            this.feedbacksProfissional.stream()
                    .forEach(profissional -> this.mediaFeedback += profissional.getNota());
            this.mediaFeedback /= feedbacksProfissional.size();
        } else {
            this.feedbacksProfissional = new ArrayList<>(); // ou null, dependendo da sua necessidade
        }
    }

    public static List<ProfissionalDTO> converterEntityForDTO(List<ProfissionalEntity> profissionalEntity) {
        return profissionalEntity.stream().map(ProfissionalDTO::new).toList();
    }

    public static List<ProfissionalDTO> converter(List<ProfissionalEntity> profissionalEntities, FeedbackRepository feedbackRepository) {
        return profissionalEntities.stream()
                .map(profissionalEntity -> new ProfissionalDTO(profissionalEntity,
                        feedbackRepository.findAllByContratoEntity_ProfissionalEntity_Id(profissionalEntity.getId())))
                .collect(Collectors.toList());
    }

}
