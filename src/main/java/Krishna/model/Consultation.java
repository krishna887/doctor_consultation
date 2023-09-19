package Krishna.model;

import io.micronaut.context.annotation.Bean;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Introspected
@Serdeable
@Bean

public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Duration duration;
    @Enumerated(EnumType.STRING)
    private  type calltype;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private  Doctor doctor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public type getCalltype() {
        return calltype;
    }

    public void setCalltype(type calltype) {
        this.calltype = calltype;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public enum type {
        SHORT,
        MEDIUM,
        LONG
    }



}
