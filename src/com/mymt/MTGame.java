package com.mymt;

import com.mymt.bean.DialoguesBean;
import com.mymt.bean.ItemsBean;
import com.mymt.bean.PlayerBean;
import com.mymt.data.ImageData;
import com.mymt.data.MonsterData;
import com.mymt.util.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.HashMap;

import static com.mymt.data.MapData.*;

/**
 * MTGame 类
 * <p>
 * 魔塔游戏核心交互类。
 * 主要是根据 魔塔 v1.12 这个版本 进行仿制。
 * <p>
 * 目前所使用的资源文件（72 pix）仅对屏幕分辨率 1920 * 1080 或以上的进行了适配。后续将适配 1280 * 720（54 pix）
 * <p>
 * GitHub 地址：https://github.com/gdut-yy/MagicTower
 * <p>
 * 绘制主游戏面板，交互函数，响应键盘输入。
 *
 * @author ZYY
 * @since 2018-7-9
 */
public class MTGame extends JPanel {

    // 像素值。需跟资源文件的像素值相匹配。当前版本为 72
    public static final int GAME_PIX_54 = 54;
    public static final int GAME_PIX_72 = 72;

    public static HashMap<Integer, BufferedImage> imgSource = ImageData.imagesMap0;     // 用于帧数切换
    public static PlayerBean playerBean_1 = new PlayerBean();                           // 用于保存玩家属性数值

    public static JFrame gameFrame;
    public static JPanel gamePanel;

    // 游戏时间 分 秒
    public static JLabel timeLabel;
    public static int gameMin = 0;
    public static double gameSec = 0;

    // 全局变量
    public static boolean inConversation = false;   // 允许键盘交互
    public static boolean talked = false;
    public static int currentFloor = 0;     // 当前楼层
    public static int maxFloor = 0;         // 最大楼层

