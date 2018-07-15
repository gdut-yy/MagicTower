package com.mymt.bean;

import java.io.Serializable;

/**
 * MonsterBean 实体类
 * <p>
 * 怪物类。构建怪物实体数据
 *
 * @author ZYY
 */
public class MonsterBean implements Serializable {

    @Deprecated
    private int id;         // Deprecated
    private int hp;         // 生命值
    private int attack;     // 攻击力
    private int defend;     // 防御力
    private int money;      // 金钱
    private int exp;        // 经验
    private String name;    // 怪物名

    public MonsterBean(int id, int hp, int attack, int defend, int money, int exp, String name) {
        this.id = id;
        this.hp = hp;
        this.attack = attack;
        this.defend = defend;
        this.money = money;
        this.exp = exp;
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefend() {
        return defend;
    }

    public int getMoney() {
        return money;
    }

    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }

//    public void setHp(int hp) {
//        this.hp = hp;
//    }
//
//    public void setAttack(int attack) {
//        this.attack = attack;
//    }
//
//    public void setDefend(int defend) {
//        this.defend = defend;
//    }
//
//    public void setMoney(int money) {
//        this.money = money;
//    }
//
//    public void setExp(int exp) {
//        this.exp = exp;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
