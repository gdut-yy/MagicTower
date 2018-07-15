package com.mymt.util;

import com.mymt.MTGame;
import com.mymt.bean.MonsterBean;
import com.mymt.data.MonsterData;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

import static com.mymt.MTGame.*;
import static com.mymt.data.MapData.LvMap;

/**
 * ForecastUtil 工具类
 * <p>
 * 对应物品 [圣光徽] 按 L 键查看怪物的基本情况
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class ForecastUtil {

    public static JLayeredPane forecastLPane = new JLayeredPane();     // 预测信息面板

    static {
        // 初始化 预测面板
        forecastLPane.setLayout(null);
        forecastLPane.setBounds(6 * GAME_PIX_72, GAME_PIX_72, GAME_PIX_72 * 11, GAME_PIX_72 * 11);
        forecastLPane.setBackground(Color.BLACK);   // 黑色背景
        forecastLPane.setOpaque(true);
        forecastLPane.setVisible(false);
    }

    // 查看怪物功能
    public static void displayForecast() {
        inConversation = true;
        forecastLPane.setVisible(true);
        int cnt = 0;

        HashSet<Integer> forecastSet = new HashSet<>();
        // 遍历当前楼层
        for (int x = 0; x < LvMap[currentFloor].length; x++) {
            for (int y = 0; y < LvMap[currentFloor][x].length; y++) {
                int id = LvMap[currentFloor][x][y];
                // 如果 id 对应 怪物 且 在 forecastSet 中不存在 id 值
                if ((id >= 40 && id <= 70) && !forecastSet.contains(id)) {
                    forecastSet.add(id);
                    MonsterBean m = MonsterData.monsterMap.get(id);

                    JLabel nameLabel = new JLabel("名称：" + m.getName());
                    JLabel hpLabel = new JLabel("生命：" + m.getHp());
                    JLabel attackLabel = new JLabel("攻击：" + m.getAttack());
                    JLabel defendLabel = new JLabel("防御：" + m.getDefend());
                    JLabel moneyExpLabel = new JLabel("金 · 经：" + m.getMoney() + " · " + m.getExp());
                    JLabel loseLabel = new JLabel("损失：" + forecast(m));

                    nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    nameLabel.setForeground(Color.WHITE);
                    nameLabel.setFont(new Font("Serif", 0, 25));

                    hpLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    hpLabel.setForeground(Color.WHITE);
                    hpLabel.setFont(new Font("Serif", 0, 25));

                    attackLabel.setBounds(339, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    attackLabel.setForeground(Color.WHITE);
                    attackLabel.setFont(new Font("Serif", 0, 25));

                    defendLabel.setBounds(339, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    defendLabel.setForeground(Color.WHITE);
                    defendLabel.setFont(new Font("Serif", 0, 25));

                    moneyExpLabel.setBounds(563, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    moneyExpLabel.setForeground(Color.WHITE);
                    moneyExpLabel.setFont(new Font("Serif", 0, 25));

                    loseLabel.setBounds(563, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    loseLabel.setForeground(Color.WHITE);
                    loseLabel.setFont(new Font("Serif", 0, 25));


//                    JLabel temp = new JLabel(
//                            "名称：" + (MonsterData.monsterMap.get(id).getName())
//                                    + "  生命：" + (MonsterData.monsterMap.get(id)).getHp()
//                                    + "  攻击：" + (MonsterData.monsterMap.get(id)).getAttack()
//                                    + "  防御：" + (MonsterData.monsterMap.get(id)).getDefend()
//                                    + "  金币：" + (MonsterData.monsterMap.get(id)).getMoney()
//                                    + "  经验：" + (MonsterData.monsterMap.get(id)).getExp()
//                                    + "  损失：" + forecast(MonsterData.monsterMap.get(id)));
//
//                    temp.setBounds(100, 20 + 80 * cnt, 11 * GAME_PIX_72, 30);
//                    temp.setForeground(Color.WHITE);
//                    temp.setFont(new Font("Serif", 0, 20));

                    JLabel headLabel = new JLabel();
                    headLabel.setIcon(new ImageIcon(MTGame.imgSource.get(id)));
                    headLabel.setBounds(25, 24 + 96 * cnt, GAME_PIX_72, GAME_PIX_72);
                    cnt++;

                    forecastLPane.add(nameLabel);
                    forecastLPane.add(hpLabel);
                    forecastLPane.add(attackLabel);
                    forecastLPane.add(defendLabel);
                    forecastLPane.add(moneyExpLabel);
                    forecastLPane.add(loseLabel);
//                    forecastLPane.add(temp);
                    forecastLPane.add(headLabel);
                }
            }
        }
    }

    // 预测功能
    public static String forecast(MonsterBean e) {
        if (MTGame.playerBean_1.getAttack() <= e.getDefend()) {
            return "???";
        } else if (MTGame.playerBean_1.getDefend() >= e.getAttack()) {
            return 0 + "";
        } else {
            return ((e.getHp() / (MTGame.playerBean_1.getAttack() - e.getDefend())) * (e.getAttack() - MTGame.playerBean_1.getDefend())) + "";
        }
    }
}
