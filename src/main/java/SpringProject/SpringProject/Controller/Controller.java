package SpringProject.SpringProject.Controller;

import SpringProject.SpringProject.DTO.UserInput;
import SpringProject.SpringProject.Enum.Message;
import SpringProject.SpringProject.Model.User;
import SpringProject.SpringProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("Connectify")
public class Controller {
    @Autowired
    UserService service;
    @PostMapping("/addUser")
    public String setUser(@RequestBody(required = true) UserInput userInput){
        return service.setUser(userInput);
    }
    @GetMapping("/getUsers")
    public List<String> getUsers(){
        return service.getUsers();
    }

    @PostMapping("/CreateGroup")
    public String createGroup(@RequestBody(required = true) List<String> users,
                              @RequestParam String index,
                              @RequestParam String groupName){
        return service.CreateGroup(users,index, groupName);
    }

    @GetMapping("/getAllGroupDetail")
    public HashMap<String, HashMap<String, User>> getAllGroupDetail(){
        return service.getAllGroupDetail();
    }

    //6
    @PutMapping("/sendMessageToGroup")
    public String sendMassageInGroup(@RequestParam String userName,@RequestParam String groupName,@RequestBody Message message) throws Exception {
        return service.sendMassageInGroup(userName,groupName,message);
    }
    //7
    @PostMapping("/addConnection")
    public String connection(@RequestParam String name,@RequestParam String connectionName){
        return service.connection(name,connectionName);
    }
    //8
    @PutMapping("/changeAdminGroup/{group}")
    public String changeAdmin(@RequestParam String oldAdmin,@RequestParam String newAdmin,@PathVariable("group") String groupName){
        return service.changeAdmin(oldAdmin,newAdmin,groupName);
    }
    //9
    @GetMapping("/groupMessage/{name}")
    public HashMap<String,List<Message>> groupMessage(@PathVariable("name") String groupName) throws Exception {
        return service.groupMessage(groupName);
    }
    //10
    @GetMapping("/groupDetail/{name}")
    public HashMap<String,User> groupDetailByName(@PathVariable("name") String groupName) throws Exception {
        return service.groupDetailByname(groupName);
    }
    //11
    @GetMapping("/getAllConnectionOfUser/{name}")
    public List<String>getAllConnectionOfUser(@PathVariable("name") String name){
        return service.getAllConnectionOfUser(name);
    }
    //12
    @DeleteMapping("/deleteGroup/{name}")
    public String deleteGroup(@PathVariable("name") String name){
        return service.deleteGroup(name);
    }
    //13
    @DeleteMapping("/deleteConnection")
    public String deleteConnection(@RequestParam String name){
        return service.deleteConnection(name);
    }
    //get group admin by group name
    @GetMapping("/getGroupAdmin/{name}")
    public String getGroupAdmin(@PathVariable("name") String name){
        return service.getGroupAdmin(name);
    }

    @PutMapping("/deleteUser")
    public String deleteUser(String name){
        return service.deleteUser(name);
    }
    @GetMapping("/checkInbox/{name}")
    public List<String> checkInbox(@PathVariable("name") String name){
        return service.checkInbox(name);
    }
    @PutMapping("/sendMessageToConnection")
    public String sendMessageToConnection(@RequestParam String name,@RequestParam String connection,@RequestBody Message message){
        return service.sendMessageToConnection(name,connection,message);
    }
    @PutMapping("/changeStatusOfConnection")
   public String changeStatusOfConnection(@RequestParam String name,@RequestParam String connectionName,@RequestParam String status){
        return service.changeStatusOfConnection(name,connectionName,status);
   }
}
