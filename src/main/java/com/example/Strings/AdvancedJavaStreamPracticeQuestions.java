//package com.example.Strings;
//
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.lang.System.out;
//
//public class AdvancedJavaStreamPracticeQuestions {
//
//    public static class Employee {
//        String name;
//        String dept;
//        String role;
//
//        public Employee(String name, String dept, String role) {
//            this.name = name;
//            this.dept = dept;
//            this.role = role;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getDept() {
//            return dept;
//        }
//
//        public void setDept(String dept) {
//            this.dept = dept;
//        }
//
//        public String getRole() {
//            return role;
//        }
//
//        public void setRole(String role) {
//            this.role = role;
//        }
//    }
//
//    public static void main(String[] args) {
////input :
//        List<Employee> emp = List.of(
//                new Employee("Alice", "Engineering", "Developer"),
//                new Employee("Bob", "Engineering", "Tester"),
//                new Employee("Charlie", "HR", "Recruiter"),
//                new Employee("David", "Engineering", "Developer")
//        );
//
//        //output : Map<String, Map<String, List<String>>>
//        //// {
//        ////   Engineering: { Developer: [Alice, David], Tester: [Bob] },
//        ////   HR: { Recruiter: [Charlie] }
//        //// }
//
//        emp.stream().collect(Collectors.groupingBy(x -> x.dept, Collectors.collectingAndThen(
//                Collectors.groupingBy(p->p.role)
//        ))
//
//
//    }
//}
