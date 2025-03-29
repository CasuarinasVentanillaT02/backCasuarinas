package com.systems.repository;

import com.systems.dto.EntityFake;
import com.systems.dto.UserDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EntityFake, Long> {

    @Query(value = "SELECT * from mantenimiento.f_sp_user_del(:aiIdUsuario)", nativeQuery = true)
    List<Object[]> fSpUserDel(
            @Param("aiIdUsuario") Integer aiIdUsuario
    );

    @Query(value = "SELECT * from mantenimiento.f_sp_user_update(:aiIdUsuario,:aiIdHabitante,:aiIdRol,:asDeAlias,:asStActivo,:idUsuarioUpd)", nativeQuery = true)
    List<Object[]> fSpUserUpdate(
            @Param("aiIdUsuario") Integer aiIdUsuario,
            @Param("aiIdHabitante") Integer aiIdHabitante,
            @Param("aiIdRol") Integer aiIdRol,
            @Param("asDeAlias") String asDeAlias,
            @Param("asStActivo") String asStActivo,
            @Param("idUsuarioUpd") Integer idUsuarioUpd
    );

    @Query(value = "SELECT * from mantenimiento.f_sp_user_save(:aiIdHabitante,:aiIdRol,:asDeAlias,:asStActivo,:idUsuarioReg)", nativeQuery = true)
    List<Object[]> fSpUserSave(
            @Param("aiIdHabitante") Integer aiIdHabitante,
            @Param("aiIdRol") Integer aiIdRol,
            @Param("asDeAlias") String asDeAlias,
            @Param("asStActivo") String asStActivo,
            @Param("idUsuarioReg") Integer idUsuarioReg
    );

    @Query(value = "SELECT * from mantenimiento.f_view_usuario_x_id(:aiIdUsuario)", nativeQuery = true)
    List<Object[]> fViewUsuarioxId(@Param("aiIdUsuario") Integer aiIdUsuario);

    @Query(value = "SELECT * from mantenimiento.f_list_roles()", nativeQuery = true)
    List<Object[]> fListRoles();

    @Query(value = "SELECT * from mantenimiento.f_list_habitantes()", nativeQuery = true)
    List<Object[]> fListHabitantes();

    @Query(value = "SELECT * from mantenimiento.f_view_usuarios()", nativeQuery = true)
    List<Object[]> fViewUsuarios();

    @Query(value = "SELECT * from mantenimiento.f_get_user_x_alias(:asDeUsuario)", nativeQuery = true)
    List<Object[]> findByDeAlias(@Param("asDeUsuario") String asDeUsuario);

    /*default UserDTO getUserResponseByAlias(String asDeUsuario) {
        return findByDeAlias(asDeUsuario)
                .map(user -> new UserDTO(
                (String) user[0], // de_habitante
                (String) user[1], // de_alias
                (String) user[2], // de_clave
                (String) user[3], // de_rol
                (String) user[4], // st_activo
                (String) user[5], // de_usuario_reg
                (LocalDateTime) user[6], // fe_reg
                (String) user[7], // de_usuario_upd
                (LocalDateTime) user[8], // fe_upd
                (Integer) user[9],
                (Integer) user[10],
                (Integer) user[11]
        ))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con alias: " + asDeUsuario));
    }
*/
    @Query(value = "SELECT * from mantenimiento.f_sp_save_user(:aiIdHabitante,:aiIdRol,:asDeAlias,:asDeClave,:asStActivo,:idUsuarioReg)", nativeQuery = true)
    List<Object[]> f_sp_save_user(
            @Param("aiIdHabitante") Integer aiIdHabitante,
            @Param("aiIdRol") Integer aiIdRol,
            @Param("asDeAlias") String asDeAlias,
            @Param("asDeClave") String asDeClave,
            @Param("asStActivo") String asStActivo,
            @Param("idUsuarioReg") Integer idUsuarioReg
    );
    
    @Query(value = "SELECT * from mantenimiento.f_get_user_perfil_x_id(:aiIdUsuario)", nativeQuery = true)
    List<Object[]> f_get_user_perfil_x_id(
            @Param("aiIdUsuario") Integer aiIdUsuario
    );
}
