package com.mymt.bean;

import com.mymt.data.ImageData;

import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
 * PlayerBean 实体类
 * <p>
 * 玩家类。玩家的各个属性
 *
 * @author ZYY
 */
public class PlayerBean implements Serializable{

    private int level;      // 等级
    private int hp;         // 生命值
    private int attack;     // 攻击力
    private int defend;     // 防御力
    private int money;      // 金钱
    private int exp;        // 经验
    private int Ykey;       // 黄色钥匙数
    private int Bkey;       // 蓝色钥匙数
    private int Rkey;       // 红色钥匙数

    private int toward;     // 当前朝向 0-左 1-下 2-右 3-上
    private int posX;       // X 坐标值
    private int posY;       // Y 坐标值

    // 构造器 游戏开始时主角的初始属性
    public PlayerBean() {

        this.level = 1;     // 初始等级      1
        this.hp = 1000;     // 初始生命值  1000
        this.attack = 10;   // 初始攻击力    10
        this.defend = 10;   // 初始防御力    10
        this.money = 0;     // 初始金钱      0
        this.exp = 0;       // 初始经验值    0
        this.Ykey = 0;      // 初始黄钥匙数   0
        this.Bkey = 0;      // 初始蓝钥匙数   0
        this.Rkey = 0;      // 初始红钥匙数   0

        this.toward = 1;    // 初始朝向
        this.posX = 5;      // 初始 X坐标
        this.posY = 9;      // 初始 Y坐标

//        // 测试用的数据
//        this.level = 15;
//        this.hp = 7691;
//        this.attack = 4365;
//        this.defend = 5008;
//        this.money = 8;
//        this.exp = 52;
//        this.Ykey = 0;
//        this.Bkey = 0;
//        this.Rkey = 0;
//        this.toward = 1;
//        this.posX = 5;
//        this.posY = 10;
    }

    public void move(int cx, int cy) {
        posX = cx;
        posY = cy;
    }

    public BufferedImage draw() {
        if (toward == 0)
            return ImageData.playerMap.get(-1);
        if (toward == 1)
            return ImageData.playerMap.get(-2);
        if (toward == 2)
            return ImageData.playerMap.get(-3);
        if (toward == 3)
            return ImageData.playerMap.get(-4);
        return null;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getYkey() {
        return Ykey;
    }

    public void setYkey(int ykey) {
        Ykey = ykey;
    }

    public int getBkey() {
        return Bkey;
    }

    public void setBkey(int bkey) {
        Bkey = bkey;
    }

    public int getRkey() {
        return Rkey;
    }

    public void setRkey(int rkey) {
        Rkey = rkey;
    }

    public int getToward() {
        return toward;
    }

    public void setToward(int toward) {
        this.toward = toward;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
