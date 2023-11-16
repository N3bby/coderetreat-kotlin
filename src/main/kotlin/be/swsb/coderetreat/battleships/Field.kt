package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.Shot.Hit
import be.swsb.coderetreat.battleships.Shot.Miss
import be.swsb.coderetreat.battleships.math.Bounds
import be.swsb.coderetreat.battleships.math.Location
import java.lang.IllegalArgumentException

sealed class Shot(val location: Location) {
    class Hit(location: Location): Shot(location)
    class Miss(location: Location): Shot(location)
}

data class Field private constructor(
    private val fleet: Fleet,
    private val shots: List<Shot> = emptyList(),
    val bounds: Bounds,
) {

    companion object {
        fun emptyField(bounds: Bounds): Field {
            return Field(
                fleet = Fleet(bounds = bounds),
                bounds = bounds
            )
        }
    }

    fun addShip(ship: Ship): Field {
        return copy(fleet = fleet.addShip(ship))
    }

    fun isShipAtLocation(location: Location): Boolean {
        return fleet.isShipAtLocation(location)
    }

    fun isHitAtLocation(location: Location): Boolean {
        return shots.find { it.location == location } is Hit
    }

    fun isMissAtLocation(location: Location): Boolean {
        return shots.find { it.location == location } is Miss
    }

    fun shoot(location: Location): Field {
        if(!bounds.isWithinBounds(location)) {
            throw IllegalArgumentException("Shot is not within bounds of the field")
        }
        if(shots.any { it.location == location }) {
            throw IllegalArgumentException("This location was already shot")
        }

        val shot = if (isShipAtLocation(location)) Hit(location) else Miss(location)

        return copy(shots = shots + listOf(shot))
    }
}