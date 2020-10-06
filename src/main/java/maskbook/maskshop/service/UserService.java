package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.User;
import maskbook.maskshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        validateDuplicateUser(user);

        userRepository.save(user);

        return user.getUserId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getUserName());

        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

}
