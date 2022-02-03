/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private String name;
    private Socket socket;

    DataOutputStream dout;

    private static final ArrayList<Client> clients = new ArrayList<Client>();

    public String getName() {
        return name;
    }
   
    public Socket getSocket() {
        return socket;
    }

    public Client(String name, Socket socket) {
        this.name = name;
        this.socket = socket;

        try {
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        clients.add(this);
    }

    public void sendMessage(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receiveMessage() throws IOException {
        DataInputStream din;

        din = new DataInputStream(socket.getInputStream());
        return din.readUTF();

        // return null;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public static String clientNamesToString() {
        String result = "";
        for (Client c : clients) {
            result += c.getName() + "\n";
        }
        return result;
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static Client findClient(String name) {
        for (Client c : clients) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

}