    // 构造器
    public MTGame() {
        setLayout(null);
        gameFrame = new JFrame("魔塔 v1.12 Java仿制版     【 GitHub地址：https://github.com/gdut-yy/MagicTower 】");

        // 初始化 时间面板
        timeLabel = new JLabel();
        timeLabel.setBounds(80, 800, 250, 100);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Serif", 0, 25));

        // 初始化 图片帧数切换
        new Timer(500, new ActionListener() {
            boolean change = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                MTGame.gameSec += 0.5;  // 频率为 0.5s
                if (gameSec == 60) {
                    gameSec = 0;
                    gameMin++;
                }
                MTGame.timeLabel.setText(" 游戏时间：" + gameMin + " 分 " + (int) gameSec + " 秒");
                if (change) {
                    change = false;
                    imgSource = ImageData.imagesMap0;
                } else {
                    change = true;
                    imgSource = ImageData.imagesMap1;
                }
                repaint();
            }
        }).start();
    }

    // 重写 paintComponent() 方法。
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 绘制游戏主背景
        g2.drawImage(ImageData.gameBgImg, 0, 0, null);

        // 绘制 地图数据
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                int id = LvMap[currentFloor][x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a, GAME_PIX_72 * y + GAME_PIX_72 * 6, GAME_PIX_72 * x + GAME_PIX_72, null);
            }
        }

        // 绘制 玩家
        g2.drawImage(playerBean_1.draw(), (playerBean_1.getPosX() + 6) * GAME_PIX_72, (playerBean_1.getPosY() + 1) * GAME_PIX_72, null);

        // 绘制 左侧面板的玩家数据
        g2.setFont(new Font("Arial", 0, 30));
        g2.setColor(Color.WHITE);
        g2.drawString(playerBean_1.getLevel() + "", 230, 150);
        g2.drawString(playerBean_1.getHp() + "", 220, 215);
        g2.drawString(playerBean_1.getAttack() + "", 220, 270);
        g2.drawString(playerBean_1.getDefend() + "", 220, 325);
        g2.drawString(playerBean_1.getMoney() + "", 220, 385);
        g2.drawString(playerBean_1.getExp() + "", 220, 440);

        // 绘制 各种钥匙数目
        g2.drawString(playerBean_1.getYkey() + "", 240, 530);
        g2.drawString(playerBean_1.getBkey() + "", 240, 605);
        g2.drawString(playerBean_1.getRkey() + "", 240, 680);

        // 绘制 当前楼层
        g2.drawString(currentFloor + "", 200, 750);
    }


    // 核 心 交 互 函 数
    public static void interaction(int x, int y) {
        int id = LvMap[currentFloor][y][x];
        switch (id) {
            case 0:     // 玩家移动
                playerBean_1.move(x, y);
                break;
            case 1:     // 砖墙
                break;
            case 2:     // 黄门
                if (playerBean_1.getYkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setYkey(playerBean_1.getYkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 3:     // 蓝门
                if (playerBean_1.getBkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setBkey(playerBean_1.getBkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 4:     // 红门
                if (playerBean_1.getRkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setRkey(playerBean_1.getRkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 5:     // 石块
                break;
            case 6:     // [道具] 黄钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
                MsgUtil.displayMessage("得到一把 黄钥匙 ！");
                break;
            case 7:     // [道具] 蓝钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
                MsgUtil.displayMessage("得到一把 蓝钥匙 ！");
                break;
            case 8:     // [道具] 红钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
                MsgUtil.displayMessage("得到一把 红钥匙 ！");
                break;
            case 9:     // [道具] 蓝宝石
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 3);
                MsgUtil.displayMessage("得到一个蓝宝石 防御力加 3 ！");
                break;
            case 10:    // [道具] 红宝石
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 3);
                MsgUtil.displayMessage("得到一个红宝石 攻击力加 3 ！");
                break;
            case 11:    // [道具] 红药水
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() + 200);
                MsgUtil.displayMessage("得到一个小血瓶 生命加 200 ！");
                break;
            case 12:    // [道具] 蓝药水
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() + 500);
                MsgUtil.displayMessage("得到一个大血瓶 生命加 500 ！");
                break;
            case 13:    // 上楼
                currentFloor++;
                maxFloor = Math.max(maxFloor, currentFloor);
                playerBean_1.move(initPos[currentFloor][0], initPos[currentFloor][1]);
                break;
            case 14:    // 下楼
                currentFloor--;
                playerBean_1.move(finPos[currentFloor][0], finPos[currentFloor][1]);
                break;
            case 15:    // 不可通过的护栏
                break;
            case 19:    // 火海
                break;
            case 20:    // 星空
                break;
            case 22:    // 商店
                if (currentFloor == 3) {
                    ShopUtil.shop(0);
                } else if (currentFloor == 11) {
                    ShopUtil.shop(3);
                }
                break;
            case 24:    // [对话] 仙子
                new DialoguesBean(id);
                break;
            case 25:    // [对话] 小偷
            case 26:    // [对话] 老人
            case 27:    // [对话] 商人
            case 28:    // [对话] 公主
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 30:    // [道具] 小飞羽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setLevel(MTGame.playerBean_1.getLevel() + 1);
                playerBean_1.setHp(MTGame.playerBean_1.getHp() + 1000);
                playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 7);
                playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 7);
                MsgUtil.displayMessage("得到 小飞羽 等级提升一级 ！");
                break;
            case 31:    // [道具] 大飞羽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                playerBean_1.setLevel(MTGame.playerBean_1.getLevel() + 3);
                playerBean_1.setHp(MTGame.playerBean_1.getHp() + 3000);
                playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 21);
                playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 21);
                MsgUtil.displayMessage("得到 大飞羽 等级提升三级 ！");
                break;
            case 32:    // [宝物] 幸运十字架
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                ItemsBean.isHasCross = true;
                MsgUtil.displayMessage("【幸运十字架】 把它交给序章中的仙子，可以将自身的所有能力提升一些（攻击、防御和生命值）。");
                break;
            case 33:    // [宝物] 圣水瓶
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() * 2);
                MsgUtil.displayMessage("【圣水瓶】 它可以将你的体质增加一倍（生命值加倍）。");
                break;
            case 34:    // [宝物] 圣光徽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                ItemsBean.isHasForecast = true;
                MsgUtil.displayMessage("【圣光徽】 按 L 键使用 查看怪物的基本情况。");
                break;
            case 35:    // [宝物] 风之罗盘
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                ItemsBean.isHasJump = true;
                MsgUtil.displayMessage("【风之罗盘】 按 J 键使用 在已经走过的楼层间进行跳跃。");
                break;
            case 36:    // [道具] 钥匙盒
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
                MsgUtil.displayMessage("得到 钥匙盒 各种钥匙数加 1 ！");
                break;
            case 38:    // [宝物] 星光神榔
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                ItemsBean.isHasHammer = true;
                MsgUtil.displayMessage("【星光神榔】 把它交给第四层的小偷，小偷便会用它打开第十八层的隐藏地面（你就可以救出公主了）。");
                break;
            case 39:    // [道具] 金块
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setMoney(MTGame.playerBean_1.getMoney() + 300);
                MsgUtil.displayMessage("得到 金块 金币数加 300 ！");
                break;
            case 40:    // [怪物 monster]
            case 41:    // [怪物 monster]
            case 42:    // [怪物 monster]
            case 43:    // [怪物 monster]
            case 44:    // [怪物 monster]
            case 45:    // [怪物 monster]
            case 46:    // [怪物 monster]
            case 47:    // [怪物 monster]
            case 48:    // [怪物 monster]
            case 49:    // [怪物 monster]
            case 50:    // [怪物 monster]
            case 51:    // [怪物 monster]
            case 52:    // [怪物 monster]
            case 53:    // [怪物 monster]
            case 54:    // [怪物 monster]
            case 55:    // [怪物 monster]
            case 56:    // [怪物 monster]
            case 57:    // [怪物 monster]
            case 58:    // [怪物 monster]
            case 59:    // [怪物 monster]
            case 60:    // [怪物 monster]
            case 61:    // [怪物 monster]
            case 62:    // [怪物 monster]
            case 63:    // [怪物 monster]
            case 64:    // [怪物 monster]
            case 65:    // [怪物 monster]
            case 66:    // [怪物 monster]
            case 67:    // [怪物 monster]
            case 68:    // [怪物 monster]
            case 69:    // [怪物 monster]
            case 70:    // [怪物 monster]
                if (ForecastUtil.forecast(MonsterData.monsterMap.get(id)).equals("???")
                        || Integer.parseInt(ForecastUtil.forecast(MonsterData.monsterMap.get(id))) >= playerBean_1.getHp()) {
                    return;
                } else {
                    new BattleUtil(id, x, y);
                }
                break;
            case 71:    // [宝物] 铁剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 10);
                MsgUtil.displayMessage("得到 铁剑 攻击加 10 ！");
                break;
            case 73:    // [宝物] 钢剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 30);
                MsgUtil.displayMessage("得到 钢剑 攻击加 30 ！");
                break;
            case 75:    // [宝物] 圣光剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 120);
                MsgUtil.displayMessage("得到 圣光剑 攻击加 120 ！");
                break;
            case 76:    // [宝物]
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 10);
                MsgUtil.displayMessage("得到 铁盾 防御加 10 ！");
                break;
            case 78:    // [宝物] 钢盾
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 30);
                MsgUtil.displayMessage("得到 钢盾 防御加 30 ！");
                break;
            case 80:    // [宝物] 星光盾
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 120);
                MsgUtil.displayMessage("得到 星光盾 防御加 120 ！");
                break;
            case 115:   // 可通过的护栏
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 119:   // 同 case 1:
            case 129:   // 同 case 1:
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 188:
                if (ForecastUtil.forecast(MonsterData.monsterMap.get(id)).equals("???")
                        || Integer.parseInt(ForecastUtil.forecast(MonsterData.monsterMap.get(id))) >= playerBean_1.getHp()) {
                    return;
                } else {
                    new BattleUtil(id, x, y);
                }
                break;
            case 202:   // [宝物] 炎之灵杖
                MsgUtil.displayMessage("得到 炎之灵杖");
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 203:   // [宝物] 心之灵杖
                MsgUtil.displayMessage("得到 心之灵杖");
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 301:   // 22层 到 24层
                currentFloor += 2;
                playerBean_1.move(5, 1);
                break;
            case 302:   // 22层 到 25层
                currentFloor += 3;
                playerBean_1.move(1, 5);
                break;
            case 303:   // 24层 到 22层
                currentFloor -= 2;
                playerBean_1.move(5, 9);
                break;
            case 304:   // 25层 到 22层
                currentFloor -= 3;
                playerBean_1.move(9, 5);
                break;
            case 305:   // 24层 到 26层
                currentFloor += 2;
                playerBean_1.move(5, 10);
                break;
        }
    }
}