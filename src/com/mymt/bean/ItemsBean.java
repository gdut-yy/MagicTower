package com.mymt.bean;


import com.mymt.MTGame;

/**
 * ItemsBean 类
 * <p>
 * 道具类。存储游戏内道具触发的事件
 *
 * @author ZYY
 */
public class ItemsBean extends BaseBean {
    public ItemsBean(int index) {
        id = index;
    }

    public void use() {
        switch (id) {
            case 0:
                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
                //MTGame.playerBean_1.Ykey++;
                MTGame.displayMessage("得到一把 黄钥匙 ！");
                break;
            case 1:
                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
                //MTGame.playerBean_1.Bkey++;
                MTGame.displayMessage("得到一把 蓝钥匙 ！");
                break;
            case 2:
                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
                //MTGame.playerBean_1.Rkey++;
                MTGame.displayMessage("得到一把 红钥匙 ！");
                break;
            case 3:
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() + 200);
                //MTGame.playerBean_1.hp += 200;
                MTGame.displayMessage("得到一个小血瓶 生命加 200 ！");
                break;
            case 4:
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() + 500);
                //MTGame.playerBean_1.hp += 500;
                MTGame.displayMessage("得到一个大血瓶 生命加 500 ！");
                break;
            case 5:
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 3);
                //MTGame.playerBean_1.attack += 3;
                MTGame.displayMessage("得到一个红宝石 攻击力加 3 ！");
                break;
            case 6:
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 3);
                //MTGame.playerBean_1.defend += 3;
                MTGame.displayMessage("得到一个蓝宝石 防御力加 3 ！");
                break;
            case 7:
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 10);
                //MTGame.playerBean_1.attack += 10;
                MTGame.displayMessage("得到 铁剑 攻击加 10 ！");
                break;
            case 8:
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 10);
                //MTGame.playerBean_1.defend += 10;
                MTGame.displayMessage("得到 铁盾 防御加 10 ！");
                break;
            case 9:
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 85);
                //MTGame.playerBean_1.defend += 85;
                MTGame.displayMessage("Obtain Knight Shield! defend+85");
                break;
            case 10:
                MTGame.playerBean_1.setYkey(MTGame.playerBean_1.getYkey() + 1);
                MTGame.playerBean_1.setBkey(MTGame.playerBean_1.getBkey() + 1);
                MTGame.playerBean_1.setRkey(MTGame.playerBean_1.getRkey() + 1);
                //MTGame.playerBean_1.Ykey++;
                //MTGame.playerBean_1.Bkey++;
                //MTGame.playerBean_1.Rkey++;
                MTGame.displayMessage("得到 钥匙盒 各种钥匙数加 1 ！");
                break;
            case 11:
                MTGame.playerBean_1.setLevel(MTGame.playerBean_1.getLevel() + 3);
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() + 3000);
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 21);
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 21);
                //MTGame.playerBean_1.level += 3;
                //MTGame.playerBean_1.hp += 3000;
                //MTGame.playerBean_1.attack += 21;
                //MTGame.playerBean_1.defend += 21;
                MTGame.displayMessage("Recieve The Feather! Level+3");
                break;
            case 12:
                MTGame.playerBean_1.setMoney(MTGame.playerBean_1.getMoney() + 300);
                //MTGame.playerBean_1.money += 300;
                MTGame.displayMessage("得到 金块 金币数加 300 ！");
                break;
            case 13:
                MTGame.hasCross = true;
                MTGame.displayMessage("You find the Cross! Take it to the elf to recieve power. ");
                break;
            case 14:
                MTGame.fly = true;
                MTGame.displayMessage("You find the snowflake! Press J to see detail ");
                break;
            case 15:
                MTGame.hasPickaxe = true;
                MTGame.displayMessage("You find the Pickaxe! Take it to the thief. ");
                break;
            case 16:
                MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() * 2);
                //MTGame.playerBean_1.hp = MTGame.playerBean_1.hp * 2;
                MTGame.displayMessage("You find the elixir! Double the current HP ");
                break;
            case 17:
                MTGame.forecast = true;
                MTGame.displayMessage("获得 圣光徽 按 L 键查看怪物的基本情况");
                break;
            case 18:
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 70);
                //MTGame.playerBean_1.attack += 70;
                MTGame.displayMessage("Obtain Knight Sword! attack+70");
                break;
            case 19:
                MTGame.playerBean_1.setAttack(MTGame.playerBean_1.getAttack() + 110);
                //MTGame.playerBean_1.attack += 110;
                MTGame.displayMessage("Obtain Holy Sword! attack+110");
                break;
            case 20:
                MTGame.playerBean_1.setDefend(MTGame.playerBean_1.getDefend() + 120);
                //MTGame.playerBean_1.defend += 120;
                MTGame.displayMessage("Obtain Holy Shield! defend+120");
                break;
        }
    }
}
