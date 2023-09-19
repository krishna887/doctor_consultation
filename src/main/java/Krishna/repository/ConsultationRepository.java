package Krishna.repository;

import Krishna.model.Consultation;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface ConsultationRepository extends CrudRepository <Consultation , Long> {
    List<Consultation> findByDoctorId(Long consultantId);
    @Query(value = "SELECT SUM(CAST(c.duration AS FLOAT )) FROM Consultation c WHERE c.doctor.id = :doctorId")
    BigDecimal getTotalConsultationTimeByDoctorId(Long doctorId);
}
