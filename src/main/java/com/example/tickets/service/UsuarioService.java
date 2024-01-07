package com.example.tickets.service;

import java.util.List;
import com.example.tickets.model.Usuario;
import org.springframework.stereotype.Service;
import com.example.tickets.repository.UsuarioRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public Usuario verificarUsuario(String nombre, String password){
        Usuario usuarioBuscar=usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
        
        if(usuarioBuscar!=null && usuarioBuscar.getPassword().equals(password))
            return usuarioBuscar;
        else 
            return null;
    }
    
    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> obtenerUsuariosNoTecnicos() {
        // Aquí suponemos que el método findAll() devuelve una lista de todos los usuarios
        // y luego filtramos esta lista según el nombre proporcionado
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getEsTecnico() == Usuario.NO_ES_TECNICO)
                .collect(Collectors.toList());
    }

    public List<Usuario> obtenerUsuariosTecnicos() {
        // Aquí suponemos que el método findAll() devuelve una lista de todos los usuarios
        // y luego filtramos esta lista según el nombre proporcionado
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getEsTecnico() == Usuario.ES_TECNICO)
                .collect(Collectors.toList());
    }

    public Usuario agregarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario modificarUsuario(Long id, String nuevoNombre, String nuevoPassword, int esTecnico) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNombre(nuevoNombre);
            usuario.setPassword(nuevoPassword);
            usuario.setEsTecnico(esTecnico);
            return usuarioRepository.save(usuario);
        } else {
            // Manejo de la situación donde el usuario no se encuentra
            // Puede lanzar una excepción o devolver null
            return null;
        }
    }

    public void borrarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
