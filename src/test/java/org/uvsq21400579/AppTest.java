package org.uvsq21400579;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class AppTest {
    @Test
    public void testEmployee() {
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").build();
        assertEquals("Dylan", employee.getName());
    }

    @Test
    public void testEmployeeDate() {
        LocalDate localDate = LocalDate.now();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updateBirthDate(localDate).build();
        assertEquals(LocalDate.now(), employee.getLocalDate());
    }

    @Test
    public void testEmployeePhone() {
        List<String> testArray = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(testArray).build();
        String phone = "0324657321";
        testArray.add(phone);
        testArray.add("312576865");
        assertEquals("Dylan", employee.getName());
        assertEquals("0324657321", employee.getPhone().get(0));
        assertEquals("312576865", employee.getPhone().get(1));
    }

    @Test
    public void testIterator() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Directory directory = Directory.getInstance();
        directory.addTeam(employee);
        directory.addTeam(new Group("group_1"));
        directory.addTeam(new Group("group_2"));
    }

    @Test
    public void testNode() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Node<Team> Team_1 = new Node<>(employee);
        Team_1.addElement(new Group("group_1"));
        Team_1.addElement(employee);
        Team_1.addElement(new Group("group_2"));
        Node<Team> iterator = Team_1;
        while(iterator.hasNext()) {
            iterator.getElement().printName();
            iterator = iterator.getNext();
        }
    }


    @Test
    public void testIteratorGroup() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Group group = new Group("Test");
        group.addMember(employee);
        group.addMember(new Group("group_2"));
        for(Team team : group) {
            team.printName();
        }
    }


    @Test
    public void testDirectory() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Employee employee2 = new Employee.Builder("Hendrix", "Jimmy", "GuitarHero").updatePhoneList(tmp).build();
        Directory directory = Directory.getInstance();
        Group group_1 = new Group("TEST_1");
        Group group_2 = new Group("TEST_2");
        group_1.addMember(employee);
        group_1.addMember(employee2);
        directory.addTeam(group_1);
        directory.addTeam(group_2);
        System.out.println("Start");
        for(Team team : directory) {
            team.printName();
        }
    }

    @Test
    public void serializationWriteReadTest() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Employee employee2 = new Employee.Builder("Hendrix", "Jimmy", "GuitarHero").updatePhoneList(tmp).build();
        Directory directory = Directory.getInstance();
        Group group_1 = new Group("TEST_1");
        Group group_2 = new Group("TEST_2");
        group_1.addMember(employee);
        group_1.addMember(employee2);
        group_2.addMember(employee);
        directory.addTeam(employee);
        directory.addTeam(new Group("group_1"));
        directory.addTeam(new Group("group_2"));
        directory.addTeam(group_1);
        directory.addTeam(group_2);
        for (Team team : directory) {
            team.printName();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(
            new FileOutputStream("./src/test/java/org/uvsq21400579/dataFile")))) {
            outputStream.writeObject(directory);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
            new FileInputStream("./src/test/java/org/uvsq21400579/dataFile")))) {
            Directory test = (Directory) inputStream.readObject();
            System.out.println("TEST");
            for (Team team : test) {
                team.printName();
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void DirectoryDAOTest() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Employee employee2 = new Employee.Builder("Hendrix", "Jimmy", "GuitarHero").updatePhoneList(tmp).build();
        DAO<Directory> dao = DAOFactory.getDirectoryDAO();
        Directory directory = Directory.getInstance();
        Group group_1 = new Group("group_1");
        Group group_2 = new Group("group_2");
        group_1.addMember(employee);
        group_1.addMember(employee2);
        group_2.addMember(employee2);
        directory.addTeam(group_1);
        directory.addTeam(group_2);
        dao.create(directory);
        Directory test = (Directory) dao.find("directory");
        dao.delete("directory");
    }

    @Test
    public void EmployeeDAOTest() {
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        DAO<Employee> dao = DAOFactory.getEmployeeDAO();
        dao.create(employee);
        Employee test = (Employee) dao.find("employee");
        dao.delete("employee");
    }

    @Test
    public void GroupDAOTest(){
        List<String> tmp = new ArrayList<>();
        Employee employee = new Employee.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Employee employee2 = new Employee.Builder("Hendrix", "Jimmy", "GuitarHero").updatePhoneList(tmp).build();
        DAO<Group> dao = DAOFactory.getGroupDAO();
        Group group = new Group("group_1");
        group.addMember(employee);
        group.addMember(employee2);
        dao.create(group);
        Group test = (Group) dao.find("group");
        dao.delete("group");
    }

}
