package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       patientRepository.save( new Patient(1,"yasine", new Date(), false, 14));
        patientRepository.save( new Patient(2,"mohame",new Date(), false, 34));
        patientRepository.save( new Patient(3,"imane",new Date(), true, 15));
        patientRepository.save( new Patient(4,"mohame",new Date(), false, 17));
        patientRepository.save( new Patient(5,"aboubakr",new Date(), false, 87));
        patientRepository.save( new Patient(6,"ihsane",new Date(), true, 16));
    }
}
