package Krishna.controller;

import Krishna.model.Consultation;
import Krishna.model.Doctor;

import Krishna.repository.ConsultationRepository;
import Krishna.repository.DoctorRepository;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller("/doctor")
public class DoctorController {
    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private ConsultationRepository consultationRepository;
    @Get ("/")
    public Iterable<Doctor>getDoctor(){
        return doctorRepository.findAll();
    }
    @Post("/create")
    public Doctor createDoctor(@Body Doctor doctor){
        return doctorRepository.save(doctor);
    }
    @Post("/update")
    public Doctor updateDoctor(@Body Doctor doctor){
        Optional<Doctor> doctor1=doctorRepository.findById(doctor.getId());
        Doctor doctor2=doctor1.get();
        doctor2.setName(doctor.getName());
        doctor2.setNmc_no(doctor.getNmc_no());
        return doctorRepository.update(doctor2);

    }
    @Delete("/delete/{id}")
    public Doctor deleteDoctor(@PathVariable Long id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        Doctor doctor1 = doctor.get();
        doctorRepository.delete(doctor1);
        return doctor1;
    }

    @Get("/consultations")
    public Iterable<Consultation> allConsultations(){
        return consultationRepository.findAll();

    }

    @Post("/consultations/create")
    public Consultation createConsultations(@Body Consultation consultation){
        return consultationRepository.save(consultation);
    }

    @Get("consultations/{id}")
    public List<Consultation> getConsultations(@PathVariable Long id){
        List<Consultation> consultation = consultationRepository.findByDoctorId(id);

        return consultation;


    }
    @Status(HttpStatus.OK)
    @Get("consultations/total/{id}")
    public Map<String, Double> getDuration(@PathVariable Long id){
        List<Consultation> consultations = getConsultations(id);

        Duration totalDuration = Duration.ZERO;

        for (Consultation consultation : consultations) {
            Duration consultationDuration = consultation.getDuration();
            if (consultationDuration != null) {
                totalDuration = totalDuration.plus(consultationDuration);
            }
        }

        Map<String, Double> response = new HashMap<>();
        response.put("totalDuration", totalDuration.toHours() +
                (totalDuration.toMinutes() % 60) / 60.0);
        return response;


    }
    @Get("total/{id}")
    public BigDecimal getTime(@PathVariable Long id){
        BigDecimal duration = consultationRepository.getTotalConsultationTimeByDoctorId(id);
        return duration;
    }

}
