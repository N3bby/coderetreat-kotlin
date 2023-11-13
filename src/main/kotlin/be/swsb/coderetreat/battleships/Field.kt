package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.math.Bounds
import be.swsb.coderetreat.battleships.math.Location
import java.lang.IllegalArgumentException

data class Field(private val ships: List<Ship> = emptyList(), private val bounds: Bounds) {
    init {
        validate()
    }

    fun validate() {
        if(!allShipsWithinBounds()) {
            throw IllegalArgumentException("Ships must be placed within the bounds of the field")
        }
        if(doShipsOverlap()) {
            throw IllegalArgumentException("Ships may not overlap")
        }
        if(!allShipsOfUniqueType()) {
            throw IllegalArgumentException("You may only place each type of ship once")
        }
    }

    fun addShip(ship: Ship): Field {
        return copy(ships = ships + listOf(ship))
    }

    private fun allShipsWithinBounds(): Boolean {
        return ships.all { it.isWithinBounds(bounds) }
    }

    private fun doShipsOverlap(): Boolean {
        val amountOfUniqueLocations = ships.flatMap { it.getLocations() }.toSet().size
        val amountOfLocations = ships.map { it.getLocations().size }.sum()
        return amountOfUniqueLocations != amountOfLocations
    }

    private fun allShipsOfUniqueType(): Boolean {
        val amountOfUniqueTypes = ships.map { it.type }.toSet().size
        return amountOfUniqueTypes == ships.size
    }

    fun isShipAtLocation(location: Location): Boolean {
        return ships.any { it.getLocations().contains(location) }
    }
}