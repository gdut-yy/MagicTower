package com.mymt;

import com.mymt.bean.PlayerBean;
import com.mymt.data.MonsterData;
import com.mymt.util.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.HashMap;

import static com.mymt.data.MapData.LvMap;
import static com.mymt.data.MapData.finPos;
import static com.mymt.data.MapData.initPos;


/**
 * MTGame 类
 * <p>
 * 魔塔游戏主类。
 * 主要是根据 魔塔 v1.12 这个版本 进行仿制。
 * <p>
 * 目前所使用的资源文件（72 pix）仅对屏幕分辨率 1920 * 1080 或以上的进行了适配。后续将适配 1280 * 720（54 pix）
 * <p>
 * GitHub 地址：https://github.com/gdut-yy/MagicTower
 *
 * @author ZYY
 * @since 2018-7-9
 */
public class MTGame extends JPanel {

    // 像素值。需跟资源文件的像素值相匹配。当前版本为 72
    public static final int GAME_PIX = 72;

    public static HashMap<Integer, BufferedImage> imgSource = ImageUtil.imagesMap0;     // 用于帧数切换
    public static PlayerBean playerBean_1 = new PlayerBean();

    public static JFrame fr = new JFrame("魔塔 v1.12 Java仿制版     【 GitHub地址：https://github.com/gdut-yy/MagicTower 】");
    public static JPanel game;

    // 游戏时间 分 秒
    public static JLabel time;
    public static int gameMin = 0;
    public static double gameSec = 0;


    //public static JLayeredPane battleFrame = new JLayeredPane();       // 战斗信息面板


    //    public static BufferedImage mapImgSheet;       // 地图素材获取
//    public static BufferedImage enemyImgSheet;     // 怪物素材获取
//    public static BufferedImage itemImgSheet;      // 道具素材获取
//    public static BufferedImage dialogueImgSheet;  // 对话图片获取
//    public static BufferedImage background;        // 背景图片获取

    public static boolean inConversation = false;
    public static boolean hasCross = false;
    public static boolean hasPickaxe = false;
    public static boolean talked = false;
    public static boolean fly = false;
    public static boolean forecast = true; // false
    public static int currentFloor = 0;     // 当前楼层
    public static int maxFloor = 0;         // 最大楼层

//    public static Object[][] enemyDict = new Object[33][6];
//
//    public static BaseBean[][][] baseBeanMap = new BaseBean[27][11][11];

