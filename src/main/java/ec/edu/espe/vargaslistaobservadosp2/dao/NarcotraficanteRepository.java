package ec.edu.espe.vargaslistaobservadosp2.dao;

import ec.edu.espe.vargaslistaobservadosp2.model.Narcotraficantes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NarcotraficanteRepository extends MongoRepository<Narcotraficantes, String> {
    Optional<Narcotraficantes> findByNombreCompleto(String NombreCompleto);
}
