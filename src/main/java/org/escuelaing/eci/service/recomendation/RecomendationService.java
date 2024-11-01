package org.escuelaing.eci.service.recomendation;


import java.util.List;
import java.util.Optional;

import org.escuelaing.eci.repository.recomendation.Recomendation;




public interface RecomendationService {

    Recomendation save(Recomendation Recomendation);

    Optional<Recomendation> findById(String id);

    List<Recomendation> all();

    Recomendation deleteById(String id);

    Recomendation update(Recomendation Recomendation, String RecomendationId);
}
