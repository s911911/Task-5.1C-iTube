package com.example.task51citube.util;

import java.security.MessageDigest;

public final class HashUtil {
    private HashUtil(){}
    public static String md5(String s){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] out = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b: out) sb.append(String.format("%02x", b));
            return sb.toString();
        }catch (Exception e){ return ""; }
    }
}
