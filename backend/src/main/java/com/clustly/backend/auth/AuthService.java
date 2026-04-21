package com.clustly.backend.auth;

import com.clustly.backend.auth.dto.AuthResponseDTO;
import com.clustly.backend.auth.dto.LoginRequestDTO;
import com.clustly.backend.auth.dto.RegisterRequestDTO;
import com.clustly.backend.negocio.Negocio;
import com.clustly.backend.negocio.NegocioRepository;
import com.clustly.backend.usuario.Rol;
import com.clustly.backend.usuario.Usuario;
import com.clustly.backend.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final NegocioRepository businessRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (businessRepository.existsByUrl(request.getUrlNegocio())) {
            throw new RuntimeException("El url ya está en uso");
        }

        Negocio negocio = Negocio.builder()
                .nombre(request.getNombreNegocio())
                .url(request.getUrlNegocio())
                .telefono(request.getTelefonoNegocio())
                .activo(true)
                .build();
        businessRepository.save(negocio);

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .rol(Rol.TipoRol.DUEÑO)
                .business(negocio)
                .build();
        userRepository.save(usuario);

        return AuthResponseDTO.builder()
                .accessToken(jwtService.generateToken(usuario))
                .refreshToken(jwtService.generateRefreshToken(usuario))
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .businessId(negocio.getId())
                .businessUrl(negocio.getUrl())
                .build();
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContrasena())
        );

        Usuario usuario = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return AuthResponseDTO.builder()
                .accessToken(jwtService.generateToken(usuario))
                .refreshToken(jwtService.generateRefreshToken(usuario))
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .businessId(usuario.getBusiness() != null ? usuario.getBusiness().getId() : null)
                .businessUrl(usuario.getBusiness() != null ? usuario.getBusiness().getUrl() : null)
                .build();
    }
}