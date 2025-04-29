package com.systems.service;

import com.systems.dto.ListaHabitantes;
import com.systems.dto.ListaRoles;
import com.systems.dto.VistaUsuarioxId;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.UserDTO;
import com.systems.dto.VistaPerfilUsuarioxId;
import java.util.List;

public interface UserService {
    ResultSpDTO fSpChangePass(String deClaveActual,String deClaveNueva);
    ResultSpDTO userDelxId(Integer idUsuario);
    ResultSpDTO userUpd(VistaUsuarioxId vistaUsuarioxId);
    ResultSpDTO userSave(VistaUsuarioxId vistaUsuarioxId);
    VistaUsuarioxId getUsuarioxId(Integer idUsuario);
    List<ListaRoles> getAllRoles();
    List<ListaHabitantes> getAllHabitantes();
    List<UserDTO> getAllUsers();
    VistaPerfilUsuarioxId getUserPerfilXid();
    
    UserDTO getLoginUser();
}
