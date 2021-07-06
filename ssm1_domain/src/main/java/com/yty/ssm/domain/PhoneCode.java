package com.yty.ssm.domain;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {
    public static void main(String[] args) {
        verifycode("13344445555");
    }
    public static String getCode(){
        Random random = new Random();
        String code = "";
        for(int i=0;i<6;i++){
            code = code+random.nextInt(10);
        }
        return code;
    }
    public static void verifycode(String phone){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        String countKey = "Verify"+phone+":count";
        String codeKey = "Verity"+phone+":code";

        String count = jedis.get(countKey);
        if(count==null){
            jedis.setex(countKey,24*60*60,"1");
        }else if(Integer.getInteger(count)<=2){
            jedis.incr(countKey);
        }else{
            System.out.println("今天的发送次数已经超过了3次，不能进行发送！");
            jedis.close();
        }

        String vcode = getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
    }
    public static void getRediscode(String phone,String code){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        String codeKey = "Verify"+phone+":code";
        String jediscode = jedis.get(codeKey);
        if(jediscode.equals(code)){
            System.out.println("验证成功！");
        }else{
            System.out.println("验证失败！");
        }
    }
}
