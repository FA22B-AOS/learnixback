package de.szut.learnixback.services;

import de.szut.learnixback.entities.LectionProgress;
import de.szut.learnixback.keyclasses.LectionProgressKey;
import de.szut.learnixback.repositories.LectionProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LectionProgressService {

    @Autowired
    private LectionProgressRepository lectionProgressRepository;

    public LectionProgress createLectionProgress(UUID userGUID, Long lectionID){
        LectionProgressKey id = new LectionProgressKey(userGUID, lectionID);
        if(this.lectionProgressRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Progress Eintrag existiert bereits");
        else
            return this.lectionProgressRepository.save(new LectionProgress(userGUID, lectionID));
    }

    public LectionProgress updateProgress(UUID userGUID, Long lectionID, Float newProgress){
        LectionProgressKey id = new LectionProgressKey(userGUID, lectionID);
        if(this.lectionProgressRepository.existsById(id)){
            LectionProgress progress = this.lectionProgressRepository.findById(id).get();
            progress.setProgress(newProgress);
            return this.lectionProgressRepository.save(progress);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Eintrag nicht gefunden");
        }
    }

    public boolean deleteProgress(UUID userGUID, Long lectionID){
        LectionProgressKey id = new LectionProgressKey(userGUID, lectionID);
        if(this.lectionProgressRepository.existsById(id)){
            this.lectionProgressRepository.deleteById(id);
            return true;
        } else return false;
    }

    public LectionProgress getLectionProgress(UUID userGUID, Long lectionID){
        LectionProgressKey id = new LectionProgressKey(userGUID, lectionID);
        if(this.lectionProgressRepository.existsById(id))
            return this.lectionProgressRepository.findById(id).get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Eintrag nicht gefunden");
    }

    public List<LectionProgress> getUserProgress(UUID userGUID){
        return this.lectionProgressRepository.findAll().stream().filter(
                p -> p.getUserGUID() == userGUID
        ).collect(Collectors.toList());
    }
}