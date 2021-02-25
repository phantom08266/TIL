package hello.demospringcore.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemeberRepository implements MemeberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memeberId) {
        return store.get(memeberId);
    }
}
