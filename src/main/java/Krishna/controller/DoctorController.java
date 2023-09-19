package Krishna.controller;

import Krishna.model.Consultation;
import Krishna.model.Doctor;

import Krishna.repository.ConsultationRepository;
import Krishna.repository.DoctorRepository;
import Krishna.service.Service;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;


import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

@Controller("/doctor")
public  class DoctorController {

    private final Doctor doctor;

    private final Consultation consultation;
    private final Service service;


    public DoctorController(Service service, DoctorRepository doctorRepository , Doctor doctor, Consultation consultation, ConsultationRepository consultationRepository) {
        this.service = service;
        this.doctor=doctor;
        this.consultation=consultation;

    }


    @Get ("/")
    
    public List<Doctor> get1(){ return service.getDoctor();}

    @Post("/create")
    public Doctor post(){ return  service.createDoctor(doctor);}

    @Post("/update")
    public  Doctor update(){
        return  service.updateDoctor(doctor);
    };
    @Delete("/delete/{id}")
    public  Doctor delete(long id){
        return  service.deleteDoctor(id);
    };

    @Get("/consultations")
    public List<Consultation> getC(@PathVariable Long id){
        return (List<Consultation>) service.allConsultations();
    }





    @Post("/consultations/create")
    public Consultation postc(){
        return service.createConsultations(consultation);
    }




    @Get("consultations/{id}")
    public List<Consultation> getCc(@PathVariable Long id){
        return service.getConsultations(id);
    };

    @Status(HttpStatus.OK)
    @Get("consultations/total/{id}")
    public  Map<String, Double> getDurat(@PathVariable Long id){
        return service.getDuration(id);
    };
    @Get("total/{id}")
    public  BigDecimal getTim(@PathVariable Long id){
        return service.getTime(id);
    };

}
