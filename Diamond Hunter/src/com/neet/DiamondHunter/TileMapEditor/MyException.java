package com.neet.DiamondHunter.TileMapEditor;

public class MyException extends Exception{

    String message;

    MyException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}