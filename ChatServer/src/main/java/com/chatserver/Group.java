/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatserver;

import java.util.ArrayList;

public class Group {

    private final String name;
    private final String creator;

    private final ArrayList<Client> clients = new ArrayList<>();
    private static final ArrayList<Group> groups = new ArrayList<>();

    public Group(String name, String creator) {
        this.name = name;
        this.creator = creator;

        groups.add(this);
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public static String groupNamesToString() {
        String result = "";
        for (Group g : groups) {
            result += g.getName() + "\n";
        }
        return result;
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public String clientNamesToString() {
        String result = "";
        for (Client c : clients) {
            result += c.getName() + "\n";
        }

        return result;
    }

    public static Group findGroup(String name) {
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }
}
