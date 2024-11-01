package org.escuelaing.eci.service.recomendation;


import java.util.List;
import java.util.Optional;

import org.escuelaing.eci.repository.recomendation.Recomendation;
import org.escuelaing.eci.repository.recomendation.RecomendationMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecomendationServiceMongoDb implements RecomendationService {

    private final RecomendationMongoRepository RecomendationMongoRepository;

    @Autowired
    public RecomendationServiceMongoDb(RecomendationMongoRepository RecomendationMongoRepository) {
        this.RecomendationMongoRepository = RecomendationMongoRepository;
    }

    @Override
    public Recomendation save(Recomendation Recomendation) {
        return RecomendationMongoRepository.save(Recomendation);
    }

    @Override
    public Optional<Recomendation> findById(String id) {
        return RecomendationMongoRepository.findById(id);
    }

    @Override
    public List<Recomendation> all() {
        return RecomendationMongoRepository.findAll();
    }

    @Override
    public Recomendation deleteById(String id) {
        Optional<Recomendation> Recomendation = RecomendationMongoRepository.findById(id);
        if (Recomendation.isPresent()) {
            RecomendationMongoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Recomendation with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public Recomendation update(Recomendation Recomendation, String RecomendationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    
}
