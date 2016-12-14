// This file must be implemented when completing activity 2
//

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

//
// ChatRobot implementation
//
public class ChatRobot implements MessageListener{
    private ChatConfiguration conf;
    private IChatServer srv = null;  
    private IChatUser robotUser = null;
    
    public ChatRobot(ChatConfiguration conf){
        this.conf = conf;
    }
    
    public String [] doConnect (String serverName) throws Exception {

      // Locate server using the name service
      try {
          Registry reg = LocateRegistry.getRegistry (conf.getNameServiceHost(), conf.getNameServicePort());
          srv = (IChatServer) reg.lookup (serverName);
          //System.out.println ("LOG==> ChatServer: " + srv);
      } catch (java.rmi.ConnectException e) {
          throw new Exception ("rmiregistry not found at '" + conf.getNameServiceHost() + ":" + conf.getNameServicePort() + "'");
      } catch (java.rmi.NotBoundException e) {
          throw new Exception ("Server '" + serverName + "' not found.");
      }

      // Once we've got the server, we create a local user object and register it into the server
      robotUser = new ChatUser ("ChatRobot", this);
      boolean done = srv.connectUser (robotUser);
      while(!done){
          int i=0;
          robotUser.setNick("ChatRobot"+i);
          done = srv.connectUser(robotUser);
        }

      // Once we've registered, retrieve the channel list
      IChatChannel [] channels = srv.listChannels ();
      if (channels == null || channels.length == 0){
        throw new Exception ("Server has no channels");
      }

      // Convert channel list to string list, since we don't want the UI to know about invocable
      // objects. It is a plain UI which does not depend on RMI, 
      String list [] = new String [channels.length];      
      for (int i=0; i<channels.length; i++) {
          list[i] = channels[i].getName();
      }

      return list;
    }
   
    public String [] doJoinChannel (String channelName) throws Exception {
      
      IChatChannel ch = srv.getChannel (channelName);
      if (ch == null) {throw new Exception ("Channel not found");}

      ch.join (robotUser); // join 
      IChatUser [] users = ch.listUsers ();
      if (users == null || users.length == 0){
          throw new Exception ("BUG. Tell professor there are no users after joining");
      }
      
      String [] userList = new String [users.length];      
      for (int i=0; i<users.length; i++) {
          userList[i] = users[i].getNick();
      }
      
      return userList;
    }
    
    public void doSendChannelMessage (String dst, String msg) throws Exception{
      try {
          IChatChannel c_dst = srv.getChannel (dst);
          IChatMessage c_msg = new ChatMessage(robotUser, c_dst, msg);
          c_dst.sendMessage (c_msg);
      } catch (Exception e) {
          throw new Exception ("Cannot send message: " + e);
      }
    }
    public void messageArrived (IChatMessage msg) {
      try {
          IChatUser src = msg.getSender();
          Remote dst = msg.getDestination();
          String str = msg.getText();
          String nick = null;
          
          if (str.startsWith (ChatChannel.JOIN)) {
              nick = str.substring (ChatChannel.JOIN.length() + 1);
              doSendChannelMessage(conf.getChannelName(), "Hola, "+nick+"!");
            }

          if (str.startsWith (ChatChannel.LEAVE)) {
              nick = str.substring (ChatChannel.LEAVE.length() + 1);
              doSendChannelMessage(conf.getChannelName(), "Adios "+nick+"!");
            }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    
    public ChatConfiguration getConf(){
        return this.conf;
    }
    public static void main (String args [] ){
      ChatRobot robot = new ChatRobot(ChatConfiguration.parse(args));
      try{
          robot.doConnect(robot.getConf().getServerName());
          robot.doJoinChannel(robot.getConf().getChannelName());
      }catch(Exception e){
          System.out.println("Error ocurred");
      }
    }
}
