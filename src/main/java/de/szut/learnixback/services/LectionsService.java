package de.szut.learnixback.services;


import de.szut.learnixback.entities.Lections;
import de.szut.learnixback.repositories.LectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectionsService {

    @Autowired
    private LectionsRepository lectionsRepository;

    public Lections createLections(Lections lections) {
        return lectionsRepository.save(lections);
    }

    public List<Lections> getAllLections() {
        return lectionsRepository.findAll();
    }

    public Lections getLectionsById(int id) {
        return lectionsRepository.findById(id).orElse(null);
    }

    public Lections updateLections(int id, Lections updatedLections) {
        Lections existingLections = lectionsRepository.findById(id).orElse(null);
        if (existingLections != null) {
            existingLections.setCreatorGuid(updatedLections.getCreatorGuid());
            // Hier weitere Aktualisierungen, falls notwendig
            return lectionsRepository.save(existingLections);
        }
        return null;
    }

    public void deleteLections(int id) {
        lectionsRepository.deleteById(id);
    }
}
