package com.systems.service.impl;

import com.systems.dto.ListaHabitantes;
import com.systems.dto.ListaRoles;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.UserDTO;
import com.systems.dto.VistaPerfilUsuarioxId;
import com.systems.dto.VistaUsuarioxId;
import com.systems.repository.UserRepository;
import com.systems.service.UserService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    //private final JwtUtils jwtUtils;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public ResultSpDTO userDelxId(Integer idUsuario) {
        List<Object[]> result = userRepository.fSpUserDel(idUsuario);
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            ResultSpDTO dto = new ResultSpDTO();
                
            dto.setCodigo((Integer)row[0]);
            dto.setStatus(dto.getCodigo() == 1 ? 200 : 500);
            dto.setMensaje((String)row[1]);
            return dto;
        }
        throw new RuntimeException("No se pudo procesar el Eliminar Usuario");
    }

    @Override
    public ResultSpDTO userUpd(VistaUsuarioxId vistaUsuarioxId) {
        UserDTO usuarioLogin = this.getLoginUser();
        Integer IdUsuarioUpd = usuarioLogin.getId_usuario();
        
        List<Object[]> result = userRepository.fSpUserUpdate(
                vistaUsuarioxId.getId_usuario(),
                vistaUsuarioxId.getId_habitante(),
                vistaUsuarioxId.getId_rol(),
                vistaUsuarioxId.getDe_alias(),
                vistaUsuarioxId.getSt_activo(),
                IdUsuarioUpd
        );
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            ResultSpDTO dto = new ResultSpDTO();
                
            dto.setCodigo((Integer)row[0]);
            dto.setStatus(dto.getCodigo() == 1 ? 200 : 500);
            dto.setMensaje((String)row[1]);
            return dto;
        }
        throw new RuntimeException("No se pudo procesar el Actualizar Usuario");
    }

    @Override
    public ResultSpDTO userSave(VistaUsuarioxId vistaUsuarioxId) {
        UserDTO usuarioLogin = this.getLoginUser();
        Integer IdUsuarioReg = usuarioLogin.getId_usuario();
        
        log.info("Valores enviados al repositorio: Habitante={}, Rol={}, Alias={}, Activo={}, UsuarioReg={}",
         vistaUsuarioxId.getId_habitante(), vistaUsuarioxId.getId_rol(),
         vistaUsuarioxId.getDe_alias(), vistaUsuarioxId.getSt_activo(), IdUsuarioReg);

        List<Object[]> result = userRepository.fSpUserSave(
                vistaUsuarioxId.getId_habitante(),
                vistaUsuarioxId.getId_rol(),
                vistaUsuarioxId.getDe_alias(),
                vistaUsuarioxId.getSt_activo(),
                IdUsuarioReg
        );
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            ResultSpDTO dto = new ResultSpDTO();
                
            dto.setCodigo((Integer)row[0]);
            dto.setStatus(dto.getCodigo() == 1 ? 200 : 500);
            dto.setMensaje((String)row[1]);
            return dto;
        }
        throw new RuntimeException("No se pudo procesar el Grabar Usuario");
    }

    @Override
    public VistaUsuarioxId getUsuarioxId(Integer idUsuario) {
        List<Object[]> result = userRepository.fViewUsuarioxId(idUsuario);
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            VistaUsuarioxId dto = new VistaUsuarioxId();
                
            dto.setId_usuario((Integer)row[0]);
            dto.setId_habitante((Integer)row[1]);
            dto.setId_rol((Integer)row[2]);
            dto.setDe_alias((String)row[3]);
            dto.setSt_activo((String) row[4]);
            dto.setDe_usuario_reg((String) row[5]);
            dto.setFe_reg(row[6] != null ? ((Timestamp) row[6]).toLocalDateTime() : null);
            dto.setDe_usuario_upd((String) row[7]);
            dto.setFe_upd(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);
            return dto;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public List<ListaRoles> getAllRoles() {
        List<Object[]> result = userRepository.fListRoles();        
        if (!result.isEmpty()) {
            List<ListaRoles> dtoList = new ArrayList<>();
            for (Object[] row : result) {
                ListaRoles dto = new ListaRoles();
                
                dto.setId_rol((Integer)row[0]);
                dto.setDe_rol((String)row[1]);
                
                dtoList.add(dto);
            }
            return dtoList;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public List<ListaHabitantes> getAllHabitantes() {
        List<Object[]> result = userRepository.fListHabitantes();        
        if (!result.isEmpty()) {
            List<ListaHabitantes> dtoList = new ArrayList<>();
            for (Object[] row : result) {
                ListaHabitantes dto = new ListaHabitantes();
                
                dto.setId_habitante((Long)row[0]);
                dto.setDe_habitante((String)row[1]);
                
                dtoList.add(dto);
            }
            return dtoList;
        }
        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<Object[]> result = userRepository.fViewUsuarios();

        if (!result.isEmpty()) {
            List<UserDTO> dtoList = new ArrayList<>();

            for (Object[] row : result) {
                UserDTO dto = new UserDTO();

                dto.setDe_habitante((String)row[0]);
                dto.setDe_alias((String)row[1]);
                dto.setDe_clave((String)row[2]);
                dto.setDe_rol((String)row[3]);
                dto.setSt_activo((String)row[4]);
                dto.setDe_usuario_reg((String)row[5]);
                dto.setFe_reg(row[6] != null ? ((Timestamp) row[6]).toLocalDateTime() : null);
                dto.setDe_usuario_upd((String)row[7]); 
                dto.setFe_upd(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);
                dto.setId_usuario((Integer)row[9]);
                dto.setId_habitante((Integer)row[10]);
                dto.setId_rol((Integer)row[11]);

                dtoList.add(dto);
            }
            return dtoList;
        }

        throw new RuntimeException("No se encontraron resultados");
    }

    @Override
    public UserDTO getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuario = authentication.getName();
        System.out.println("usuario login: "+usuario);
        List<Object[]> user = userRepository.findByDeAlias(usuario);
                //.orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        UserDTO dto = new UserDTO();
        
        Object[] row = user.get(0);
        
        dto.setDe_habitante((String)row[0]);
        dto.setDe_alias((String)row[1]);
        dto.setDe_clave((String)row[2]);
        dto.setDe_rol((String)row[3]);
        dto.setSt_activo((String)row[4]);
        dto.setDe_usuario_reg((String)row[5]);
        dto.setFe_reg(row[6] != null ? ((Timestamp) row[6]).toLocalDateTime() : null);
        dto.setDe_usuario_upd((String)row[7]); 
        dto.setFe_reg(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);
        dto.setId_usuario((Integer)row[9]);
        dto.setId_habitante((Integer)row[10]);
        dto.setId_rol((Integer)row[11]);
        return dto;
    }

    @Override
    public VistaPerfilUsuarioxId getUserPerfilXid() {
        Integer idUsuario = this.getLoginUser().getId_usuario();
        List<Object[]> result = userRepository.f_get_user_perfil_x_id(idUsuario);
        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            VistaPerfilUsuarioxId dto = new VistaPerfilUsuarioxId();

            dto.setDe_alias((String)row[0]);
            dto.setDe_habitante((String)row[1]);
            dto.setDe_rol((String)row[2]);
            dto.setSt_activo((String) row[3]);
            dto.setFe_baja(row[4] != null ? ((Timestamp) row[4]).toLocalDateTime() : null);
            dto.setDe_usuario_reg((String) row[5]);
            dto.setFe_reg(row[6] != null ? ((Timestamp) row[6]).toLocalDateTime() : null);
            dto.setDe_usuario_upd((String) row[7]);
            dto.setFe_upd(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);
            return dto;
        }
        throw new RuntimeException("No se encontraron resultados");
    }
    
}
