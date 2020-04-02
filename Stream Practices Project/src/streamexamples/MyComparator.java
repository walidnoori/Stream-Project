/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamexamples;

import java.util.Comparator;

/**
 *
 * @author student
 */
public class MyComparator implements Comparator<Person>{

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
    
}
