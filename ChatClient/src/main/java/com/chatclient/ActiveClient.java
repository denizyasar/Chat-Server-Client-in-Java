/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActiveClient {

    private String name;
    private Socket socket;

    private static ActiveClient instance;

    private DataOutputStream dout;

    private ActiveClient() {

    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void init(String name, Socket socket) {
        instance.name = name;
        instance.socket = socket;

        try {
            instance.dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ActiveClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ActiveClient getInstance() {
        if (instance == null) {
            instance = new ActiveClient();
        }
        return instance;
    }

    public void sendMessage(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException ex) {
            Logger.getLogger(ActiveClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receiveMessage() {
        DataInputStream din;
        try {
            din = new DataInputStream(socket.getInputStream());
            return din.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ActiveClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
