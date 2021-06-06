package com.slearning.pokedex.model

import org.eclipse.aether.util.graph.manager.DependencyManagerUtils
import javax.persistence.*

@Entity(name = "pokemons")
@Table(name = "pokemons", schema = "pokedex")
class PokemonEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_sequence")
    @SequenceGenerator(name="pokemon_sequence", sequenceName = "POK")
    @Column(name = "id", updatable = false, nullable = false)
    var id: String? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @Column(name = "description", nullable = false)
    var description: String? = null,

    @ManyToMany
    @JoinTable(
        name = "pokemon_has_type",
        joinColumns = [JoinColumn(name = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "type_id")]
    )
    var types: Set<TypeEntity> = setOf(),

    @ManyToMany
    @JoinTable(
        name = "pokemon_has_skill",
        joinColumns = [JoinColumn(name = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "skill_id")]
    )
    var skills: Set<SkillEntity> = setOf()

)