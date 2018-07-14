package com.mymt.util;

import com.mymt.MTGame;
import com.mymt.bean.MonsterBean;
import com.mymt.data.MonsterData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.mymt.MTGame.GAME_PIX;
import static com.mymt.MTGame.currentFloor;
import static com.mymt.data.MapData.LvMap;

/**
 * BattleUtil 工具类
 * <p>
 * 绘制战斗过程页面。（ 在玩家能击败怪物的情况下 ）
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class BattleUtil {



    // 战斗页面文字的预设格式 Font
    private final static Font BATTLE_FONT = new Font("Serif", 0, 35);

    public static JLayeredPane battleFrame = new JLayeredPane();       // 战斗信息面板

    private JLabel battleBackground = new JLabel();
    private JLabel monster_img = new JLabel();      // 怪物图片
    private JLabel monster_hp = new JLabel();       // 怪物生命值
    private JLabel monster_attack = new JLabel();   // 怪物攻击力
    private JLabel monster_defend = new JLabel();   // 怪物防御力
    private JLabel player_hp = new JLabel();        // 玩家生命值
    private JLabel player_attack = new JLabel();    // 玩家攻击力
    private JLabel player_defend = new JLabel();    // 玩家防御力
    private MonsterBean monsterBean;
    private int hp;
    private int attack;
    private int defend;

    public BattleUtil(int id, int x, int y) {

        monsterBean = MonsterData.monsterMap.get(id);
        hp = monsterBean.getHp();
        attack = monsterBean.getAttack();
        defend = monsterBean.getDefend();


        battleBackground = new JLabel(new ImageIcon(ImageUtil.battleBgImg));
        monster_img = new JLabel(new ImageIcon(MTGame.imgSource.get(id)));

        // 初始化 战斗信息面板
        battleFrame.setLayout(null);
        battleFrame.setBounds(27, GAME_PIX * 2, 1242, 541);
        battleBackground.setBounds(0, 0, 1242, 541);
        battleFrame.add(battleBackground, 1, 0);
        battleFrame.setOpaque(true);
        battleFrame.setVisible(false);

        //
        int tmp = -50;
        monster_hp.setBounds(400, 37 + tmp, 300, 300);
        monster_hp.setFont(BATTLE_FONT);
        monster_hp.setForeground(Color.WHITE);

        monster_attack.setBounds(400, 157 + tmp, 300, 300);
        monster_attack.setFont(BATTLE_FONT);
        monster_attack.setForeground(Color.WHITE);

        monster_defend.setBounds(400, 291 + tmp, 300, 300);
        monster_defend.setFont(BATTLE_FONT);
        monster_defend.setForeground(Color.WHITE);

        player_hp.setBounds(785, 37 + tmp, 300, 300);
        player_hp.setFont(BATTLE_FONT);
        player_hp.setForeground(Color.WHITE);

        player_attack.setBounds(785, 157 + tmp, 300, 300);
        player_attack.setFont(BATTLE_FONT);
        player_attack.setForeground(Color.WHITE);

        player_defend.setBounds(785, 291 + tmp, 300, 300);
        player_defend.setFont(BATTLE_FONT);
        player_defend.setForeground(Color.WHITE);

        battleFrame.add(monster_hp, 2, 0);
        battleFrame.add(monster_attack, 3, 0);
        battleFrame.add(monster_defend, 4, 0);
        battleFrame.add(player_hp, 5, 0);
        battleFrame.add(player_attack, 6, 0);
        battleFrame.add(player_defend, 7, 0);

        monster_img.setBounds(100, 120, 72, 72);
        battleFrame.add(monster_img, 8, 0);

        monster_hp.setText(hp + "");
        monster_attack.setText(attack + "");
        monster_defend.setText(defend + "");

        player_hp.setText(MTGame.playerBean_1.getHp() + "");
        player_attack.setText(MTGame.playerBean_1.getAttack() + "");
        player_defend.setText(MTGame.playerBean_1.getDefend() + "");
        battleFrame.setVisible(true);
        MTGame.inConversation = true;
        Timer bFrame = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ex) {
                attack(monsterBean);
                monster_hp.setText(hp + "");
                player_hp.setText(MTGame.playerBean_1.getHp() + "");
                MTGame.fr.repaint();
                if (hp <= 0) {
                    battleFrame.setVisible(false);
                    MTGame.inConversation = false;

                    MTGame.playerBean_1.setExp(MTGame.playerBean_1.getExp() + monsterBean.getExp());//exp += monsterBean.getExp();
                    MTGame.playerBean_1.setMoney(MTGame.playerBean_1.getMoney() + monsterBean.getMoney());// += monsterBean.getMoney();
                    MsgUtil.displayMessage("获得金币数 " + monsterBean.getExp() + " 经验值 " + monsterBean.getMoney() + " ！");
                    battleFrame.remove(monster_img);
                    battleFrame.remove(monster_hp);
                    battleFrame.remove(monster_attack);
                    battleFrame.remove(monster_defend);
                    battleFrame.remove(player_hp);
                    battleFrame.remove(player_attack);
                    battleFrame.remove(player_defend);


                    LvMap[currentFloor][y][x] = 0;
                    MTGame.playerBean_1.move(x, y);
                    ((Timer) ex.getSource()).stop();
                }
            }
        });
        bFrame.start();
    }

    private void attack(MonsterBean e) {
        if (MTGame.playerBean_1.getAttack() > defend) {
            hp = hp - MTGame.playerBean_1.getAttack() + defend;
        }
        if (hp <= 0) return;
        if (attack > MTGame.playerBean_1.getDefend()) {
            MTGame.playerBean_1.setHp(MTGame.playerBean_1.getHp() - attack + MTGame.playerBean_1.getDefend()); //= hp - e.getAttack() + defend;
        }
        if (MTGame.playerBean_1.getAttack() < defend && attack < MTGame.playerBean_1.getDefend()) return;
    }
}
