package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.character.Hero;

public class Team {

    private static final int MAX_SIZE = 3;
    private final List<Hero> members = new ArrayList<>();

    public void addMember(Hero hero) {
        if (members.size() >= MAX_SIZE) {
            throw new IllegalStateException("Đội tối đa " + MAX_SIZE + " người");
        }
        members.add(hero);
    }

    public List<Hero> getMembers() {
        return Collections.unmodifiableList(members);   // chỉ đọc từ ngoài
    }

    public List<Hero> getAliveMembers() {
        List<Hero> alive = new ArrayList<>();
        for (Hero h : members) {
            if (h.isAlive()) alive.add(h);
        }
        return alive;                                    // bản sao mới, duyệt an toàn
    }

    public boolean isAlive() {
        return !getAliveMembers().isEmpty();
    }

    public void gainExp(int amount) {
        for (Hero h : getAliveMembers()) {
            h.gainExp(amount);
        }
    }
}