    // 构造器
    public MTGame() {
        setLayout(null);

        // 初始化 时间事件
        time = new JLabel();
        time.setBounds(80, 800, 250, 100);
        time.setForeground(Color.WHITE);
        time.setFont(new Font("Serif", 0, 25));

        // 初始化 对话事件
        conversation.setLayout(null);
        dialogueBackground.setIcon(new ImageIcon(ImageUtil.blankBgImg));
        conversation.add(dialogueBackground, 1, 0);
        dialogueBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", 0, 30));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setEditable(false);
        text.setFocusable(false);


//        // 初始化 战斗信息面板
//        battleFrame.setLayout(null);
//        battleFrame.setBounds(27, GAME_PIX * 2, 1242, 541);
//        JLabel battleBackground = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg.png"))));
//        battleBackground.setBounds(0, 0, 1242, 541);
//        battleFrame.add(battleBackground, 1, 0);
//        battleFrame.setOpaque(true);
//        battleFrame.setVisible(false);



        // 初始化 地图
//        background = ImageUtil.gameBgImg;
//        mapImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property.png"));
//        enemyImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty.png"));
//        itemImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/itemProperty.png"));
//        dialogueImgSheet = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty.png"));
//
//        // 读取怪物属性数据
//        BufferedReader br = new BufferedReader(new FileReader("data/enemy.dat"));
//        for (int x = 0; x < enemyDict.length; x++) {
//            enemyDict[x][0] = br.readLine();
//            for (int y = 1; y < 6; y++) {
//                enemyDict[x][y] = Integer.parseInt(br.readLine());
//            }
//        }
//        br.close();
//
//        // 读取怪物位置数据
//        BufferedReader br1 = new BufferedReader(new FileReader("data/enemyMap.dat"));
//        String line;
//        while ((line = br1.readLine()) != null) {
//            String[] temp = line.split(" ");
//            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new MonsterBean(Integer.parseInt(temp[3]));
//        }
//        br1.close();
//
//        // 读取道具位置数据
//        BufferedReader br2 = new BufferedReader(new FileReader("data/itemMap.dat"));
//        while ((line = br2.readLine()) != null) {
//            String[] temp = line.split(" ");
//            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new ItemsBean(Integer.parseInt(temp[3]));
//        }
//        br2.close();
//
//        // 读取 NPC 位置数据
//        BufferedReader br3 = new BufferedReader(new FileReader("data/dialogueMap.dat"));
//        while ((line = br3.readLine()) != null) {
//            String[] temp = line.split(" ");
//            MTGame.baseBeanMap[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = new DialoguesBean(Integer.parseInt(temp[3]));
//        }
//        br3.close();

        // 初始化 动画
        new Timer(500, new ActionListener() {
            boolean change = true;
//            BufferedImage one = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property.png"));
//            BufferedImage two = ImageIO.read(new File(System.getProperty("user.dir") + "/res/property2.png"));
//            BufferedImage three = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty.png"));
//            BufferedImage four = ImageIO.read(new File(System.getProperty("user.dir") + "/res/enemyProperty2.png"));
//            BufferedImage five = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty.png"));
//            BufferedImage six = ImageIO.read(new File(System.getProperty("user.dir") + "/res/dialogueProperty2.png"));

            @Override
            public void actionPerformed(ActionEvent e) {
                MTGame.gameSec += 0.5;
                if (gameSec == 60) {
                    gameSec = 0;
                    gameMin++;
                }
                MTGame.time.setText(" 游戏时间：" + gameMin + " 分 " + (int) gameSec + " 秒");
                if (change) {
                    change = false;
                    imgSource = ImageUtil.imagesMap0;
//                    mapImgSheet = one;
//                    enemyImgSheet = three;
//                    dialogueImgSheet = five;
                } else {
                    change = true;
                    imgSource = ImageUtil.imagesMap1;
//                    mapImgSheet = two;
//                    enemyImgSheet = four;
//                    dialogueImgSheet = six;
                }
                repaint();
            }
        }).start();
    }


    // 绘制函数。根据 MapData 绘制 背景、地图、玩家。绘制左侧面板
    @Override
    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
        Graphics g2 = g;
        g2.drawImage(ImageUtil.gameBgImg, 0, 0, null);
//        try {
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
//                BufferedImage a = mapImgSheet.getSubimage(GAME_PIX * LvMap[currentFloor][x][y], 0, GAME_PIX, GAME_PIX);
//                    BufferedImage a = ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/" + LvMap[currentFloor][x][y] + ".png"));
                int id = LvMap[currentFloor][x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a, GAME_PIX * y + GAME_PIX * 6, GAME_PIX * x + GAME_PIX, null);
            }
        }

        g2.drawImage(playerBean_1.draw(), (playerBean_1.getPosX() + 6) * GAME_PIX, (playerBean_1.getPosY() + 1) * GAME_PIX, null);
