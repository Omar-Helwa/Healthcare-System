package com.database;

import com.functions.Doctor;
import com.functions.Patient;
import com.functions.Person;

public interface Database {

    <T extends Person> boolean delete(T doctor);

    abstract <T extends Person> boolean save();

}
