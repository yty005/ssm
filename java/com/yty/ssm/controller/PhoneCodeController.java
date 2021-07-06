package com.yty.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import java.util.Random;
@Controller
@RequestMapping("/phone")
public class PhoneCodeController {

//    public static void main(String[] args) {
//        //模拟验证码发送
//        verifyCode("13678765435");
//
//        //模拟验证码校验
//        //getRedisCode("13678765435","4444");
//    }

    //3 验证码校验
//    @RequestMapping("/check.do")
//    public ModelAndView getRedisCode(@RequestParam("phone") String phone, @RequestParam("code") String code,@RequestParam("username")String username,@RequestParam("password") String password) {
//        //从redis获取验证码
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("username",username);
//        mv.addObject("password",password);
//        //验证码key
//        String codeKey = "VerifyCode"+phone+":code";
//        String redisCode = jedis.get(codeKey);
//        //判断
//        if(redisCode.equals(code)) {
//            System.out.println("成功");
//        }else {
//            System.out.println("失败");
////            mv.setViewName("failer.jsp");
////            return mv;
//        }
//        jedis.close();
//        mv.setViewName("redirect:login.do");
//        return mv;
//    }

    //2 每个手机每天只能发送三次，验证码放到redis中，设置过期时间120

    @RequestMapping("/code.do")
    public @ResponseBody String verifyCode(String phone) {
        //连接redis
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //拼接key
        //手机发送次数key
        String countKey = "VerifyCode"+phone+":count";
        //验证码key
        String codeKey = "VerifyCode"+phone+":code";

        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if(count == null) {
            //没有发送次数，第一次发送
            //设置发送次数是1
            jedis.setex(countKey,24*60*60,"1");
        } else if(Integer.parseInt(count)<=2) {
            //发送次数+1
            jedis.incr(countKey);
        } else if(Integer.parseInt(count)>2) {
            //发送三次，不能再发送
            System.out.println("今天发送次数已经超过三次");
            jedis.close();
        }
        //发送验证码放到redis里面
        String vcode = getCode();
        jedis.setex(codeKey,120,vcode);
        jedis.close();
        return "success";
    }

    //1 生成6位数字验证码
    public String getCode() {
        Random random = new Random();
        String code = "";
        for(int i=0;i<6;i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
