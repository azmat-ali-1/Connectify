package SpringProject.SpringProject.Repository;

import SpringProject.SpringProject.DTO.UserInput;
import SpringProject.SpringProject.Enum.Message;
import SpringProject.SpringProject.Enum.Password;
import SpringProject.SpringProject.Model.User;
import SpringProject.SpringProject.Enum.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {
    public HashMap<String, User> userHashMap = new HashMap<>();
    public HashMap<String, HashMap<String,User>> groupHashMap = new HashMap<>();
    public HashMap<String,String> groupAdmin = new HashMap<>();
    public HashMap<String,HashMap<String,List<Message>>>groupMessage = new HashMap<>();

    //1 set user to user databases ->userHashMap
    public String setUserHashMap(UserInput userInput){

        //set password using password class
        Password password = new Password();
        //check whenever user exist or not
        if (userHashMap.containsKey(userInput.getName())){
            return "User Already exist";
        }
        //sure about user not having null name
       else if(userInput.getName().equals("")){
           return "Enter user name";
       }
       //if password right then add user return message
       else if(password.setPassword(userInput.getPassword())){
           User user  = new User(userInput.getId(),userInput.getName(),userInput.getEmail(),password);
           userHashMap.put(userInput.getName(),user);
           return "User succesfully added";
       }
       //last condition fail means password wrong
       return " Wrong_Password";
    }

    //2 get All user
    public List<String> getAllUsers(){
        return new ArrayList<>(userHashMap.keySet());
    }

    //3 3creation of group
    public String CreateGroup( List<String> name, String indexOfAdmin, String groupName){
      if(name.size()<Integer.parseInt(indexOfAdmin)){
          return "index out of bound";
      }
        for(String i : name){
          if(!userHashMap.containsKey(i)){
              return "user not exist"+i;
          }
        }
        if(!groupHashMap.containsKey(groupName)){
            HashMap<String,User> hashMap = new HashMap<>();
            HashMap<String,List<Message>> hashMap1 = new HashMap<>();
            for (String i : name){
                hashMap.put(i,userHashMap.get(i));
                hashMap1.put(i,new ArrayList<>());
            }
            groupMessage.put(groupName,hashMap1);
            groupHashMap.put(groupName,hashMap);
            groupAdmin.put(groupName,name.get(Integer.parseInt(indexOfAdmin)-1));
            return "group created";
        }
        return "Group Already Exist";

    }
    //4 Add member to group
    public String addMemberInGroup(String group,String adminName,String memberName){
        if(groupHashMap.containsKey(group)){
            if(groupAdmin.containsKey(adminName)){
                if(userHashMap.containsKey(memberName)){
                    HashMap<String, User> map = groupHashMap.get(group);
                    map.put(memberName,userHashMap.get(memberName));
                    groupHashMap.put(group,map);
                    HashMap<String,List<Message>> map1 =groupMessage.get(group);
                    map1.put(group,new ArrayList<>());
                    groupMessage.put(group,map1);
                    return "Added "+memberName+" to"+group+" by permission of "+adminName;
                }
                return "User not present";

            }
            return "Access denied (wrong admin)";

        }
        return "Group not present";
    }

    //5 All group detail in single api
    public  HashMap<String, HashMap<String,User>> getAllGroupDetail(){
        return groupHashMap;
    }

    //6 send message to group
    public String sendMassageInGroup(String userName,String groupName,Message message) throws Exception {
        if(groupHashMap.containsKey(groupName)){
            HashMap<String,User> realGroup = groupHashMap.get(groupName);
            if(realGroup.containsKey(userName)){
              HashMap<String ,List<Message>> map = groupMessage.get(groupName);
              List<Message> messagesList = map.get(userName);
              message.setDate(new Date());
              messagesList.add(message);
              map.put(userName,messagesList);
              groupMessage.put(groupName,map);
              return message.getMessage()+" send successfully";
            }
            throw new Exception("user not in group");
        }
        throw new Exception("Group not exist");
    }
    //7 Connect user to each other and save position
    public String connection(String name,String connectionName){
        if(userHashMap.containsKey(name)&&userHashMap.containsKey(connectionName)){
            User user = userHashMap.get(name);
            if(user.getConnection()==null){
                HashMap<String,User> users = new HashMap<>();
                users.put(connectionName,userHashMap.get(connectionName));
                userHashMap.get(name).setConnection(users);
                return "Connection Stablised b/w "+name+" and "+connectionName;
            }
            if(!user.getConnection().containsKey(connectionName)){
                HashMap<String,User> users = user.getConnection();
                users.put(connectionName,userHashMap.get(connectionName));
                userHashMap.get(name).setConnection(users);
                return "Connection Stablised b/w "+name+" and "+connectionName;
                }

            return "Already connected";
        }
        return "Something went wrong";
    }
    //8 change admin
    public String changeAdmin(String oldAdmin,String newAdmin,String groupName){
        if(groupHashMap!=null&&groupMessage!=null&&groupHashMap.containsKey(groupName)&&groupMessage.containsKey(groupName)){
            if (groupHashMap.get(groupName).containsKey(oldAdmin)){
                if(groupHashMap.get(groupName).containsKey(newAdmin)){
                    if(groupAdmin.containsKey(groupName)){
                        groupAdmin.put(groupName,newAdmin);
                        return "changed";
                    }
                    return "new admin user not exist";
                }
            }
            return "old admin user not exist";
        }
        return "Group not exist";
    }
    //9 seen group massage by group name
    public HashMap<String,List<Message>> groupMessage(String groupName) throws Exception {
        if (groupMessage.containsKey(groupName)){
            return groupMessage.get(groupName);
        }
        throw new Exception("group not present");
    }
    //10 get all group name
    public HashMap<String,User> groupDetailByname(String groupName) throws Exception {
        if(groupHashMap.containsKey(groupName)){
            return groupHashMap.get(groupName);
        }
        throw new Exception("group not present");
    }
    //11 get connection detail of user
    public List<String>getAllConnectionOfUser(String name){
        if(!userHashMap.containsKey(name)){
            return null;
        }
        HashMap<String,User>map = userHashMap.get(name).getConnection();
        if(map!=null){
            return new ArrayList<>(map.keySet());
        }
       return null;
    }
    //12 delete group
    public boolean deleteGroup(String name){
        if(groupMessage.containsKey(name)&&groupHashMap.containsKey(name)){
            groupHashMap.remove(name);
            groupMessage.remove(name);
            return true;
        }
        return false;
    }

    //13 delete connection
    public boolean deleteConnection(String name){
        if(userHashMap.containsKey(name)){
            HashMap<String,User> map=userHashMap.get(name).getConnection();
            map.remove(name);
            userHashMap.get(name).setConnection(map);
            System.out.println(map.size());
            return true;
        }
        return false;
    }
    //14 delete user
    public String deleteUser(String name){
        //check user are exist are not
        if(!userHashMap.containsKey(name)){
            return "user not Present";
        }
        userHashMap.remove(name);
        //check if any user having connection b/w present user disconnect it
        for (String i : userHashMap.keySet()){
            User u = userHashMap.get(i);
            HashMap<String,User> userHashMap1 = u.getConnection();
            if(userHashMap1.containsKey(name)){
                userHashMap1.remove(name);
                u.setConnection(userHashMap1);
            }
        }

        boolean admin = false;
        //In any group user present first check it is not admin
        for (String i : groupAdmin.keySet()){
            if(groupAdmin.get(i).equals(name)){
                groupAdmin.remove(i);
                admin=true;
            }
        }
        //so remove from group
        String groupName = "";
        HashMap<String, User> map=null;
       for(String i: groupHashMap.keySet()){
            if(groupHashMap.get(i).containsKey(name)){
                groupName = i;
                map = groupHashMap.get(i);
                groupHashMap.remove(i);
            }
       }

        //if he was admin so,make random person to admin
        if(admin&&map!=null&&groupName!=null){
            for(String i : map.keySet()){
                groupAdmin.put(groupName,i);
               return "remove completed";
            }
        }
        return "remove completed";
    }

    public String getGroupAdmin(String name){
            return groupAdmin.get(name);
    }

    //change status of connect user
    public String changeStatusOfConnection(String name,String connectionName,String status){
        User userMain = userHashMap.get(name);
        HashMap<String,User> listOfConnection = userMain.getConnection();
        User user = listOfConnection.get(connectionName);
        user.setConnect(Status.valueOf(status));
        listOfConnection.put(name,user);
        userMain.setConnection(listOfConnection);
        userHashMap.put(name,userMain);
        return "change successfully";
    }

    //send message to connection according to there status
    public String sendMessageToConnection(String name,String connection,Message message){
    //if disconnected don't send message
      HashMap<String,User> listOfConnection = userHashMap.get(name).getConnection();
      int count=0;
          for(String i : listOfConnection.keySet()){
              String status = listOfConnection.get(i).getConnect().toString();
             if(status.equalsIgnoreCase(connection)){
                 count++;
                 List<String> list = userHashMap.get(i).getInbox();
                 list.add(name+":- "+message.getMessage()+" "+new Date());
                 userHashMap.get(i).setInbox(list);
             }
          }
       return "send all connection :- "+count;
    }
    public List<String> checkInbox(String name){
        return userHashMap.get(name).getInbox();
    }
}
