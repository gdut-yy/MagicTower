package com.mymt.bean;

import com.mymt.MTGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DialoguesBean 类
 * <p>
 * 对话类。存储游戏内的对话内容。
 *
 * @author ZYY
 */
public class DialoguesBean extends BaseBean {
    public DialoguesBean(int index) {
        id = index;
    }

    public void perform() throws IOException {
        String[] messages;
        BufferedImage[] characters = new BufferedImage[50];
        int[] w = new int[50];
        int[] h = new int[50];
        for (int x = 0; x < 50; x++) {
            w[x] = 530;
            h[x] = 170;
        }
        for (int x = 0; x < characters.length; x++) {
            if (x % 2 == 0)
                characters[x] = ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_front.png"));
            else characters[x] = this.draw();
        }
        switch (id) {
            case 0:
                messages = new String[]{"......",
                        "你醒了!",
                        "......\n你是谁？我在哪里？",
                        "我是这里的仙子，刚才你被这里的小怪打昏了。",
                        "......\n剑，剑，我的剑呢？",
                        "你的剑被他们抢走了，我只来得及将你救出来。",
                        "那，公主呢？我是来救公主的。",
                        "公主还在里面，你这样进去是打不过里面的小怪的。",
                        "那我怎么办，我答应了国王一定要把公主救出来的，那我现在应该怎么办呢？",
                        "放心吧，我把我的力量借给你，你就可以打赢那些小怪了。不过，你的先去帮我去找一样东西，找到了再来这里找我。",
                        "找东西？找什么东西？",
                        "是一个十字架，中间有一颗红色的宝石。",
                        "那个东西有什么用吗？",
                        "我本是这座塔守护者，可不久前，从北方来了一批恶魔，他们占领了这座塔，并将我的魔力封在了这个十字架里面，" +
                                "如果你能将它带出塔来，那我的魔力便会慢慢地恢复，到那时我便可以把力量借给你去救公主了。" +
                                "要记住，只有用我的魔力才可以打开二十一层的门。",
                        "......\n好吧，我试试看。",
                        "刚才我去看过了，你的剑被放在三楼，你的盾在五楼上，而那个十字架被放在七楼。要到七楼，你的先取回你的剑和盾。" +
                                "另外在塔里的其他楼层上，还有一些存放了好几百年的宝剑和宝物，如果得到它们，对于你对付这里面的怪物将有很大的帮助。",
                        ". . .\n可是，我怎么进去呢?",
                        "我这里有三把钥匙，你先拿去，在塔里面还有很多这样的钥匙，你一定要珍惜使用。勇敢的去吧，勇士！"};
                h[9] = 300;
                h[13] = 330;
                h[15] = 330;
                h[17] = 200;
                MTGame.talk(messages, characters, w, h);
                MTGame.baseBeanMap[0][4][8] = new DialoguesBean(1);
                MTGame.baseBeanMap[0][5][8] = null;
                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
//                MTGame.playerBean_1.Ykey++;
//                MTGame.playerBean_1.Bkey++;
//                MTGame.playerBean_1.Rkey++;
                break;
            case 1:
                if (!MTGame.hasCross) {
                    MTGame.inConversation = false;
                    return;
                }
                String[] messages1 = {"You brought the cross back! 你更强的力量！\n咪啦哆咪哔······"};
                BufferedImage[] characters1 = {this.draw()};
                MTGame.talk(messages1, characters1, w, h);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() * 4 / 3);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() * 4 / 3);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() * 4 / 3);
