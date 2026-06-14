package com.farmacia.msrecetas.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Table(name = "recetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long clienteId;

    @Column(
            nullable = false,
            length = 120
    )
    private String doctorNombre;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private EstadoReceta estado;

    @PrePersist
    public void prePersist() {

        if (this.fecha == null) {
            this.fecha = LocalDate.now();
        }

        if (this.estado == null) {
            this.estado = EstadoReceta.ACTIVA;
        }
    }
}
