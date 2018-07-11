package com.mymt.bean;

import com.mymt.MTGame;

import java.io.Serializable;

/**
 * MonsterBean 实体类
 * <p>
 * 怪物类。构建怪物实体数据
 *
 * @author ZYY
 */
public class MonsterBean extends BaseBean implements Serializable {

    private int hp;     // 生命值
    private int attack; // 攻击力
    private int defend; // 防御力
    private int money;  // 金钱
    private int exp;    // 经验

    // 构造器 根据传入的 id 生成 具体怪物
    public MonsterBean(int name) {
        this.id = name;
        this.hp = (int) MTGame.enemyDict[name][1];
        this.attack = (int) MTGame.enemyDict[name][2];
        this.defend = (int) MTGame.enemyDict[name][3];
        this.money = (int) MTGame.enemyDict[name][4];
        this.exp = (int) MTGame.enemyDict[name][5];
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
