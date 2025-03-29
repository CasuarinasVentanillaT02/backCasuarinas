package com.systems.controller;

import com.systems.dto.ListaHabitantes;
import com.systems.dto.ListaRoles;
import com.systems.dto.ResultSpDTO;
import com.systems.dto.UserDTO;
import com.systems.dto.VistaPerfilUsuarioxId;
import com.systems.dto.VistaUsuarioxId;
import com.systems.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<ResultSpDTO> UserDelete(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(userService.userDelxId(idUsuario));
    }
    
    @PutMapping
    public ResponseEntity<ResultSpDTO> UserUpdate(@RequestBody @Valid VistaUsuarioxId vistaUsuarioxId) {
        return ResponseEntity.ok(userService.userUpd(vistaUsuarioxId));
    }
    
    @PostMapping
    public ResponseEntity<ResultSpDTO> UserSave(@RequestBody @Valid VistaUsuarioxId vistaUsuarioxId) {
        log.info("Cuerpo Body, {}", vistaUsuarioxId.getDe_alias());
        System.out.println("mirame");        
        return ResponseEntity.ok(userService.userSave(vistaUsuarioxId));
    }
    
    @GetMapping("/{idUsuario}")
    public ResponseEntity<VistaUsuarioxId> vistaUserxId(@PathVariable Integer idUsuario){
        VistaUsuarioxId resultado = userService.getUsuarioxId(idUsuario);
        return ResponseEntity.ok(resultado);
    }
    
    @GetMapping("/getListaRoles")
    public ResponseEntity<List<ListaRoles>> listaRoles(){
        List<ListaRoles> resultado = userService.getAllRoles();
        return ResponseEntity.ok(resultado);
    }
    
    @GetMapping("/getListaHabitantes")
    public ResponseEntity<List<ListaHabitantes>> listaHabitantes(){
        List<ListaHabitantes> resultado = userService.getAllHabitantes();
        return ResponseEntity.ok(resultado);
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> vistaUser() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return ResponseEntity.ok(userDTOList);
    }
    
    @GetMapping("/usuarioLogueado")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getLoginUser());
    }
    
    @GetMapping("/perfil")
    public ResponseEntity<VistaPerfilUsuarioxId> getPerfilUser() {
        return ResponseEntity.ok(userService.getUserPerfilXid());
    }
}
