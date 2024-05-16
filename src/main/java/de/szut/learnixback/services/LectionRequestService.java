package de.szut.learnixback.services;

import de.szut.learnixback.entities.LectionRequest;
import de.szut.learnixback.repositories.LectionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectionRequestService {

    @Autowired
    private LectionRequestRepository lectionRequestRepository;

    public List<LectionRequest> getAllLectionRequests() {
        return lectionRequestRepository.findAll();
    }

    public List<LectionRequest> getAllLectionRequestByUser(String userGUID){
        return getAllLectionRequests().stream()
                .filter(p -> p.getApplicantGUID().equals(userGUID))
                .collect(Collectors.toList());
    }

    public Optional<LectionRequest> getLectionRequestById(Long id) {
        return lectionRequestRepository.findById(id);
    }

    public LectionRequest createLectionRequest(LectionRequest lectionRequest) {
        return lectionRequestRepository.save(lectionRequest);
    }

    public boolean deleteLectionRequest(Long id) {
        if (lectionRequestRepository.existsById(id)) {
            lectionRequestRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}