package org.uvsq21400579;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class AppTest
{
    @Test
    public void testPersonnel() {
        Personnel p = new Personnel.Builder("Dylan", "Bob", "Singer").build();
        assertEquals("Dylan", p.getName());
    }

    @Test
    public void testPersonnelDate() {
        List<String> tmp = new ArrayList<>();
        LocalDate t = LocalDate.now();
        Personnel p = new Personnel.Builder("Dylan", "Bob", "Singer").updateBirthDate(t).build();
        assertEquals(LocalDate.now(), p.getLocalDate());
    }

    @Test
    public void testPersonnelPhone() {
        List<String> tmp = new ArrayList<>();
        Personnel p = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        String tel = new String("0324657321");
        tmp.add(tel);
        tmp.add("312576865");
        assertEquals("Dylan", p.getName());
        assertEquals("0324657321", p.getPhone().get(0));
        assertEquals("312576865", p.getPhone().get(1));
    }

    @Test
    public void testIterator() {
        List<String> tmp = new ArrayList<>();
        Personnel personnel = new Personnel.Builder("Dylan", "Bob", "Singer").updatePhoneList(tmp).build();
        Annuaire annuaire = Annuaire.getInstance();
        String phone = new String("0324657321");
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
        String phone = new String("0324657321");
        tmp.add(phone);
        tmp.add("312576865");
        Node<Team> Team_1 = new Node<Team>(personnel);
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
        String phone = new String("0324657321");
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
        String phone = new String("0324657321");
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
}
