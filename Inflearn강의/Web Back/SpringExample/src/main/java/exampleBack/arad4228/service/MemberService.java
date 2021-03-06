package exampleBack.arad4228.service;

import exampleBack.arad4228.domain.Member;
import exampleBack.arad4228.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private  final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member) {
        /*
        시간을 찍기위했던 로직들 메서드의 경과 시간을 확인하기위한 방법 중 하나.
        long start = System.currentTimeMillis();

        try {
            // 중복확인
            ValidateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join =" + timeMs +  "Ms");
        }
        */

        // 같은 이름이 있는 회원은 불가능 하다.
        /*
        Optional <Member> result = memberRepository.findByName(member.getName());
        // 요즘은 null을 optional로 감싼다.
        result.ifPresent(m ->{
               throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        // 중복회원 검증
        ValidateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void ValidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
    /*
        전체 회원 조회.
     */
    public List<Member> findMembers() {
        /*
        시간을 찍기위했던 로직들
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
        */
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
