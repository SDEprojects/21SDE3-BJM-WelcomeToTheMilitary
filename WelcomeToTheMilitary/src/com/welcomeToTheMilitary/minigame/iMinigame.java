package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;

public interface iMinigame {
    boolean play();
    boolean play(ServiceMember usr, FinalBoss boss);
}
