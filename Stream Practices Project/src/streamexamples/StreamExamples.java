/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamexamples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author admin
 */
public class StreamExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();

        personList.add(new Person("John Smith", LocalDate.of(1992, 3, 30), 80));
        personList.add(new Person("Rebeka Taylor", LocalDate.of(1991, 4, 04), 59));
        personList.add(new Person("József Asztalos", LocalDate.of(1991, 06, 20), 75));
        personList.add(new Person("Johnny Gold", LocalDate.of(1989, 11, 1), 65));
        personList.add(new Person("Jakab Gipsz", LocalDate.of(1990, 10, 20), 76));
        personList.add(new Person("Adam Peterson", LocalDate.of(1993, 9, 21), 71));
        personList.add(new Person("Bobby Ewing", LocalDate.of(1985, 12, 16), 78));

        ex10(personList);

         System.out.println("Which example to run? ");
        Scanner sc = new Scanner(System.in);
        runExample(sc.nextInt(), personList);
        example1(personList); //sort by name
         example2(personList); //sort by age
         example3(personList); //sort by weight
         example4(personList); //print all starting with "J"
         example5(personList); //print all older than 25
               example6(personList); //print all using only capital letters (only the names)
            example7(personList); //print out the youngest person
        example8(personList); //print out the three youngest person
        example9(personList); //print out the average weight of the persons
        example10(personList); //Print out the names of those persons alphabetically ordered using only
         //uppercase letters that are older than 25 years and their names start with "J"
         
    }

    public static void runExample(int i, ArrayList<Person> personList) {
        Class c = StreamExamples.class;
        try {
            Method m = c.getDeclaredMethod("example" + i, ArrayList.class);
            System.out.println(m.getName());
            m.invoke(null, personList);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(StreamExamples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  
    

    /**
     * Print out all the persons alphabetically ordered.
     */
    public static void example1(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 1\n----------------");

        /*Comparator<Person> c = new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        };*/
        //Comparator<Person> c = (Person p1, Person p2) -> {return p1.getName().compareTo(p2.getName());};
        Comparator<Person> c = (p1, p2) -> p1.getName().compareTo(p2.getName());

        pList.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                //.sorted(new MyComparator())
                //.sorted(c)
                //.forEach(System.out::println);
                .forEach(p -> System.out.println(p));

        System.out.println("----------------");
    }

    /**
     * Print out all the persons ordered by age.
     */
    public static void example2(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 2\n----------------");
        pList.stream()
                .sorted((p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()))
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    /**
     * Print out all the persons ordered by weight.
     */
    public static void example3(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 3\n----------------");
        pList.stream()
                .sorted((p1, p2) -> p1.getWeight() - p2.getWeight())
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    /**
     * Print out all the persons starting with "J".
     */
    public static void example4(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 4\n----------------");
        pList.stream()
                .filter(p -> p.getName().startsWith("J"))
                //  .filter(p -> p.getWeight()>70)
                .forEach(p -> System.out.println(p));
        /*.forEach(System.out::println); // method references are lambda expressions in compact form - https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html*/
        System.out.println("----------------");
    }

    /**
     * Print out all the persons older than 25.
     */
    public static void example5(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 5\n----------------");
        pList.stream()
                .filter(p -> (p.getDateOfBirth().isBefore(LocalDate.now().minusYears(25))))
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    /**
     * Print out all the persons names using uppercase letters.
     */
    public static void example6(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 6\n----------------");
        pList.stream()
                //.map(p -> (p.getName().toUpperCase()))
                .map(p -> (LocalDate.now().getYear() - (p.getDateOfBirth().getYear())))
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    /**
     * Print out the youngest person.
     */
    public static void example7(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 7\n----------------");
        pList.stream()
                .min((p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()))
                .ifPresent(System.out::println);
        System.out.println("----------------");
    }

    /**
     * Print out the three youngest person.
     *
     */
    public static void example8(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 8\n----------------");
        List<Person> tempList = pList.stream()
                .sorted((p1, p2) -> p1.getDateOfBirth().compareTo(p2.getDateOfBirth()))
                .collect(Collectors.toList());

        for (int i = 0; i < 3; i++) {
            System.out.println(tempList.get(i));
        }
        System.out.println("----------------");
    }
 
    /**
     * Print out the average weight of the persons
     */
    public static void example9(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 9\n----------------");
        IntSummaryStatistics sumStat = pList.stream()
                .collect(Collectors.summarizingInt(p -> p.getWeight()));

        System.out.println(sumStat.getAverage());

        System.out.println("----------------");
    }
    
      // a solution for getting the avg weight
 /*   public void exampleNo(ArrayList<Person> pList){
        pList.stream()
                .sorted((p1, p2) -> p1.getWeight() - p2.getWeight())
                .mapToInt(p -> p.getWeight())
                .average()
                .ifPresent(System.out::println);
                
    }
                
    }
*/

    /**
     * Print out the names of those persons alphabetically ordered using only
     * uppercase letters that are younger than 25 years and their names start
     * with "J"
     */
    public static void example10(ArrayList<Person> pList) {
        System.out.println("----------------\n Example 10\n----------------");
        pList.stream()
                .filter(p -> p.getName().startsWith("J"))
                .filter(p -> p.getDateOfBirth().isAfter(LocalDate.now().minusYears(25)))
                .map(p -> p.getName().toUpperCase())
                .sorted()
                .forEach(System.out::println);

        System.out.println("----------------");
    }

    public static void ex4(ArrayList<Person> pList) {
        pList.stream()
                .filter(p -> p.getName().length() > 10)
                .forEach(System.out::println);
    }

    public static void ex5(ArrayList<Person> pList) {
        pList.stream()
                //.sorted( (p1,p2) -> p1.getDateOfBirth().getDayOfYear() - p2.getDateOfBirth().getDayOfYear())
                .sorted((p1, p2) -> p1.getDateOfBirth().withYear(0).compareTo(p2.getDateOfBirth().withYear(0)))
                .forEach(System.out::println);
    }

    public static void ex6(ArrayList<Person> pList) {
        pList.stream()
                //.map(p -> p.getName().substring(0, p.getName().indexOf(" ")))
                .map(p -> p.getName().split(" ")[1])
                .forEach(System.out::println);
    }

    public static void ex7(ArrayList<Person> pList) {
        pList.stream()
                .filter(p -> p.getWeight() > 70)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                //sort by the lastnames:
                //.sorted((p1,p2) -> p1.getName().split(" ")[1].compareTo(p2.getName().split(" ")[1]))
                .forEach(System.out::println);
    }

    public static void ex8(ArrayList<Person> pList) {
        pList.stream()
                .reduce((p1, p2) -> {
                    if (p1.getName().length() > p2.getName().length()) {
                        return p1;
                    } else {
                        return p2;
                    }
                })
                .ifPresent(System.out::println);

    }

    public static void ex9(ArrayList<Person> pList) {
        int s = pList.stream()
                .filter( p -> p.getWeight()<70)
                .mapToInt(p -> p.getWeight())
                .sum();
        System.out.println(s);                 
    }
    
    public static void ex10(ArrayList<Person> pList) {
        pList.stream()
                .filter(p -> p.getDateOfBirth().getDayOfYear() >= LocalDate.now().getDayOfYear())
                .filter(p -> p.getDateOfBirth().getDayOfYear() <= LocalDate.now().getDayOfYear()+21)
                .map(p->p.getName().split(" ")[0])
                .forEach(System.out::println);
                }                
    
}
