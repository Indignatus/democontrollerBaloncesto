package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Alan on 10/10/2016.
 */
@Entity
public class coches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

}
