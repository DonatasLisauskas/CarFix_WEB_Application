package repositories;

import entities.CarService;
import org.springframework.data.repository.CrudRepository;

public interface CarServiceRepository extends CrudRepository<CarService, Long> {
}
