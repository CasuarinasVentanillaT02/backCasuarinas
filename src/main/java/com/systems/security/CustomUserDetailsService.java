package com.systems.security;

import com.systems.dto.UserDTO;
import com.systems.repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String de_alias) throws UsernameNotFoundException {
        //System.out.println("Custom: "+de_alias);
        List<Object[]> userArray = userRepository.findByDeAlias(de_alias);
        
        UserDTO userDTO = new UserDTO();
        Object[] user = userArray.get(0);
        userDTO.setDe_habitante(user[0].toString());
        userDTO.setDe_alias(user[1].toString());
        userDTO.setDe_clave(user[2].toString());
        userDTO.setDe_rol(user[3].toString());
        userDTO.setSt_activo(user[4].toString());
        userDTO.setDe_usuario_reg(user[5].toString());
        userDTO.setFe_reg(user[6] != null ? ((Timestamp) user[6]).toLocalDateTime() : null);
        userDTO.setDe_usuario_upd((String) user[7]);
        userDTO.setFe_upd(user[8] != null ? ((Timestamp) user[8]).toLocalDateTime() : null);
        userDTO.setId_usuario((Integer) user[9]);
        userDTO.setId_habitante((Integer) user[10]);
        userDTO.setId_rol((Integer) user[11]);
        
        return AuthUser.builder()
                .user(userDTO)
                .build();
    }
}
