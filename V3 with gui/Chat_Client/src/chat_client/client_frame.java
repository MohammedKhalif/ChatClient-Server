package chat_client;

import java.net.*;
import java.io.*;
import java.util.*;

public class client_frame extends javax.swing.JFrame {

    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 8888;
    Boolean isConnected = false;
    int reading = 0;

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    InetAddress IPaddress;

    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    public void userAdd(String data) {
        users.add(data);
    }

    public void userRemove(String data) {
        OutputArea.append(data + " is now offline.\n");
    }

    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList) {
            //users.append(token + "\n");
        }
    }

    public void UserDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            OutputArea.append("Could not send Disconnect message.\n");
        }
    }

    public void Disconnect() {
        try {
            OutputArea.append("Disconnected.\n");
            socket.close();
        } catch (IOException ex) {
            OutputArea.append("Failed to disconnect. \n");
        }
        isConnected = false;
        Textfield2.setEditable(true);

    }

    public client_frame() {
        try {
            this.IPaddress = InetAddress.getLocalHost();
            initComponents();
        } catch (UnknownHostException ex) {

        }
    }

    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    if (data[2].equals(chat)) {
                        OutputArea.append(data[0] + ": " + data[1] + "\n");
                        OutputArea.setCaretPosition(OutputArea.getDocument().getLength());
                    } else if (data[2].equals(connect)) {
                        OutputArea.removeAll();
                        userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        userRemove(data[0]);
                    } else if (data[2].equals(done)) {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        Textfield2 = new javax.swing.JTextField();
        Connectbtn = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        OutputArea = new javax.swing.JTextArea();
        input = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_port.setText("Port :");

        tf_port.setText("8888");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        lb_username.setText("Username :");

        Textfield2.setText("localhost");
        Textfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Textfield2ActionPerformed(evt);
            }
        });

        Connectbtn.setText("Connect");
        Connectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectbtnActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        OutputArea.setColumns(20);
        OutputArea.setRows(5);
        jScrollPane1.setViewportView(OutputArea);

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Textfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Connectbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(b_disconnect)
                        .addGap(101, 101, 101)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_port)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Textfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_username)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_disconnect)
                    .addComponent(Connectbtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed

    }//GEN-LAST:event_tf_portActionPerformed

    private void Textfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Textfield2ActionPerformed

    }//GEN-LAST:event_Textfield2ActionPerformed

    private void ConnectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectbtnActionPerformed
        if (isConnected == false) {
            username = Textfield2.getText();
            Textfield2.setEditable(false);

            try {
                socket = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                //send server which user is connected
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + " is connected");
                writer.flush();
                isConnected = true;
                //getting IP addreses 
                OutputArea.append("Connected to " + socket.getInetAddress() + "\n");
                OutputArea.append("Connected to " + IPaddress + "\n");
                Textfield2.setEditable(true);
                //recieving 
                InputStream input = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));
                String line = br.readLine();
                OutputArea.append("Server said: " + line + "\n");
                OutputArea.append("\n" + "Criteria: Number must be in the Range 1-50 and should not be repeated." + "\n");
                OutputArea.append("Enter Lottery numbers" + "\n");
            } catch (Exception ex) {
            }
            ListenThread();
            
            
        }
    }//GEN-LAST:event_ConnectbtnActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        UserDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed


    }//GEN-LAST:event_b_sendActionPerformed
        
    public static void main(String args[]) {
        
        boolean Connectbtn = false;
        
        while (Connectbtn = true) {
            //reading = Integer.parseInt(input.getText());
            
            int Lotteryno[] = new int[6];
            for (int Counter = 0; Counter != 6; Counter++) {
                //OutputArea.append("Number " + (Counter + 1) + " :");
                //Lotteryno[Counter] = reading;
                //OutputArea.append("\n" + Lotteryno[Counter] + "\n");

            }
        }
    java.awt.EventQueue.invokeLater ( 
        new Runnable() {
            @Override
        public void run
        
                    
            () {
                new client_frame().setVisible(true);
        }
    }

);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Connectbtn;
    private javax.swing.JTextArea OutputArea;
    private javax.swing.JTextField Textfield2;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JTextField input;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextField tf_port;
    // End of variables declaration//GEN-END:variables
}
