package com.iBME.emg_label_tool.security;


import com.iBME.emg_label_tool.constant.ExceptionMessage;
import com.iBME.emg_label_tool.exception.EmailNotFoundException;
import com.iBME.emg_label_tool.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserDetail::new)
                .orElseThrow(() -> new EmailNotFoundException(ExceptionMessage.EMAIL_NOT_FOUND));

    }
}