//                MTGame.playerBean_1.hp = MTGame.playerBean_1.hp * 4 / 3;
//                MTGame.playerBean_1.attack = MTGame.playerBean_1.attack * 4 / 3;
//                MTGame.playerBean_1.defend = MTGame.playerBean_1.defend * 4 / 3;
                MTGame.baseBeanMap[0][4][8] = null;
                break;
            case 2:
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 30);
                //MTGame.playerBean_1.attack += 30;
                MTGame.baseBeanMap[2][7][10] = null;
                MTGame.fr.repaint();
                MTGame.inConversation = false;
                break;
            case 3:
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 30);
                //MTGame.playerBean_1.defend += 30;
                MTGame.baseBeanMap[2][9][10] = null;
                MTGame.fr.repaint();
                MTGame.inConversation = false;
                break;
            case 4:
                MTGame.store(0);
                break;
            case 5:
                MTGame.store(0);
                break;
            case 6:
                MTGame.store(0);
                break;
            case 7:
                MTGame.store(1);
                break;
            case 8:
                MTGame.store(2);
                break;
            case 9:
                MTGame.store(3);
                break;
            case 10:
                MTGame.store(3);
                break;
            case 11:
                MTGame.store(3);
                break;
            case 12:
                messages = new String[]{"You are saved! ",
                        "啊，那真是太好了，我又可以在这里面寻宝了。的小偷 不过这次运气可不是太好，刚进来就被抓了。 不，不会有事的，快说吧，叫我做什么",
                        "No matter who you are, you are freed. Now leave the tower. I am going to rescue the princess.",
                        "No, I won't leave because you will need me. As a repay, I will open the gate at LV2 for you. If you can bring me my pickaxe I will be able to open level 18 floor"};
                h[3] = 330;
                MTGame.talk(messages, characters, w, h);
                MTGame.LvMap[2][6][1] = 1;
                MTGame.baseBeanMap[4][5][0] = new DialoguesBean(13);
                break;
            case 13:
                if (!MTGame.hasPickaxe) {
                    MTGame.inConversation = false;
                    return;
                }
                messages = new String[]{"Is this the pickaxe you're talking about?", "You brought me the pickaxe! I will open the pathway for you. Good Bye!"};
                MTGame.talk(messages, characters, w, h);
                MTGame.baseBeanMap[4][5][0] = null;
                MTGame.LvMap[18][8][5] = 1;
                MTGame.LvMap[18][9][5] = 1;
                break;
            case 14:
                MTGame.store(4);
                break;
            case 15:
                MTGame.store(5);
                break;
            case 16:
                messages = new String[]{"If you have 500 EXP, I will give you a legendary sword."};
                characters[0] = this.draw();
                MTGame.talk(messages, characters, w, h);
                if (MTGame.playerBean_1.getExp() >= 500) {
                    MTGame.playerBean_1.setExp(MTGame.playerBean_1.getExp() - 500);
                    MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 110);
//                    MTGame.playerBean_1.exp -= 500;
//                    MTGame.playerBean_1.attack += 110;
                    MTGame.baseBeanMap[15][4][3] = null;
                }
                break;
            case 17:
                messages = new String[]{"If you have 500 GOLD, I will give you a legendary shield."};
                characters[0] = this.draw();
                MTGame.talk(messages, characters, w, h);
                if (MTGame.playerBean_1.getMoney() >= 500) {
                    MTGame.playerBean_1.setMoney(MTGame.playerBean_1.getMoney() - 500);
                    MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 120);
//                    MTGame.playerBean_1.money -= 500;
//                    MTGame.playerBean_1.defend += 120;
                    MTGame.baseBeanMap[15][4][5] = null;
                }
                break;
            case 18:
                messages = new String[]{"Tell me, where is the princess!",
                        "Shut up, mortal. Let's fight!"};
                characters[0] = new MonsterBean(29).draw();
                MTGame.talk(messages, characters, w, h);
                MTGame.baseBeanMap[16][5][4] = null;
                break;
            case 19:
                if (MTGame.talked) {
                    MTGame.inConversation = false;
                    return;
                }
                messages = new String[]{"公主，你得救了！ ",
                        "啊，你是来救我的吗？",
                        "请你快随我出去吧！ ",
                        "No, I don't want to leave yet. The demon lord is still alive. The evilness still remains in the tower will be a great threat to our kingdom. ",
                        "大恶魔？我已经杀死了一个魔王！ ",
                        "大恶魔比你刚才杀死的那个厉害多了 ",
                        "好，那你等着，等我杀了那个恶魔再来这里",
                        "请一定要杀死大魔王!"
                };
                h[3] = 330;
                MTGame.talk(messages, characters, w, h);
                MTGame.LvMap[18][10][10] = 8;
                MTGame.talked = true;
                break;
            case 20:
                messages = new String[]{"啊······\n怎么可能，我怎么可能会被你打败呢！\n不，不要这样······"};
                characters[0] = new MonsterBean(32).draw();
                MTGame.talk(messages, characters, w, h);
                MTGame.inConversation = true;
                JOptionPane.showMessageDialog(MTGame.fr, "恭喜通关！您的通关时间是：" + MTGame.gameMin + " 分, " + (int) MTGame.gameSec + " 秒");
                PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("通关记录.txt", true)));
                pr.println(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()) + "  " + MTGame.gameMin + " 分, " + (int) MTGame.gameSec + " 秒");
                pr.close();
                MTGame.baseBeanMap[21][5][1] = null;
                break;
        }
    }
}
