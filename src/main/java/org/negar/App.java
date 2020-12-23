package org.negar;

import org.negar.config.ComponentScanConfig;
import org.negar.models.Student;
import org.negar.service.StudentManagement;
import org.negar.util.UserInputService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static StudentManagement management;
    private static UserInputService inputService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        management = context.getBean(StudentManagement.class);
        inputService = context.getBean(UserInputService.class);

        int userChoice = 0;
        do {

            System.out.println("=====================Student Management=====================");
            System.out.println("1- Insert a new student");
            System.out.println("2- Update a student by Id");
            System.out.println("3- Remove a student by Id");
            System.out.println("4- Find a student by Id");
            System.out.println("5- Print all students");
            System.out.println("Other- Exit");

            userChoice = inputService.getInt();
            Student student = null;
            int id = 0;
            switch (userChoice) {
                case 1:
                    student = management.create();
                    Student savedStudent = management.save(student);
                    System.out.print("New student id:");
                    System.out.println(savedStudent.getId());
                    break;
                case 2:
                    System.out.println("Enter Id for edit:");
                    try{
                        id = inputService.getInt();

                        student = management.find(id);
                        System.out.println("Enter new name:");

                        String newName = inputService.getString();
                        student.setName(newName);
                        management.edit(student);
                    } catch (RuntimeException ex){
                        System.out.println("Id was not found!!");
                    }
                    break;
                case 3:
                    System.out.println("Enter Id for delete:");
                    id = inputService.getInt();
                    boolean removeResult = management.remove(id);
                    if (removeResult)
                        System.out.println("Student successfully deleted");
                    else
                        System.out.println("Id was not found");
                    break;
                case 4:
                    System.out.println("Enter Id for find:");
                    try{
                        id = inputService.getInt();

                        student = management.find(id);
                        System.out.println("Student Detail");
                        System.out.println(student);
                    } catch (RuntimeException ex){
                        System.out.println("Id was not found!!");
                    }
                    break;
                case 5:
                    System.out.println("Print all students:");

                    for(Student tempStudent : management.findAll())
                        System.out.println(tempStudent);

                    break;
            }
        }while (userChoice >= 1 && userChoice <= 5);

        context.close();
    }
}

