package hello.demospringcore.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memeberId);
}
