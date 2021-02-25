package hello.demospringcore.member;

public interface MemeberRepository {
    void save(Member member);
    Member findById(Long memeberId);
}
