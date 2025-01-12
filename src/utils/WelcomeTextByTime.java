package utils;

import java.time.LocalTime;

public class WelcomeTextByTime {

    public static String getWelcomeText(){
        LocalTime agora = LocalTime.now();
        String welcomeText = "";
        if(agora.isBefore(LocalTime.of(12, 0))){
            welcomeText ="Bom Dia ";
        }
        else if(agora.isBefore(LocalTime.of(18, 0))){
            welcomeText = "Boa Tarde ";
        }else{
            welcomeText = "Boa Noite ";
        }
    
        return welcomeText;
    }


}
