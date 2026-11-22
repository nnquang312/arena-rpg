package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import model.character.Hero;
import model.character.Monster;

public class SwingUI implements GameUI {

    // Hàng đợi: nút bấm bỏ lựa chọn vào, Battle lấy ra (chờ nếu chưa có)
    private final BlockingQueue<Integer> inputs = new LinkedBlockingQueue<>();

    private JFrame frame;
    private JTextArea log;
    private JLabel heroName, enemyName;
    private JProgressBar heroHp, heroMana, enemyHp;
    private JButton attackBtn, skillBtn;
    private final JButton[] skillBtns = new JButton[3];

    public SwingUI() {
        try {
            SwingUtilities.invokeAndWait(this::buildWindow); // dựng cửa sổ trên luồng giao diện
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void buildWindow() {
        frame = new JFrame("Đấu Trường Quái Vật");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));

        // Phần trên: trạng thái hero (trái) và quái (phải)
        heroName = new JLabel("Hero");
        heroHp = makeBar(new Color(200, 50, 50));
        heroMana = makeBar(new Color(60, 100, 220));
        JPanel heroPanel = new JPanel(new GridLayout(3, 1, 0, 4));
        heroPanel.add(heroName);
        heroPanel.add(heroHp);
        heroPanel.add(heroMana);

        enemyName = new JLabel("Quái vật");
        enemyHp = makeBar(new Color(200, 50, 50));
        JPanel enemyPanel = new JPanel(new GridLayout(3, 1, 0, 4));
        enemyPanel.add(enemyName);
        enemyPanel.add(enemyHp);
        enemyPanel.add(new JLabel(""));

        JPanel top = new JPanel(new GridLayout(1, 2, 16, 0));
        top.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        top.add(heroPanel);
        top.add(enemyPanel);
        frame.add(top, BorderLayout.NORTH);

        // Giữa: nhật ký trận đấu
        log = new JTextArea();
        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        frame.add(new JScrollPane(log), BorderLayout.CENTER);

        // Dưới: các nút
        attackBtn = new JButton("Đánh thường");
        attackBtn.addActionListener(e -> choose(1));
        skillBtn = new JButton("Kỹ năng");
        skillBtn.addActionListener(e -> choose(2));

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(attackBtn);
        buttons.add(skillBtn);
        for (int i = 0; i < 3; i++) {
            final int number = i + 1;
            skillBtns[i] = new JButton("Kỹ năng " + number);
            skillBtns[i].addActionListener(e -> choose(number));
            buttons.add(skillBtns[i]);
        }
        frame.add(buttons, BorderLayout.SOUTH);

        setButtons(false, false);
        frame.setSize(680, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JProgressBar makeBar(Color color) {
        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setForeground(color);
        return bar;
    }

    private void setBar(JProgressBar bar, int value, int max, String label) {
        bar.setMaximum(max);
        bar.setValue(value);
        bar.setString(label + " " + value + "/" + max);
    }

    // Bật hoặc tắt nhóm nút (luôn chạy trên luồng giao diện)
    private void setButtons(boolean actions, boolean skills) {
        SwingUtilities.invokeLater(() -> {
            attackBtn.setEnabled(actions);
            skillBtn.setEnabled(actions);
            for (JButton b : skillBtns) {
                b.setEnabled(skills);
            }
        });
    }

    private void choose(int number) {
        inputs.offer(number);
        setButtons(false, false); // chống bấm đúp
    }

    private int waitForClick() {
        try {
            return inputs.take(); // chờ tới khi có nút được bấm
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 1;
        }
    }

    @Override
    public void showMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            log.append(message + "\n");
            log.setCaretPosition(log.getDocument().getLength()); // cuộn xuống cuối
        });
    }

    @Override
    public void showStatus(Hero hero, Monster enemy) {
        // đọc số liệu ngay bây giờ, rồi mới giao cho luồng giao diện vẽ
        String hName = hero.getName() + "  (Lv " + hero.getLevel() + ")";
        int hp = hero.getHp(), maxHp = hero.getMaxHP();
        int mana = hero.getMana(), maxMana = hero.getMaxMana();
        String eName = enemy.getName();
        int eHp = enemy.getHp(), eMax = enemy.getMaxHP();

        SwingUtilities.invokeLater(() -> {
            heroName.setText(hName);
            setBar(heroHp, hp, maxHp, "HP");
            setBar(heroMana, mana, maxMana, "Mana");
            enemyName.setText(eName);
            setBar(enemyHp, eHp, eMax, "HP");
        });
    }

    @Override
    public int askAction() {
        inputs.clear();
        setButtons(true, false);
        return waitForClick();
    }

    @Override
    public int askSkill() {
        inputs.clear();
        setButtons(false, true);
        return waitForClick();
    }
}