package com.example.back.exceptions.OP;

public class OPAlreadyFinishedException extends RuntimeException{
    public OPAlreadyFinishedException (Long id){
        super("Op "+ id +" já foi finalizada");
    }

}
