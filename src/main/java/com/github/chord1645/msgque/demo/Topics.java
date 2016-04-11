package com.github.chord1645.msgque.demo;

import java.awt.*;
import java.io.Serializable;

public class Topics implements Serializable {
    //server
    public  final static String  S_JOIN="JOIN";
    public  final static String  S_QUIT="QUIT";
    public  final static String  S_READY="READY";
    public  final static String  S_ANSWER="ANSWER";
    //client
    public  final static String  C_START="START";
    public  final static String  C_DRAWER="DRAWER";
    public  final static String  C_END="END";
}
