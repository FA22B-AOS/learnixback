package de.szut.learnixback.services;

import de.szut.learnixback.controller.LectionController;
import de.szut.learnixback.entities.Lection;
import de.szut.learnixback.entities.LectionProgress;
import de.szut.learnixback.repositories.LectionProgressRepository;
import de.szut.learnixback.repositories.LectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectionService {

    @Autowired
    private LectionRepository lectionRepository;

    @Autowired
    private LectionProgressRepository lectionProgressRepository;

    public List<Lection> getAllLections() {
        return lectionRepository.findAll();
    }

    public List<Lection> getSubscribedLections(String userGUID){
        List<Long> lectionIds = this.lectionProgressRepository.findAll()
                .stream()
                .filter(p -> p.getUserGUID().equals(userGUID))
                .map(LectionProgress::getLectionID)
                .collect(Collectors.toList());

        return this.lectionRepository.findAllById(lectionIds);
    }

    public Optional<Lection> getLectionById(Long id) {
        return lectionRepository.findById(id);
    }

    public Lection createLection(Lection lection) {
        return lectionRepository.save(lection);
    }

    public Lection updateLection(Long id, Lection updatedLection) {
        if (lectionRepository.existsById(id)) {
            updatedLection.setLectionId(id);
            return lectionRepository.save(updatedLection);
        } else {
            return null;
        }
    }

    public boolean deleteLection(Long id) {
        if (lectionRepository.existsById(id)) {
            lectionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
