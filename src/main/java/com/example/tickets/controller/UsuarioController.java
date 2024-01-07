package com.example.tickets.controller;

import java.util.List;
import com.example.tickets.model.Usuario;
import com.example.tickets.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/usuario")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombre(@RequestParam String nombre) {
        Usuario usuario = usuarioService.obtenerUsuarioPorNombre(nombre);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuariostecnicos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosTecnicos() {
        return ResponseEntity.ok(usuarioService.obtenerUsuariosTecnicos());
    }

    @GetMapping("/usuariosnotecnicos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosNoTecnicos() {
        return ResponseEntity.ok(usuarioService.obtenerUsuariosNoTecnicos());
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.agregarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) int esTecnico) {
        Usuario usuarioActualizado = usuarioService.modificarUsuario(id, nombre, password, esTecnico);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {
        usuarioService.borrarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/verificaruser")
    public ResponseEntity<Usuario> verificarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioVerificar = usuarioService.verificarUsuario(usuario.getNombre(), usuario.getPassword());
        if (usuarioVerificar != null) {
            return ResponseEntity.ok(usuarioVerificar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
