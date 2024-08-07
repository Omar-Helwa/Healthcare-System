package com.database;

import com.functions.Person;

public interface Database {

    <T extends Person> boolean delete(T doctor);

    abstract <T extends Person> boolean save();

}
