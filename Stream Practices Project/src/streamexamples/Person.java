/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamexamples;

import java.time.LocalDate;

/**
 *
 * @author admin
 */
public class Person {
    private String name;
    private LocalDate dateOfBirth;
    private int weight;

    public Person(String name, LocalDate dateOfBirth, int weight) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + ": " + dateOfBirth + " (" + weight + '}';
    }
    
}
