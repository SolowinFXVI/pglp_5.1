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

public class AppTest
{
    @Test
    public void testPersonnel() {
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").build();
        assertEquals("Dylan", personnel.getName());
    }

    @Test
    public void testPersonnelDate() {
        LocalDate localDate = LocalDate.now();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updateBirthDate(localDate).build();
        assertEquals(LocalDate.now(), personnel.getLocalDate());
    }

    @Test
    public void testPersonnelPhone() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        String phone = "0324657321";
        tmp.add(phone);
        tmp.add("312576865");
        assertEquals("Dylan", personnel.getName());
        assertEquals("0324657321", personnel.getPhone().get(0));
        assertEquals("312576865", personnel.getPhone().get(1));
    }

    @Test
    public void testIterator() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Annuaire annuaire = Annuaire.getInstance();
        String phone = "0324657321";
        tmp.add(phone);
        tmp.add("312576865");
        annuaire.addTeam(personnel);
        annuaire.addTeam(new Group("Group_1"));
        annuaire.addTeam(new Group("Group_2"));
        annuaire.addTeam(new Group("Group_3"));
        annuaire.addTeam(new Group("Group_4"));
        System.out.println("Start");
        for(Team e : annuaire) {
            e.printName();
        }
    }

    @Test
    public void testNode() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        String phone = "0324657321";
        tmp.add(phone);
        tmp.add("312576865");
        Node<Team> Team_1 = new Node<>(personnel);
        Team_1.addElement(new Group("group_1"));
        Team_1.addElement(personnel);
        Team_1.addElement(new Group("group_2"));
        Team_1.addElement(new Group("group_3"));

        Node<Team> iterator = Team_1;
        while(iterator.hasNext()) {
            iterator.getElement().printName();
            iterator = iterator.getNext();
        }
    }


    @Test
    public void testIteratorGroup() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Group group = new Group("Test");
        String phone = "0324657321";
        tmp.add(phone);
        tmp.add("312576865");
        group.addMember(personnel);
        group.addMember(new Group("group_2"));
        for(Team team : group) {
            team.printName();
        }
    }


    @Test
    public void testAnnuaire() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Personnel personnel2 = new Personnel.Builder("Hendrix", "Jimmy", "GuitarHero").updatePhoneList(tmp).build();
        Annuaire annuaire = Annuaire.getInstance();
        String phone = "0324657321";
        Group group_1 = new Group("TEST_1");
        Group group_2 = new Group("TEST_2");
        tmp.add(phone);
        tmp.add("312576865");
        group_1.addMember(personnel);
        group_1.addMember(personnel2);
        group_2.addMember(personnel);
        annuaire.addTeam(personnel);
        annuaire.addTeam(new Group("Groupe_1"));
        annuaire.addTeam(new Group("Group_2"));
        annuaire.addTeam(new Group("Group_3"));
        annuaire.addTeam(new Group("Group_4"));
        annuaire.addTeam(group_1);
        annuaire.addTeam(group_2);
        System.out.println("Start");
        for(Team team : annuaire) {
            team.printName();
        }
    }

    @Test
    public void serializationWriteReadTest() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp)
            .build();
        Personnel personnel2 = new Personnel.Builder("Hendrix", "Jimmy", "GuitarHero")
            .updatePhoneList(tmp).build();
        Annuaire annuaire = Annuaire.getInstance();
        String phone = "0324657321";
        Group group_1 = new Group("TEST_1");
        Group group_2 = new Group("TEST_2");
        tmp.add(phone);
        tmp.add("312576865");
        group_1.addMember(personnel);
        group_1.addMember(personnel2);
        group_2.addMember(personnel);
        annuaire.addTeam(personnel);
        annuaire.addTeam(new Group("Groupe_1"));
        annuaire.addTeam(new Group("Group_2"));
        annuaire.addTeam(new Group("Group_3"));
        annuaire.addTeam(new Group("Group_4"));
        annuaire.addTeam(group_1);
        annuaire.addTeam(group_2);
        for (Team team : annuaire) {
            team.printName();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(
            new FileOutputStream("./src/test/java/org/uvsq21400579/dataFile")))) {
            outputStream.writeObject(annuaire);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
            new FileInputStream("./src/test/java/org/uvsq21400579/dataFile")))) {
            Annuaire test = (Annuaire) inputStream.readObject();
            System.out.println("TEST");
            for (Team team : test) {
                team.printName();
            }
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        //TODO assert
    }

}
