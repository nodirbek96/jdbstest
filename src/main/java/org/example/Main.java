package org.example;

import org.example.entity.candidate.CandidateCallbacks;
import org.example.entity.candidate.CandidateRepository;
import org.example.entity.user.UserRepository;
import org.example.entity.user.UserTableCallbacks;

public class Main {
    public static void main(String[] args) {
        UserTableCallbacks userTableCallbacks = new UserRepository();
//        if(userTableCallbacks.createUserTable()){
//            System.out.println("User "+userTableCallbacks.insertUser(new
//                    UserDto("Nodirbek","Hasanboev","user","12364")));
//        }else{
//            System.out.println("Table not exists");
//        }

//        System.out.println(userTableCallbacks.getAllUsers().toString());
//        System.out.println(userTableCallbacks.getUser(4));
//        System.out.println(userTableCallbacks.updateUser(new UserDto(3, "111", "Sodiqov", "akissdasdsdh", "32ss615")).toString());

        CandidateCallbacks candidateCallbacks = new CandidateRepository();
        candidateCallbacks.createCandidateTable();
    }
}