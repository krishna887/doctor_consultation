package Krishna.service;

import Krishna.model.Consultation;
import Krishna.model.Doctor;
import Krishna.repository.ConsultationRepository;
import Krishna.repository.DoctorRepository;
import io.micronaut.context.annotation.Bean;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.hibernate.service.spi.InjectService;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Bean
public class Service {

    private DoctorRepository doctorRepository;
    private ConsultationRepository consultationRepository;

    Service(DoctorRepository doctorRepository, ConsultationRepository consultationRepository) {
        this.doctorRepository = doctorRepository;
        this.consultationRepository=consultationRepository;
    }

    public List<Doctor> getDoctor() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public Doctor updateDoctor(@Body Doctor doctor) {
        Optional<Doctor> doctor1 = doctorRepository.findById(doctor.getId());
        Doctor doctor2 = doctor1.get();
        doctor2.setName(doctor.getName());
        doctor2.setNmc_no(doctor.getNmc_no());
        return doctorRepository.update(doctor2);

    }

    public Doctor createDoctor(@Body Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor deleteDoctor(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        Doctor doctor1 = doctor.get();
        doctorRepository.delete(doctor1);
        return doctor1;
    }
    public BigDecimal getTime(@PathVariable Long id){
        BigDecimal duration = consultationRepository.getTotalConsultationTimeByDoctorId(id);
        return duration;
    }
    public List<Consultation> getConsultations(@PathVariable Long id){

        return consultationRepository.findByDoctorId(id);


    }
    public Consultation createConsultations(@Body Consultation consultation){
        return consultationRepository.save(consultation);
    }
    public Map<String, Double> getDuration(@PathVariable Long id){
        List<Consultation> consultations = getConsultations(id);

        Duration totalDuration = Duration.ZERO;
//
//        for (Consultation consultation : consultations) {
//            Duration consultationDuration = consultation.getDuration();
//            if (consultationDuration != null) {
//                totalDuration = totalDuration.plus(consultationDuration);
//            }
//        }

        Map<String, Double> response = new HashMap<>();
        response.put("totalDuration", totalDuration.toHours() +
                (totalDuration.toMinutes() % 60) / 60.0);
        return response;


    }
    public Iterable<Consultation> allConsultations(){
       return consultationRepository.findAll();
    }
}

