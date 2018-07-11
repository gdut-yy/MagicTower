package com.mymt;

import com.mymt.bean.MonsterBean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * PlayerBean 实体类
 * <p>
 * 玩家类。玩家的各个属性
 *
 * @author ZYY
 */
public class PlayerBean {

    // 战斗页面文字的预设格式 Font
    private final static Font BATTLE_FONT = new Font("Serif", 0, 35);

    private int level;      // 等级
    private int hp;         // 生命值
    private int attack;     // 攻击力
    private int defend;     // 防御力
    private int money;      // 金钱
    private int exp;        // 经验
    private int Ykey;       // 黄色钥匙数
    private int Bkey;       // 蓝色钥匙数
    private int Rkey;       // 红色钥匙数

    private int fl;         // 当前所在楼层
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

        this.fl = 0;        // 初始楼层
        this.toward = 1;    // 初始朝向
        this.posX = 5;      // 初始 X坐标
        this.posY = 9;      // 初始 Y坐标

//        // 测试用的数据
//        this.level = 1;
//        this.hp = 1000;
//        this.attack = 10000;
//        this.defend = 10000;
//        this.money = 10000;
//        this.exp = 10000;
//        this.Ykey = 50;
//        this.Bkey = 50;
//        this.Rkey = 50;
//        this.fl = 0;
//        this.toward = 1;
//        this.posX = 5;
//        this.posY = 9;
    }


    // 以下代码按照设计模式 按理说不该出现在实体类中，暂时先放着

    // 填充战斗信息面板中的信息
    JLabel monster_img = new JLabel();      // 怪物图片
    JLabel monster_hp = new JLabel();       // 怪物生命值
    JLabel monster_attack = new JLabel();   // 怪物攻击力
    JLabel monster_defend = new JLabel();   // 怪物防御力

    JLabel player_hp = new JLabel();        // 玩家生命值
    JLabel player_attack = new JLabel();    // 玩家攻击力
    JLabel player_defend = new JLabel();    // 玩家防御力

    public void battle(MonsterBean e) {
        try {
            monster_img = new JLabel(new ImageIcon(e.draw()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //
        int tmp = -50;
        monster_hp.setBounds(400, 37+tmp, 300, 300);
        monster_hp.setFont(BATTLE_FONT);
        monster_hp.setForeground(Color.WHITE);

        monster_attack.setBounds(400, 157+tmp, 300, 300);
        monster_attack.setFont(BATTLE_FONT);
        monster_attack.setForeground(Color.WHITE);

        monster_defend.setBounds(400, 291+tmp, 300, 300);
        monster_defend.setFont(BATTLE_FONT);
        monster_defend.setForeground(Color.WHITE);

        player_hp.setBounds(785, 37+tmp, 300, 300);
        player_hp.setFont(BATTLE_FONT);
        player_hp.setForeground(Color.WHITE);

        player_attack.setBounds(785, 157+tmp, 300, 300);
        player_attack.setFont(BATTLE_FONT);
        player_attack.setForeground(Color.WHITE);

        player_defend.setBounds(785, 291+tmp, 300, 300);
        player_defend.setFont(BATTLE_FONT);
        player_defend.setForeground(Color.WHITE);

        MTGame.battleFrame.add(monster_hp, 2, 0);
        MTGame.battleFrame.add(monster_attack, 3, 0);
        MTGame.battleFrame.add(monster_defend, 4, 0);
        MTGame.battleFrame.add(player_hp, 5, 0);
        MTGame.battleFrame.add(player_attack, 6, 0);
        MTGame.battleFrame.add(player_defend, 7, 0);

        monster_img.setBounds(100, 120, 72, 72);
        MTGame.battleFrame.add(monster_img, 8, 0);
        monster_hp.setText(e.getHp() + "");
        monster_attack.setText(e.getAttack() + "");
        monster_defend.setText(e.getDefend() + "");
        player_hp.setText(hp + "");
        player_attack.setText(attack + "");
        player_defend.setText(defend + "");
        MTGame.battleFrame.setVisible(true);
        MTGame.inConversation = true;
        Timer battleFrame = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ex) {
                attack(e);
                monster_hp.setText(e.getHp() + "");
                player_hp.setText(hp + "");
                MTGame.fr.repaint();
                if (hp <= 0 || e.getHp() <= 0) {
                    MTGame.battleFrame.setVisible(false);
                    MTGame.inConversation = false;
                    exp += e.getExp();
                    money += e.getMoney();
                    MTGame.displayMessage("获得金币数 " + e.getExp() + " 经验值 " + e.getMoney() + " ！");
                    MTGame.battleFrame.remove(monster_img);
                    ((Timer) ex.getSource()).stop();
                }
            }
        });
        battleFrame.start();
    }

    public void move(int cx, int cy) {
        posX = cx;
        posY = cy;
    }

    public String forecast(MonsterBean e) {
        if (attack <= e.getDefend())
            return "???";
        else if (defend >= e.getAttack())
            return 0 + "";
        else {
            //int one = (e.hp/(attack-e.defend))-1; //how many times MonsterBean will strike before it dies
            return ((e.getHp() / (attack - e.getDefend())) * (e.getAttack() - defend)) + "";
        }
    }

    public void attack(MonsterBean e) {
        if (attack > e.getDefend())
            e.setHp(e.getHp() - attack + e.getDefend());
        if (e.getHp() <= 0) return;
        if (e.getAttack() > defend)
            hp = hp - e.getAttack() + defend;
        if (attack < e.getDefend() && e.getAttack() < defend) return;
    }

    public BufferedImage draw() throws IOException {
        if (toward == 0)
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_left.png"));
        if (toward == 1)
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_front.png"));
        if (toward == 2)
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_right.png"));
        if (toward == 3)
            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_back.png"));
        return null;
    }

    public BufferedImage drawFront() throws IOException {
        return ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_front.png"));
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

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
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
