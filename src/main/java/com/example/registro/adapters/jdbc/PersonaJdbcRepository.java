package com.example.registro.adapters.jdbc;

import com.example.registro.domain.Persona;
import com.example.registro.ports.PersonaRepository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.sql.DataSource;

public class PersonaJdbcRepository implements PersonaRepository {

    private final DataSource dataSource;

    public PersonaJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Persona save(Persona persona) {
        if (persona.getId() == null) {
            return insert(persona);
        } else {
            return update(persona);
        }
    }

    private Persona insert(Persona p) {
        String sql = "INSERT INTO PERSONAS (NOMBRE, APELLIDO, EMAIL, EDAD) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            if (p.getEdad() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, p.getEdad());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getLong(1));
            }
            return findById(p.getId()).orElse(p);
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando persona", e);
        }
    }

    private Persona update(Persona p) {
        String sql = "UPDATE PERSONAS SET NOMBRE=?, APELLIDO=?, EMAIL=?, EDAD=? WHERE ID=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getEmail());
            if (p.getEdad() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, p.getEdad());
            ps.setLong(5, p.getId());
            ps.executeUpdate();
            return findById(p.getId()).orElse(p);
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando persona", e);
        }
    }

    @Override
    public Optional<Persona> findById(Long id) {
        String sql = "SELECT * FROM PERSONAS WHERE ID=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando persona por id", e);
        }
    }

    public List<Persona> findAll() {
        String sql = "SELECT * FROM PERSONAS ORDER BY ID";
        List<Persona> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando todas las personas", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM PERSONAS WHERE ID=?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando persona", e);
        }
    }

    private Persona mapRow(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getLong("ID"),
                rs.getString("NOMBRE"),
                rs.getString("APELLIDO"),
                rs.getString("EMAIL"),
                rs.getInt("EDAD"),
                rs.getTimestamp("FECHA_CREACION").toLocalDateTime()
        );
    }
}
