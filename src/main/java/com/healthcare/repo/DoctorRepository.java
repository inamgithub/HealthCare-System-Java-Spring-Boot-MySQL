/*
    *  Repository interface exposing CRUD funconality for Doctor Enty.
    ● It must contain the methods for:
        ○ Finding all doctors by speciality.
        ○ Finding all doctors by name.
    ● You can go ahead and add any custom methods as per requirements
 */



package com.healthcare.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthcare.entity.Doctor;
import java.util.*;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Custom methods removed. Now you can practice implementing them.


    // finding all doctors by theri speciality
    List<Doctor> findBySpeciality(String speciality);

    // finding all doctors by name
    List<Doctor> findByName(String name);


}
