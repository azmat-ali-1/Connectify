package SpringProject.SpringProject.Enum;

import org.jetbrains.annotations.NotNull;

public class Password {
    private String password;

    public Password() {

    }

    public String getPassword() {
        return password;
    }


    public boolean setPassword(String password) {
        boolean digit=false,Uppercase=false,LowerCase=false,specialC=false;
        for(int i=0;i<password.length();i++){
            if(Character.isDigit(password.charAt(i))){
                digit=true;
            }
            else if(Character.isUpperCase(password.charAt(i))){
                Uppercase=true;
            } else if (Character.isLowerCase(password.charAt(i))) {
                LowerCase=true;
            }
            else {
                specialC=true;
            }
        }
        if(password.length()>7&&digit&&Uppercase&&LowerCase){
            this.password=password;
            return true;
        }
        return false;
    }
    public boolean changePassword(String password) {
       if(password.equals(getPassword())){
           return setPassword(password);
       }
           return false;

    }
}
