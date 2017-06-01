package com.ty.util;

import java.util.Timer;
public class TimerTask {
 
 public static void main(String[] args) {
  Timer timer = new Timer();
  // 在1秒后执行此任务,每次间隔2秒,
  timer.schedule(new MyTask(), 1000, 5000);
  // 这个是用来停止此任务的,否则就一直循环执行此任务了
  while (true) {
   try {
    int ch = System.in.read();
    if (ch - 'c' == 0) {
     timer.cancel();// 使用这个方法退出任务
    }
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
 }
 static class MyTask extends java.util.TimerTask {
  @Override
  public void run() {
   System.out.println("________");
  }
 }
}