//            for (int x = 0; x < baseBeanMap[currentFloor].length; x++) {
//                for (int y = 0; y < baseBeanMap[currentFloor].length; y++) {
//                    if (baseBeanMap[currentFloor][x][y] != null)
//                        g2.drawImage(baseBeanMap[currentFloor][x][y].draw(), (x + 6) * GAME_PIX, (y + 1) * GAME_PIX, null);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 绘制左侧面板的玩家数据
        g2.setFont(new Font("Arial", 0, 30));
        g2.setColor(Color.WHITE);
        g2.drawString(playerBean_1.getLevel() + "", 230, 150);
        g2.drawString(playerBean_1.getHp() + "", 220, 215);
        g2.drawString(playerBean_1.getAttack() + "", 220, 270);
        g2.drawString(playerBean_1.getDefend() + "", 220, 325);
        g2.drawString(playerBean_1.getMoney() + "", 220, 385);
        g2.drawString(playerBean_1.getExp() + "", 220, 440);

        g2.drawString(playerBean_1.getYkey() + "", 240, 530);
        g2.drawString(playerBean_1.getBkey() + "", 240, 605);
        g2.drawString(playerBean_1.getRkey() + "", 240, 680);
        g2.drawString(currentFloor + "", 200, 750);
    }


    // 遇到事件交互函数
    public static void interaction(int x, int y) {
        // 如果打不赢怪物
//        if (baseBeanMap[currentFloor][x][y] instanceof MonsterBean
//                && (playerBean_1.forecast((MonsterBean) baseBeanMap[currentFloor][x][y]).equals("???")
//                || Integer.parseInt(playerBean_1.forecast((MonsterBean) baseBeanMap[currentFloor][x][y])) >= playerBean_1.getHp())) {
//            return;
//        } else if (baseBeanMap[currentFloor][x][y] instanceof MonsterBean) {
//            playerBean_1.battle((MonsterBean) baseBeanMap[currentFloor][x][y]);
//            //System.out.println((enemyDict[baseBeanMap[currentFloor][x][y].id][0]).toString());
//            MTGame.baseBeanMap[currentFloor][x][y] = null;
//        } else if (baseBeanMap[currentFloor][x][y] instanceof ItemsBean) {
//            ((ItemsBean) baseBeanMap[currentFloor][x][y]).use();
//            baseBeanMap[currentFloor][x][y] = null;
//        } else if (baseBeanMap[currentFloor][x][y] instanceof DialoguesBean) {
//            int d = ((DialoguesBean) baseBeanMap[currentFloor][x][y]).getId();
//            inConversation = true;
//            try {
//                ((DialoguesBean) baseBeanMap[currentFloor][x][y]).perform();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else
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
            case 22:    // 第三层 商店
                ShopUtil.shop(0);
                break;
            case 24:    // [对话] 第 0 层仙子

            case 29:    // [道具]
            case 30:    // [道具]
            case 31:    // [道具]
            case 32:    // [宝物] 幸运十字架
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 33:    // [宝物] 圣水瓶
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() * 2);
                MsgUtil.displayMessage("【圣水瓶】 它可以将你的体制增加一倍（生命值加倍）。");
                break;
            case 34:    // [宝物]
            case 35:    // [宝物]
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
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
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 10);
                MsgUtil.displayMessage("得到 铁剑 攻击加 10 ！");
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 73:    // [宝物]
            case 75:    // [宝物]
            case 76:    // [宝物]
            case 78:    // [宝物]
            case 80:    // [宝物]
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 115:   // 可通过的护栏
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 119:   // 同 1 ？
            case 129:   // 战斗
            case 201:   // [宝物]
            case 202:   // [宝物]
            case 203:   // [宝物]
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


    public static JLayeredPane conversation = new JLayeredPane();
    public static JLabel dialogueBackground = new JLabel();
    public static JTextArea text = new JTextArea(20, 20);
    public static JLabel imgIco = new JLabel();

    // 对话事件
    public static void talk(String[] messages, BufferedImage[] characters, int[] w, int[] h) {
        Insets insets = conversation.getInsets();

        imgIco.setIcon(new ImageIcon(characters[0]));
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX, GAME_PIX);
        text.setBounds(100 + insets.left, 20 + insets.top, w[0] - 100, h[0]);
        text.setText(messages[0]);
        dialogueBackground.setBounds(0, 0, w[0], h[0]);
        conversation.setBounds(675, 560, w[0], h[0]);
        conversation.add(imgIco, 2, 0);
        conversation.add(text, 3, 0);
        game.add(conversation);
        game.repaint();

        fr.addKeyListener(new KeyListener() {
            int count = 0;
            int x = 0, y = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == e.VK_SPACE) {
                    conversation.remove(imgIco);
                    conversation.remove(text);
                    game.remove(conversation);
                    count++;
                    if (count >= messages.length) {
                        inConversation = false;
                        fr.removeKeyListener(this);
                        return;
                    }
                    if (count % 2 == 1) {
                        x = 400;
                        y = 310;
                    } else {
                        x = 675;
                        y = 560;
                    }
                    imgIco.setIcon(new ImageIcon(characters[count]));
                    imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX, GAME_PIX);
                    text.setBounds(100 + insets.left, 20 + insets.top, w[count] - 100, h[count]);
                    text.setText(messages[count]);
                    dialogueBackground.setBounds(0, 0, w[count], h[count]);
                    conversation.setBounds(x, y, w[count], h[count]);
                    conversation.add(imgIco, 2, 0);
                    conversation.add(text, 3, 0);
                    game.add(conversation);
                    game.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }


        });
    }
}