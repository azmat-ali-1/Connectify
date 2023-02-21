package SpringProject.SpringProject.Service;

import SpringProject.SpringProject.DTO.UserInput;
import SpringProject.SpringProject.Enum.Message;
import SpringProject.SpringProject.Model.User;
import SpringProject.SpringProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    //1
    public String setUser(UserInput userInput){
        return repository.setUserHashMap(userInput);
    }
    //2
    public List<String> getUsers(){
        List<String> list = repository.getAllUsers();
        Collections.sort(list);
        return list;
    }
    //3
    public String CreateGroup(List<String> users,String index,String groupName){
        return repository.CreateGroup(users,index,groupName);
    }
    //4
    public String addMemberInGroup(String group,String adminName,String memberName){
        return repository.addMemberInGroup(group,adminName,memberName);
    }
    //5
    public HashMap<String, HashMap<String, User>> getAllGroupDetail(){
        return repository.getAllGroupDetail();
    }
    //6
    public String sendMassageInGroup(String userName, String groupName, Message message) throws Exception {
            return repository.sendMassageInGroup(userName,groupName,message);
    }
    //7
    public String connection(String name,String connectionName){
            return repository.connection(name,connectionName);
    }
    //8
    public String changeAdmin(String oldAdmin,String newAdmin,String groupName){
        return repository.changeAdmin(oldAdmin,newAdmin,groupName);
    }
    //9
    public HashMap<String,List<Message>> groupMessage(String groupName) throws Exception {
    return repository.groupMessage(groupName);
    }
    //10
    public HashMap<String,User> groupDetailByname(String groupName) throws Exception {
    return repository.groupDetailByname(groupName);
    }
    //11
    public List<String>getAllConnectionOfUser(String name){
        return repository.getAllConnectionOfUser(name);
    }
    //12
    public String deleteGroup(String name){
        if(repository.deleteGroup(name)){
            return "Done";
        }
        return "error";
    }
    //13
    public String deleteConnection(String name){
        if(repository.deleteConnection(name)){
            return "Done";
        }
        return "error";
    }
    //14
    public String getGroupAdmin(String name){
        return repository.getGroupAdmin(name);
    }
    public String deleteUser(String name){
        return repository.deleteUser(name);
    }
    public List<String> checkInbox(String name){
            return repository.checkInbox(name);
    }
    public String sendMessageToConnection(String name,String connection,Message message){
        return repository.sendMessageToConnection(name,connection,message);
    }
    public String changeStatusOfConnection(String name,String connectionName,String status){
        return repository.changeStatusOfConnection(name,connectionName,status);
    }
}
