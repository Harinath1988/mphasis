package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8Streams {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,6,7,8,9,10);
        numbers.stream().filter(element -> element%2 == 0)
                .map(element -> element*2)
                .reduce(0, (total, element) -> total+element);

        List<Integer> doubleOfEven = numbers.stream().filter(element -> element%2==0)
                .map(element -> element*2)
                .collect(Collectors.toList());
        System.out.println(doubleOfEven);

        List<Student> list = getStudents();
        Map<Object, Object> map = list.stream()
                .collect(Collectors.toMap(student -> student.getId(), student -> student));
                System.out.println(map);

        Map<String, List<Student>> map2 = list.stream()
                .collect(Collectors.groupingBy(Student::getName));
        System.out.println(map2);

        Map<String, List<String>> map3 = list.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(map3);
    }

    private static List<Student> getStudents() {
        return Arrays.asList(
                new Student(1, "hari"),
                new Student(2, "giri"),
                new Student(3, "suri"),
                new Student(4, "hari")
        );
    }
}

class Student{

    int id;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Student(){

    }
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


}
