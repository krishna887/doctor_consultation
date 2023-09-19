package Krishna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.context.annotation.Bean;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Introspected
@Serdeable
@Bean
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int nmc_no;
    @JsonIgnore
   @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<Consultation> consultation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNmc_no() {
        return nmc_no;
    }

    public void setNmc_no(int nmc_no) {
        this.nmc_no = nmc_no;
    }

    public List<Consultation> getConsultation() {
        return consultation;
    }

    public void setConsultation(List<Consultation> consultation) {
        this.consultation = consultation;
    }
}
