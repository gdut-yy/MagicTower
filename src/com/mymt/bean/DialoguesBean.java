package com.mymt.bean;

import com.mymt.MTGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mymt.data.MapData.LvMap;

/**
 * DialoguesBean 类
 * <p>
 * 对话类。存储游戏内的对话内容。
 *
 * @author ZYY
 */
public class DialoguesBean extends BaseBean {

//    public DialoguesBean(int index) {
//        this.id = index;
//    }
//
//    public void perform() throws IOException {
//        String[] messages;
//        BufferedImage[] characters = new BufferedImage[50];
//        int[] w = new int[50];
//        int[] h = new int[50];
//        for (int x = 0; x < 50; x++) {
//            w[x] = 530;
//            h[x] = 170;
//        }
//        for (int x = 0; x < characters.length; x++) {
//            if (x % 2 == 0)
//                characters[x] = ImageIO.read(new File(System.getProperty("user.dir") + "/res/player_front.png"));
//            else characters[x] = this.draw();
//        }
//        switch (this.id) {
//            case 24:
//                messages = new String[]{"......",
//                        "你醒了!",
//                        "......\n你是谁？我在哪里？",
//                        "我是这里的仙子，刚才你被这里的小怪打昏了。",
//                        "......\n剑，剑，我的剑呢？",
//                        "你的剑被他们抢走了，我只来得及将你救出来。",
//                        "那，公主呢？我是来救公主的。",
//                        "公主还在里面，你这样进去是打不过里面的小怪的。",
//                        "那我怎么办，我答应了国王一定要把公主救出来的，那我现在应该怎么办呢？",
//                        "放心吧，我把我的力量借给你，你就可以打赢那些小怪了。不过，你的先去帮我去找一样东西，找到了再来这里找我。",
//                        "找东西？找什么东西？",
//                        "是一个十字架，中间有一颗红色的宝石。",
//                        "那个东西有什么用吗？",
//                        "我本是这座塔守护者，可不久前，从北方来了一批恶魔，他们占领了这座塔，并将我的魔力封在了这个十字架里面，" +
//                                "如果你能将它带出塔来，那我的魔力便会慢慢地恢复，到那时我便可以把力量借给你去救公主了。" +
//                                "要记住，只有用我的魔力才可以打开二十一层的门。",
//                        "......\n好吧，我试试看。",
//                        "刚才我去看过了，你的剑被放在三楼，你的盾在五楼上，而那个十字架被放在七楼。要到七楼，你的先取回你的剑和盾。" +
//                                "另外在塔里的其他楼层上，还有一些存放了好几百年的宝剑和宝物，如果得到它们，对于你对付这里面的怪物将有很大的帮助。",
//                        ". . .\n可是，我怎么进去呢?",
//                        "我这里有三把钥匙，你先拿去，在塔里面还有很多这样的钥匙，你一定要珍惜使用。勇敢的去吧，勇士！"};
//                h[9] = 300;
//                h[13] = 330;
//                h[15] = 330;
//                h[17] = 200;
//                MTGame.talk(messages, characters, w, h);
//                MTGame.baseBeanMap[0][4][8] = new DialoguesBean(1);
//                MTGame.baseBeanMap[0][5][8] = null;
//                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
//                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
//                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
//                break;
//            case 1:
//                if (!MTGame.hasCross) {
//                    MTGame.inConversation = false;
//                    return;
//                }
//                String[] messages1 = {"You brought the cross back! 你更强的力量！\n咪啦哆咪哔······"};
//                BufferedImage[] characters1 = {this.draw()};
//                MTGame.talk(messages1, characters1, w, h);
//                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() * 4 / 3);
//                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() * 4 / 3);
//                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() * 4 / 3);
//                MTGame.baseBeanMap[0][4][8] = null;
//                break;
//            case 2:
//                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 30);
//                MTGame.baseBeanMap[2][7][10] = null;
//                MTGame.fr.repaint();
//                MTGame.inConversation = false;
//                break;
//            case 3:
//                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 30);
//                MTGame.baseBeanMap[2][9][10] = null;
//                MTGame.fr.repaint();
//                MTGame.inConversation = false;
//                break;
//            case 4:
//                MTGame.shop(0);
//                break;
//            case 5:
//                MTGame.shop(0);
//                break;
//            case 6:
//                MTGame.shop(0);
//                break;
//            case 7:
//                MTGame.shop(1);
//                break;
//            case 8:
//                MTGame.shop(2);
//                break;
//            case 9:
//                MTGame.shop(3);
//                break;
//            case 10:
//                MTGame.shop(3);
//                break;
//            case 11:
//                MTGame.shop(3);
//                break;
//            case 12:    //  第 4 层 第一次小偷对话
//                messages = new String[]{"你已经得救了! ",
//                        "啊，那真是太好了，我又可以在这里面寻宝了。" +
//                                "哦，还没有自我介绍，我叫杰克，是这附近有名" +
//                                "的小偷，什么金银财宝我样样都偷过。" +
//                                "不过这次运气可不是太好，刚进来就被抓了。" +
//                                "现在你帮我打开了门，那我就帮你做一件事吧。",
//                        "快走吧，外面还有很多怪物，我可能顾不上你。",
//                        "不，不，不会有事的，快说吧，叫我做什么？",
//                        "······\n你会开门吗？",
//                        "那当然。",
//                        "那就请你帮我打开第二层的门吧！",
//                        "那个简单，不过，如果你能帮我找到一把嵌了红" +
//                                "宝石的铁榔头的话，我还帮你打通第十八层的路。",
//                        "嵌了红宝石的铁榔头？好吧，我帮你找找。",
//                        "非常地感谢。一会我便会将第二层的门打开。" +
//                                "如果你找到那个铁榔头的话，还是来这里找我！"
//                };
//                h[3] = 330;
//                MTGame.talk(messages, characters, w, h);
//                LvMap[2][6][1] = 1;
//                MTGame.baseBeanMap[4][5][0] = new DialoguesBean(13);
//                break;
//            case 13:
//                if (!MTGame.hasPickaxe) {
//                    MTGame.inConversation = false;
//                    return;
//                }
//                messages = new String[]{"Is this the pickaxe you're talking about?", "You brought me the pickaxe! I will open the pathway for you. Good Bye!"};
//                MTGame.talk(messages, characters, w, h);
//                MTGame.baseBeanMap[4][5][0] = null;
//                LvMap[18][8][5] = 1;
//                LvMap[18][9][5] = 1;
//                break;
//            case 14:
//                MTGame.shop(4);
//                break;
//            case 15:
//                MTGame.shop(5);
//                break;
//            case 16:
//                messages = new String[]{"If you have 500 EXP, I will give you a legendary sword."};
//                characters[0] = this.draw();
//                MTGame.talk(messages, characters, w, h);
//                if (MTGame.playerBean_1.getExp() >= 500) {
//                    MTGame.playerBean_1.setExp(MTGame.playerBean_1.getExp() - 500);
//                    MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 110);
//                    MTGame.baseBeanMap[15][4][3] = null;
//                }
//                break;
//            case 17:
//                messages = new String[]{"If you have 500 GOLD, I will give you a legendary shield."};
//                characters[0] = this.draw();
//                MTGame.talk(messages, characters, w, h);
//                if (MTGame.playerBean_1.getMoney() >= 500) {
//                    MTGame.playerBean_1.setMoney(MTGame.playerBean_1.getMoney() - 500);
//                    MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 120);
//                    MTGame.baseBeanMap[15][4][5] = null;
//                }
//                break;
//            case 18:
//                messages = new String[]{"Tell me, where is the princess!",
//                        "Shut up, mortal. Let's fight!"};
//                characters[0] = new MonsterBean(29).draw();
//                MTGame.talk(messages, characters, w, h);
//                MTGame.baseBeanMap[16][5][4] = null;
//                break;
//            case 19:
//                if (MTGame.talked) {
//                    MTGame.inConversation = false;
//                    return;
//                }
//                messages = new String[]{
//                        "公主，你得救了！ ",
//                        "啊，你是来救我的吗？",
//                        "是的，我是奉国王的命令来救你的。\n请你快随我出去吧！ ",
//                        "不，我还不想走。",
//                        "为什么？这里到处到时恶魔。",
//                        "正是因为这里面到处都是恶魔，所以才不可以就" +
//                                "这样出去，我要看着那个恶魔被杀死！" +
//                                "英雄的勇士，如果你能够将那个大恶魔杀死。我" +
//                                "就和你一起出去！",
//                        "大恶魔？我已经杀死了一个魔王！ ",
//                        "大恶魔在这座塔的最顶层，你杀死的可能是一个" +
//                                "小队长之类的恶魔",
//                        "好，那你等着，等我杀了那个恶魔再来这里找你！",
//                        "大恶魔比你刚才杀死的那个厉害多了。" +
//                                "而且他还会变身，变身后的魔王他的攻击力和防" +
//                                "御力都会提升至少一半以上，你要小心！" +
//                                "请一定要杀死大魔王！"
//                };
//                h[3] = 330;
//                MTGame.talk(messages, characters, w, h);
//                LvMap[18][10][10] = 8;
//                MTGame.talked = true;
//                break;
//            case 20:    // 第 21 层 冥灵魔王
//                messages = new String[]{
//                        "啊······\n怎么可能，我怎么可能" +
//                                "会被你打败呢！\n" +
//                                "不，不要这样············"};
//                characters[0] = new MonsterBean(32).draw();
//                MTGame.talk(messages, characters, w, h);
//                MTGame.inConversation = true;
//                JOptionPane.showMessageDialog(MTGame.fr, "恭喜通关！您的通关时间是：" + MTGame.gameMin + " 分, " + (int) MTGame.gameSec + " 秒");
//                PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("通关记录.txt", true)));
//                pr.println(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()) + "  " + MTGame.gameMin + " 分, " + (int) MTGame.gameSec + " 秒");
//                pr.close();
//                MTGame.baseBeanMap[21][5][1] = null;
//                break;
//            case 21:    // 第 2 层 神秘老人对话（得到钢剑，攻击+30）
//                messages = new String[]{
//                        "您已经得救了！",
//                        "哦，我的孩子，真是太感谢你了！" +
//                                "这个地方又脏又坏，我真的是快呆不下去了。",
//                        "快走吧，我还得救走被关在这里的公主。",
//                        "哦，你是来救公主的，为了表示对你的感谢，这" +
//                                "个东西就送给你吧，这还是我年青的时候用过的。" +
//                                "拿着它去解救公主吧！"
//
//                };
//                break;
//            case 22:    // 第 2 层 商人对话（得到钢盾，防御+30）
//                messages = new String[]{
//                        "您已经得救了！",
//                        "哦，是嘛！真是太感谢你了！" +
//                                "我是个商人，不知为什么被抓到这里来了。",
//                        "快走吧，现在你已经自由了。",
//                        "哦，对对对，我已经自由了。" +
//                                "那这个东西就给你吧，本来我是准备卖钱的。" +
//                                "相信它对你一定很有帮助！"
//
//                };
//                break;
//            case 23:    // 第 16 层 魔王对话
//                messages = new String[]{
//                        "······",
//                        "停止吧！愚蠢的人类！",
//                        "该停止的是你！魔王。快说，公主关在哪里？",
//                        "等你打赢我再说吧！"
//                };
//                break;
//            case 99:    // 第 15 层 神秘老人对话（得到圣光剑，攻击+120）
//                messages = new String[]{
//                        "你好，勇敢的孩子，你终于来到这里了。" +
//                                "我将给你一个非常好的宝物，它可以使你的攻击" +
//                                "力提升 120 点，单着必须的用你的 500 点经验" +
//                                "来进行交换，考虑一下子吧！",
//                        "好吧，那就将那把剑给我吧！",
//                        "那好吧，这把剑就给你了！"
//                };
//                break;
//            case 25:    // 第 15 层 商人对话（得到圣光盾，防御+120）
//                messages = new String[]{
//                        "啊哈，欢迎你的到来！\n我这里有一件对你来说" +
//                                "非常好的宝物，只要你出得起钱，我就卖给你。",
//                        "什么宝物？要多少钱？",
//                        "是这个游戏里最好的盾牌，防御值可以增加 120 " +
//                                "点，而你只要出 500 个金币就可以买下。" +
//                                "怎么样？你有 500 个金币吗？",
//                        "我有 500 个金币。",
//                        "好，成交！"
//                };
//                break;
//            case 26:    // 第 4 层 第二次小偷对话
//                messages = new String[]{
//                        "哈，快看，我找到了什么！",
//                        "太好了，这个东西果然是在这里。" +
//                                "好吧，我这就去帮你修好第十八层的路面。",
//                        "是这个游戏里最好的盾牌，防御值可以增加 120 " +
//                                "点，而你只要出 500 个金币就可以买下。" +
//                                "怎么样？你有 500 个金币吗？",
//                        "我有 500 个金币。",
//                        "好，成交！"
//                };
//                break;
//            case 27:    // 第 19 层 冥灵魔王
//                messages = new String[]{
//                        "大魔王，你的死期到了！",
//                        "哈哈哈······\n你也真是有意思，别以" +
//                                "为蝶仙那家伙给了你力量你就可以打败我，想打败" +
//                                "我你还早着呢！",
//                        "废话少说，去死吧！"
//
//                };
//
//                String[] s2 = new String[]{
//                        "看不出你还有两下子，有本领的话来 21 楼。" +
//                                "在那里，你就可以见识到我真正的实力了！"
//                };
//                break;
//            case 28:
//                messages = new String[]{
//                        "啊······\n怎么可能，我怎么可能" +
//                                "会被你打败呢！" +
//                                "不，要这样不",
//                        "哈哈哈······\n你也真是有意思，别以" +
//                                "为蝶仙那家伙给了你力量你就可以打败我，想打败" +
//                                "我你还早着呢！",
//                        "废话少说，去死吧！"
//
//                };
//                break;
//        }
//    }
}
