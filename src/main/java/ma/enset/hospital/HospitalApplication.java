package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner{

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       patientRepository.save( new Patient(1,"yasine", new Date(), false, 142));
        patientRepository.save( new Patient(2,"mohame",new Date(), false, 347));
        patientRepository.save( new Patient(3,"imane",new Date(), true, 153));
        patientRepository.save( new Patient(4,"mohame",new Date(), false, 127));
        patientRepository.save( new Patient(5,"aboubakr",new Date(), false, 187));
        patientRepository.save( new Patient(6,"ihsane",new Date(), true, 165));
    }

//    @Bean
    CommandLineRunner CommandLineRunnerJdbcUser(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build()
            );

            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ROLE", "ADMIN").build()
            );
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
