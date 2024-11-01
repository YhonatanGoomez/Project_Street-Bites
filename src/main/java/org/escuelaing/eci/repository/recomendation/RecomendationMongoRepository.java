package org.escuelaing.eci.repository.recomendation;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendationMongoRepository extends MongoRepository<Recomendation, String> {
    
}